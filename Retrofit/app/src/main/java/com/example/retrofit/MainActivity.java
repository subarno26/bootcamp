package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private HttpsURLConnection httpURLConnection = null;
    private URL url;
    private BufferedReader reader = null;
    private List<RetroModel> retroModelList = new ArrayList<>();

    String BASE_URL = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/posts.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
//        Call<List<RetroModel>> call = service.getAllPosts();
//        call.enqueue(new Callback<List<RetroModel>>() {
//            @Override
//            public void onResponse(Call<List<RetroModel>> call, Response<List<RetroModel>> response) {
//                generateDataList(response.body());
//                Log.e("DATA",response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<List<RetroModel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
//                Log.e("TAG",t.getMessage());
//            }
//        });


    }



    //Buttons
    public void downloadRetrofit(View view) {
        //Downloading with Retrofit
        Call<data> call = RetrofitInstance.getDataService().getAllPosts();
        call.enqueue(new Callback<data>() {
            @Override
            public void onResponse(Call<data> call, Response<data> response) {
                data data = response.body();
                List<RetroModel> retroData = data.getRetroDataList();
                retroModelList.addAll(retroData);
                generateDataList(retroModelList);
            }

            @Override
            public void onFailure(Call<data> call, Throwable t) {

            }
        });
    }
    private void generateDataList(List postList) {
        MyAdapter adapter = new MyAdapter(this,postList);
        adapter.notifyDataSetChanged();

        recyclerView.setAdapter(adapter);
        Toast.makeText(this, "Loaded with Retrofit implementation", Toast.LENGTH_SHORT).show();
    }

    public void downloadHTTPS(View view) {
        new DataDownload().execute(BASE_URL);
    }


    //Downloading task with HTTPS
    public class DataDownload extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpsURLConnection)url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = reader.readLine())!=null){

                    stringBuilder.append(line);

                }
                return stringBuilder.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

            httpURLConnection.disconnect();
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Gson gson = new Gson();
            data data = gson.fromJson(result, data.class);
            adapter = new MyAdapter(MainActivity.this,data.getRetroDataList());
            recyclerView.setAdapter(adapter);
            Toast.makeText(MainActivity.this, "Loaded with HTTP URL", Toast.LENGTH_SHORT).show();
            //adapter.notifyDataSetChanged();
        }
    }
}

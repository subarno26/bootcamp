package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private List<RetroModel> retroModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);


        GetDataService service = RetrofitInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroModel>> call = service.getAllPosts();
        call.enqueue(new Callback<List<RetroModel>>() {
            @Override
            public void onResponse(Call<List<RetroModel>> call, Response<List<RetroModel>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
                Log.e("TAG",t.getMessage());
            }
        });
    }

    private void generateDataList(List<RetroModel> postList) {
        MyAdapter adapter = new MyAdapter(this,postList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}

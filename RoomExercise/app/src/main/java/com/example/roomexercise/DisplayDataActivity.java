package com.example.roomexercise;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DisplayDataActivity extends AppCompatActivity {
    private RecyclerView recyclerViewList;
    private List<EmployeeEntity> employeeEntityList;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayDataActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


        employeeEntityList = new ArrayList<>();
        recyclerViewList = findViewById(R.id.recycleViewList);
        getEmployeeDetails();


    }

    private void getEmployeeDetails() {

        class GetEmployeeDataTask extends AsyncTask<Void, Void, List<EmployeeEntity>> {
            @Override
            protected List<EmployeeEntity> doInBackground(Void... voids) {


                List<EmployeeEntity> employeeEntities = EmployeeDatabase
                        .getInstance(getApplicationContext()).employeeDao().getEmployeeList();

                return employeeEntities;
            }

            @Override
            protected void onPostExecute(List<EmployeeEntity> employeeEntities) {
                super.onPostExecute(employeeEntities);

//                recyclerViewList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerViewList.setLayoutManager(new wrapContentLinearLayoutManager(DisplayDataActivity.this,LinearLayoutManager.VERTICAL,false));
                adapter = new Adapter(DisplayDataActivity.this, employeeEntities);
                recyclerViewList.setAdapter(adapter);


            }
        }

        GetEmployeeDataTask getEmployeeDataTask = new GetEmployeeDataTask();
        getEmployeeDataTask.execute();
    }

}

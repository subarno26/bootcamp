package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerList = findViewById(R.id.recyclerList);
        recyclerList.setLayoutManager(new LinearLayoutManager (this));
        String[] names = {"Subarno","Ashutosh", "Anupam","Tanvi","Naveen", "Arpit","Astha","Ujjwal", "Lakshya","Bharat", "Aakansha", "Priya"};
        int [] numbers = {1,2,3,4,5,6,7,8,9,10};
        recyclerList.setAdapter(new ProgrammingAdapter(names));
    }
}

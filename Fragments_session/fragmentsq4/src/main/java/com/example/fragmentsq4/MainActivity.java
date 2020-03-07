package com.example.fragmentsq4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition", 0);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ListItem itemFragment = new ListItem();
        DescriptionFragment detailFragment = new DescriptionFragment();
        detailFragment.setArguments(bundle);
        ft.replace(R.id.listFrame,itemFragment,"Item Fragment");
        ft.replace(R.id.descFrame,detailFragment,"detail");
        ft.commit();
    }
}

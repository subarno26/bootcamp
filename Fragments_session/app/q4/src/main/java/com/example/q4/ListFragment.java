package com.example.q4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class ListFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition", 0);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListItems listFrag = new ListItems();
        ListDesc descFragment = new ListDesc();
        descFragment.setArguments(bundle);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            fragmentTransaction.replace(R.id.name_list, listFrag, "listFrag");
            fragmentTransaction.replace(R.id.description, descFragment);
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.replace(R.id.listContainer, listFrag, "listFrag");
            fragmentTransaction.addToBackStack("listFrag");
            fragmentTransaction.commit();
        }

    }
}


package com.example.fragments_session;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

        frag1 fragment1;
        frag2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new frag1();
        fragment2 = new frag2();
    }

    public void fragment1_button(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //frag1 fragment1 = new frag1();
        fragmentTransaction.add(R.id.layout, fragment1, "frag1Tag");
        fragmentTransaction.addToBackStack("Fragment 1");
        fragmentTransaction.commit();
    }

    public void fragment2_button(View view) {
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
        //frag2 fragment2 = new frag2();
        fragmentTransaction2.replace(R.id.layout, fragment2, "frag2Tag");
        fragmentTransaction2.addToBackStack("Fragment 2");
        fragmentTransaction2.commit();
    }

    public void fragment_hide(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment1);
        fragmentTransaction.commit();

    }

    public void fragment_show(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.show(fragment1);
        fragmentTransaction.commit();
    }

    public void fragment_remove(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment2);
        fragmentTransaction.commit();
    }
}

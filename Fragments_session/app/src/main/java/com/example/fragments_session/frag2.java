package com.example.fragments_session;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class frag2 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Create View Fragment 2", "OnCreateView called");
        return inflater.inflate(R.layout.frag_2, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Activity Created 2", "OnActivityCreated called");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("OnAttach Fragment 2", "OnAttach called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create Fragment 2", "OnCreate called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Start Fragment 2", "OnStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resume Fragment 2", "OnResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Pause Fragment 2", "OnPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Stop Fragment 2", "OnStop called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Destroy View Fragment 2", "DestroyView called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy Fragment 2", "OnDestroy called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Detach Fragment 2", "OnDetach called");
    }
}

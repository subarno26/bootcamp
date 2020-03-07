package com.example.fragments_session;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class frag1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Create View Fragment 1", "OnCreateView called");
        return inflater.inflate(R.layout.frag_1, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Activity Created 1", "OnActivityCreated called");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("OnAttach Fragment 1", "OnAttach called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create Fragment 1", "OnCreate called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Start Fragment 1", "OnStart called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("Resume Fragment 1", "OnResume called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("Pause Fragment 1", "OnPause called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("Stop Fragment 1", "OnStop called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("Destroy View Fragment 1", "DestroyView called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Destroy Fragment 1", "OnDestroy called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("Detach Fragment 1", "OnDetach called");
    }
}

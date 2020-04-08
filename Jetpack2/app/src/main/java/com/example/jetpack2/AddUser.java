package com.example.jetpack2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddUser extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.user_add,container,false);
        return view;
    }

    EditText editName = view.findViewById(R.id.edit_name);
    EditText editEmail = view.findViewById(R.id.edit_email);
    EditText editNumber = view.findViewById(R.id.edit_number);


}





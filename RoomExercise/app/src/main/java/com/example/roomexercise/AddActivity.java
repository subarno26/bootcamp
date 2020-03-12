package com.example.roomexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    private EditText nameEditText,addressEditText,mobileEditText;
    private String name,address,mobile;
    private Button addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initializeFields();
    }

    private void initializeFields() {
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        mobileEditText = findViewById(R.id.mobileEditText);
        addButton = findViewById(R.id.addEmp);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();

            }
        });


    }

    private void saveEmployee() {
        name = nameEditText.getText().toString();
        address = addressEditText.getText().toString();
        mobile = mobileEditText.getText().toString();

        SaveEmployeeRecordsTask saveEmployeeRecordsTask = new SaveEmployeeRecordsTask();
        saveEmployeeRecordsTask.execute();


    }

    class SaveEmployeeRecordsTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setName(name);
            employeeEntity.setAddress(address);
            employeeEntity.setMobile(mobile);

            EmployeeDatabase.getInstance(getApplicationContext()).employeeDao()
                    .insert(employeeEntity);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            startActivity(new Intent(AddActivity.this,DisplayDataActivity.class));
            finish();
            Toast.makeText(AddActivity.this,"Record Saved..",Toast.LENGTH_LONG).show();
        }
    }
}

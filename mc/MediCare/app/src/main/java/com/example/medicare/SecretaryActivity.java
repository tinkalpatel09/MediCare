package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SecretaryActivity extends AppCompatActivity implements View.OnClickListener {

TextView textviewName;
    Button btnPaymentBilling, btnAddNewPatient, btnEditInformation, btnDocotrsList, btnMedicalExpense,btnLogout, btnPatientList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secretary);

        textviewName=findViewById(R.id.textviewName);
        Bundle mBundle=getIntent().getExtras();
        if(mBundle!=null) {
            String fullName = mBundle.getString("FullName");
            textviewName.setText("Welcome "+fullName);
            textviewName.setTextColor(getResources().getColor(R.color.btncolor));
        }



        btnPaymentBilling = findViewById(R.id.btnPaymentBilling);
        btnAddNewPatient = findViewById(R.id.btnAddNewPatient);
        btnEditInformation = findViewById(R.id.btnEditInformation);
        btnDocotrsList = findViewById(R.id.btnDocotrsList);
        btnMedicalExpense = findViewById(R.id.btnMedicalExpense);
        btnPatientList = findViewById(R.id.btnPatientList);
        btnLogout=findViewById(R.id.btnLogout);

        btnPatientList.setOnClickListener(this);
        btnMedicalExpense.setOnClickListener(this);
        btnDocotrsList.setOnClickListener(this);
        btnEditInformation.setOnClickListener(this);
        btnAddNewPatient.setOnClickListener(this);
        btnPaymentBilling.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnPatientList:
                startActivity(new Intent(getApplicationContext(),ListOfPatientsSec.class));
                break;
            case R.id.btnMedicalExpense:
                startActivity(new Intent(getApplicationContext(),MedicalExpenses.class));
                break;
            case R.id.btnEditInformation:
                startActivity(new Intent(getApplicationContext(),EditPatientSecretary.class));
                break;
            case R.id.btnDocotrsList:
                startActivity(new Intent(getApplicationContext(),DoctorListActivity.class));
                break;
            case R.id.btnAddNewPatient:
                startActivity(new Intent(getApplicationContext(),AddNewPatientActivity.class));
                break;
            case R.id.btnPaymentBilling:
                startActivity(new Intent(getApplicationContext(),BillingActivity.class));
                break;
            case R.id.btnLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                break;
        }

    }
}

package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.model.NewPatient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class AddNewPatientActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    TextView textViewFileNumber, textViewDate;
    EditText editTextFirstName, editTextLastName, editTextAge, editTextAddress, editTextAptNumber, editTextCity, editTextZipCode, editTextPhoneNo, editTextEmergencyName, editTextEmergencyNumber, editTextReason;
    RadioGroup radioGroupGender;
    RadioButton radioButtonMale, radioButtonFemale, radioButtonUnspecified;
    Button btnAddNewPatient;

    Spinner spinnerBloodGroup;

    ProgressDialog progressDialog;


    final Random random = new Random();

    String fileNumber;
    String Date;
    String FN;
    String LN;
    String age;
    String Gender;
    String address;
    String aptno;
    String city;
    String zip;
    String phone;
    String blood;
    String EN;
    String EC;
    String reason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);


        textViewFileNumber = findViewById(R.id.textViewFileNumber);
        fileNumber = String.valueOf(10000 + random.nextInt(89999));
        textViewFileNumber.setText("File Number : " + fileNumber);

        textViewDate = findViewById(R.id.textViewDate);
        Date = DateFormat.getDateInstance(DateFormat.FULL).format(Calendar.getInstance().getTime());
        textViewDate.setText("Date Of Entry : " + Date);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading Patient info...");


        getSupportActionBar().setTitle("New Patient Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initiliaze();
    }


    private void initiliaze() {

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextAddress = findViewById(R.id.editTextAdress);
        editTextAptNumber = findViewById(R.id.editTextAptNumber);
        editTextCity = findViewById(R.id.editTextCity);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        editTextPhoneNo = findViewById(R.id.editTextPhoneNo);

        spinnerBloodGroup = findViewById(R.id.spinnerBloodGroup);

        editTextEmergencyName = findViewById(R.id.editTextEmergencyName);
        editTextEmergencyNumber = findViewById(R.id.editTextEmergencyNumber);
        editTextReason = findViewById(R.id.editTextReason);


        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioButtonMale);
        radioButtonFemale = findViewById(R.id.radioButtonFemale);
        radioButtonUnspecified = findViewById(R.id.radioButtonUnspecified);

        btnAddNewPatient = findViewById(R.id.btnAddNewPatient);
        btnAddNewPatient.setOnClickListener(this);


        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,
         R.array.blood_grp, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(R.layout.blood_item);
        spinnerBloodGroup.setAdapter(adapter);

        spinnerBloodGroup.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        blood = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v == btnAddNewPatient) {

            progressDialog.show();


            FN = editTextFirstName.getText().toString();
            LN = editTextLastName.getText().toString();
            age = editTextAge.getText().toString();
            address = editTextAddress.getText().toString();
            aptno = editTextAptNumber.getText().toString();
            city = editTextCity.getText().toString();
            zip = editTextZipCode.getText().toString().toUpperCase();
            phone = editTextPhoneNo.getText().toString();

            EN = editTextEmergencyName.getText().toString();
            EC = editTextEmergencyNumber.getText().toString();
            reason = editTextReason.getText().toString();

            int gender = radioGroupGender.getCheckedRadioButtonId();
            Gender = null;
            try {
                switch (gender) {
                    case R.id.radioButtonMale:
                        Gender = "Male";
                        break;
                    case R.id.radioButtonFemale:
                        Gender = "Female";
                        break;
                    case R.id.radioButtonUnspecified:
                        Gender = "UnSpecified";
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }


            if (FN.isEmpty()) {
                editTextFirstName.setError("Please Enter the First Name");
                editTextFirstName.requestFocus();
                progressDialog.dismiss();
            } else if (LN.isEmpty()) {
                editTextLastName.setError("Please Enter the Last Name");
                editTextLastName.requestFocus();
                progressDialog.dismiss();
            } else if (age.isEmpty() || Integer.valueOf(age) >= 100 || Integer.valueOf(age) <= 0) {
                editTextAge.setError("Please Enter the Age");
                editTextAge.requestFocus();
                progressDialog.dismiss();
            } else if (radioGroupGender.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Choose a Gender", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            } else if (address.isEmpty()) {
                editTextAddress.setError("Please Enter the Street Address");
                editTextAddress.requestFocus();
                progressDialog.dismiss();
            } else if (city.isEmpty()) {
                editTextCity.setError("Please Enter the City");
                editTextCity.requestFocus();
                progressDialog.dismiss();
            } else if (zip.isEmpty() || zip.length() != 6) {
                editTextZipCode.setError("Please Enter the ZipCode");
                editTextZipCode.requestFocus();
                progressDialog.dismiss();
            } else if (phone.isEmpty() || phone.length() != 10) {
                editTextPhoneNo.setError("Please Enter the Phone Number");
                editTextPhoneNo.requestFocus();
                progressDialog.dismiss();
            } else if (blood.isEmpty()||blood.matches("Select")) {
                Toast.makeText(this, "Please Choose a blood group", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            } else if (EN.isEmpty()) {
                editTextEmergencyName.setError("Please Enter the Name");
                editTextEmergencyName.requestFocus();
                progressDialog.dismiss();
            } else if (EC.isEmpty() || EC.length() != 10) {
                editTextEmergencyNumber.setError("Please Enter the Number");
                editTextEmergencyNumber.requestFocus();
                progressDialog.dismiss();
            } else if (reason.isEmpty()) {
                reason = "Not mentioned while Registration";
                AddingNewPatient();
                progressDialog.dismiss();
            } else {

                AddingNewPatient();
                progressDialog.dismiss();


            }
        }


    }

    private void AddingNewPatient() {
        NewPatient newPatient = new NewPatient(fileNumber, Date, FN,
                LN,
                age,
                Gender,
                address,
                aptno,
                city,
                zip,
                phone,
                blood,
                EN,
                EC,
                reason);


        FirebaseDatabase.getInstance().getReference("MediCare Data").child("Patients Info")
                .child(String.valueOf(fileNumber)).child("Personal Info").setValue(newPatient).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(AddNewPatientActivity.this, "Details uploaded", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddNewPatientActivity.this, "Failed to upload", Toast.LENGTH_LONG).show();

            }
        });
        ;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


    public void onBackPressed() {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);
        alertdialog.setTitle("All info will be lost");
        alertdialog.setMessage("");
        alertdialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddNewPatientActivity.super.onBackPressed();

            }
        });
        alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }

        });
        AlertDialog alert = alertdialog.create();
        alertdialog.show();
    }


}

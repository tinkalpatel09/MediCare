package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.model.Report;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class AnalysisActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    TextView textViewFileNumber,textViewFullName,textViewDate;
    EditText editTextSearch,editTextCondition,editTextReports;
    Button btnSearch,btnAddReport;
    RadioGroup radioGroupCondition;
    RadioButton radioButtonSerious,radioButtonMinor;
    LinearLayout LinearLayoutSearch,LinearLayoutData;
    String SearchText;
    String FN,LN,Datee,condition, diagnosis,severity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        getSupportActionBar().setTitle("Medical Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        initiliaze();
    }

    private void initiliaze() {

        textViewFileNumber=findViewById(R.id.textViewFileNumber);
        textViewFullName=findViewById(R.id.textViewFullName);
        textViewDate=findViewById(R.id.textViewDate);

        editTextSearch=findViewById(R.id.editTextSearch);
        editTextCondition=findViewById(R.id.editTextCondition);
        editTextReports=findViewById(R.id.editTextReports);

        btnSearch=findViewById(R.id.btnSearch);
        btnAddReport=findViewById(R.id.btnAddReport);
        btnSearch.setOnClickListener(this);
        btnAddReport.setOnClickListener(this);

        radioGroupCondition=findViewById(R.id.radioGroupCondition);
        radioButtonSerious=findViewById(R.id.radioButtonSerious);
        radioButtonMinor=findViewById(R.id.radioButtonMinor);

        LinearLayoutSearch=findViewById(R.id.LinearLayoutSearch);
        LinearLayoutData=findViewById(R.id.LinearLayoutData);

        Datee = DateFormat.getDateInstance(DateFormat.DEFAULT).format(Calendar.getInstance().getTime());

    }

    @Override
    public void onClick(View v) {
        if(v==btnSearch){

            SearchText = editTextSearch.getText().toString();

            if (SearchText.isEmpty()||SearchText.length()!=5) {
                editTextSearch.setError("Enter Valid File Number");
                btnSearch.requestFocus();
            }else{
                SearchPatient();

            }

        }else if(v==btnAddReport) {



            int s = radioGroupCondition.getCheckedRadioButtonId();
            severity = null;
            try {
                switch (s) {
                    case R.id.radioButtonSerious:
                        severity = "Serious";
                        break;
                    case R.id.radioButtonMinor:
                        severity = "Minor";
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }

            condition = editTextCondition.getText().toString();
            diagnosis = editTextReports.getText().toString();


            if (condition.isEmpty()) {
                editTextCondition.setError("Enter the Medicine Condition of Patient");
                editTextCondition.requestFocus();
            }else if (diagnosis.isEmpty()) {
                editTextReports.setError("Please Enter the Diagnosis Details");
                editTextReports.requestFocus();

            }else if (radioGroupCondition.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this,"Choose a option of severity",Toast.LENGTH_LONG).show();
            }else{

                uploadReport();
            }


        }
        }



    private void SearchPatient() {
        DatabaseReference FileNumber = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(SearchText));

        FileNumber.addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()) {

            FN = dataSnapshot.child("Personal Info").child("firstName").getValue().toString();
            LN = dataSnapshot.child("Personal Info").child("lastName").getValue().toString();

            textViewFileNumber.setText("File Number : " + SearchText);
            textViewFullName.setText("Patient Name : " + FN + " " + LN);
            textViewDate.setText("Date Of Report : "+Datee);

            try {
                Datee=dataSnapshot.child("Medical Condition").child("date").getValue().toString();
                condition=dataSnapshot.child("Medical Condition").child("condition").getValue().toString();
                diagnosis=dataSnapshot.child("Medical Condition").child("diagnosis").getValue().toString();

                textViewDate.setText("Date Of Report : "+Datee);
                editTextCondition.setText(condition);
                editTextReports.setText(diagnosis);
            } catch (Exception e) {
                e.printStackTrace();
            }


            LinearLayoutSearch.setVisibility(View.GONE);
            LinearLayoutData.setVisibility(View.VISIBLE);
        }else {
            editTextSearch.setError("File Number doesn't exist");
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    private void uploadReport() {

        Report report = new Report(Datee,condition,diagnosis,severity);

        FirebaseDatabase.getInstance().getReference("MediCare Data").child("Patients Info")
                .child(String.valueOf(SearchText)).child("Medical Condition").setValue(report).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(AnalysisActivity.this,"Reports uploaded",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AnalysisActivity.this,"Failed to upload",Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

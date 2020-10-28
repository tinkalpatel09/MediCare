package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.model.MedicalExpense;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MedicalExpenses extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    TextView textViewFileNumber,textViewTaxes,textViewFullName,textViewTotal,textViewPaymentLeft;
    EditText editTextSearch,editTextConsultationCharges,editTextTestCharges,editTextReportCharges,editTextOtherCharges,editTextPharmacyCharges;
    Button btnSearch,btnUpdateMedicalExpenses,btnCalculateTotal;
    LinearLayout LinearLayoutSearch,LinearLayoutData;

    String SearchText;
    String FN,LN ;
    String consultationCost,testCost,pharmacyCost,reportCost,otherCost,tax,total,
            paymentPayed,PaymentLeft,modeOfPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_expenses);

        getSupportActionBar().setTitle("Medical Expenses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initiliaze();
    }

    private void initiliaze() {
        textViewFileNumber=findViewById(R.id.textViewFileNumber);
        textViewTaxes=findViewById(R.id.textViewTaxes);
        textViewFullName=findViewById(R.id.textViewFullName);
        textViewTotal=findViewById(R.id.textViewTotal);
        textViewPaymentLeft=findViewById(R.id.textViewPaymentLeft);

        editTextConsultationCharges=findViewById(R.id.editTextConsultationCharges);
        editTextTestCharges=findViewById(R.id.editTextTestCharges);
        editTextReportCharges=findViewById(R.id.editTextReportCharges);
        editTextOtherCharges=findViewById(R.id.editTextOtherCharges);
        editTextPharmacyCharges=findViewById(R.id.editTextPharmacyCharges);

        editTextSearch=findViewById(R.id.editTextSearch);
        btnSearch=findViewById(R.id.btnSearch);


        btnUpdateMedicalExpenses=findViewById(R.id.btnUpdateMedicalExpenses);
        btnCalculateTotal=findViewById(R.id.btnCalculateTotal);

        LinearLayoutSearch=findViewById(R.id.LinearLayoutSearch);
        LinearLayoutData=findViewById(R.id.LinearLayoutData);


        btnCalculateTotal.setOnClickListener(this);
        btnUpdateMedicalExpenses.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
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

        }else if(v==btnCalculateTotal){

            consultationCost=editTextConsultationCharges.getText().toString();
            testCost=editTextTestCharges.getText().toString();
            pharmacyCost=editTextPharmacyCharges.getText().toString();
            reportCost=editTextReportCharges.getText().toString();
            otherCost=editTextOtherCharges.getText().toString();

            if (consultationCost.isEmpty()) {
                editTextConsultationCharges.setError("Please Enter the Consultation Charges");
                editTextConsultationCharges.requestFocus();
            }else {

                if (testCost.isEmpty()) {
                    editTextTestCharges.setText("0");
                    testCost = "0";
                }
                if (pharmacyCost.isEmpty()) {
                    editTextPharmacyCharges.setText("0");
                    pharmacyCost = "0";
                }
                if (reportCost.isEmpty()) {
                    editTextReportCharges.setText("0");
                    reportCost = "0";
                }
                if (otherCost.isEmpty()) {
                    editTextOtherCharges.setText("0");
                    otherCost = "0";
                }

                tax=String.valueOf(0.15*(Double.valueOf(consultationCost)+Double.valueOf(testCost)+
                        Double.valueOf(pharmacyCost)+Double.valueOf(reportCost)+Double.valueOf(otherCost)));
                total=String.valueOf(Double.valueOf(consultationCost)+Double.valueOf(testCost)+
                        Double.valueOf(pharmacyCost)+Double.valueOf(reportCost)+Double.valueOf(otherCost)+Double.valueOf(tax));

                PaymentLeft=String.valueOf(Double.valueOf(total)-Double.valueOf(paymentPayed));

                textViewTaxes.setText("Total Taxes(QST+PST) : "+tax);
                textViewTotal.setText("Total Charges(inc. Taxes) : "+total);
                textViewPaymentLeft.setText("Payment Left : "+PaymentLeft);

                btnCalculateTotal.setVisibility(View.GONE);
                btnUpdateMedicalExpenses.setVisibility(View.VISIBLE);
            }

        }else if(v==btnUpdateMedicalExpenses){

                uploadMedicalExpense();

            }

        }


    private void SearchPatient() {
        DatabaseReference FileNumber = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(SearchText));

        FileNumber.addValueEventListener(this);
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){

            FN=dataSnapshot.child("Personal Info").child("firstName").getValue().toString();
            LN=dataSnapshot.child("Personal Info").child("lastName").getValue().toString();



            try {
                consultationCost=dataSnapshot.child("Medical Expenses Info").child("consultationCost").getValue().toString();
                testCost=dataSnapshot.child("Medical Expenses Info").child("testCost").getValue().toString();
                pharmacyCost=dataSnapshot.child("Medical Expenses Info").child("pharmacyCost").getValue().toString();
                reportCost=dataSnapshot.child("Medical Expenses Info").child("reportCost").getValue().toString();
                otherCost=dataSnapshot.child("Medical Expenses Info").child("otherCost").getValue().toString();
                paymentPayed=dataSnapshot.child("Medical Expenses Info").child("paymentPayed").getValue().toString();
                modeOfPayment=dataSnapshot.child("Medical Expenses Info").child("modeOfPayment").getValue().toString();

                editTextConsultationCharges.setText(consultationCost);
                editTextTestCharges.setText(testCost);
                editTextReportCharges.setText(pharmacyCost);
                editTextOtherCharges.setText(reportCost);
                editTextPharmacyCharges.setText(otherCost);


            } catch (Exception e) {

                paymentPayed=String.valueOf(Double.valueOf("0"));
                modeOfPayment="No payment made yet";
            }

            textViewFileNumber.setText("File Number : "+SearchText);
            textViewFullName.setText("Patient Name : "+FN+" "+LN);
            LinearLayoutSearch.setVisibility(View.GONE);
            LinearLayoutData.setVisibility(View.VISIBLE);

        }else {
            editTextSearch.setError("File Number doesn't exist");
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }






    private void uploadMedicalExpense() {

        MedicalExpense medicalExpense = new MedicalExpense(consultationCost,testCost,pharmacyCost,reportCost,otherCost,
                tax,total,paymentPayed,PaymentLeft,modeOfPayment);

        FirebaseDatabase.getInstance().getReference("MediCare Data").child("Patients Info")
                .child(String.valueOf(SearchText)).child("Medical Expenses Info").setValue(medicalExpense).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MedicalExpenses.this,"Expenses uploaded",Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MedicalExpenses.this,"Failed to upload",Toast.LENGTH_LONG).show();

            }
        });
    }





    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }




}

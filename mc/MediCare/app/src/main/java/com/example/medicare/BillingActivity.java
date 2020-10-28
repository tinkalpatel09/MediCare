package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

import com.example.medicare.model.MedicalExpense;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Method;

public class BillingActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    TextView textViewFileNumber,textViewFullName,textViewConsultationCost,textViewTestCost,textViewPharmacyCost,
            textViewReportCost,textViewOtherCost,textViewTax,textViewTotalCost,textViewMissingData;

    EditText editTextSearch,editTextPaymentPayed;
    
    Button btnSearch,btnGenrateBill;
    
    RadioGroup radioGroupMethod;
    RadioButton radioButtonDC,radioButtonCC,radioButtonCash,radioButtonETransfer;
    LinearLayout LinearLayoutSearch,LinearLayoutData,LinearLayoutDataMissing;

    String SearchText;
    String FN,LN,Method ;
    String consultationCost,testCost,pharmacyCost,reportCost,otherCost,tax,total,PaymentPayed,PaymentLeft,ModeOfPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);


        getSupportActionBar().setTitle("Billing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        initiliaze();
    }

    private void initiliaze() {

        textViewFileNumber=findViewById(R.id.textViewFileNumber);
        textViewFullName=findViewById(R.id.textViewFullName);
        textViewConsultationCost=findViewById(R.id.textViewConsultationCost);
        textViewTestCost=findViewById(R.id.textViewTestCost);
        textViewPharmacyCost=findViewById(R.id.textViewPharmacyCost);
        textViewReportCost=findViewById(R.id.textViewReportCost);
        textViewOtherCost=findViewById(R.id.textViewOtherCost);
        textViewTax=findViewById(R.id.textViewTax);
        textViewTotalCost=findViewById(R.id.textViewTotalCost);
        textViewMissingData=findViewById(R.id.textViewMissingData);


        editTextSearch=findViewById(R.id.editTextSearch);
        editTextPaymentPayed=findViewById(R.id.editTextPaymentPayed);


        btnSearch=findViewById(R.id.btnSearch);
        btnGenrateBill=findViewById(R.id.btnGenrateBill);

        radioGroupMethod=findViewById(R.id.radioGroupMethod);
        radioButtonDC=findViewById(R.id.radioButtonDC);
        radioButtonCC=findViewById(R.id.radioButtonCC);
        radioButtonCash=findViewById(R.id.radioButtonCash);
        radioButtonETransfer=findViewById(R.id.radioButtonETransfer);

        LinearLayoutSearch=findViewById(R.id.LinearLayoutSearch);
        LinearLayoutData=findViewById(R.id.LinearLayoutData);
        LinearLayoutDataMissing=findViewById(R.id.LinearLayoutDataMissing);

        btnSearch.setOnClickListener(this);
        btnGenrateBill.setOnClickListener(this);
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

        }else if(v==btnGenrateBill){

            PaymentPayed=editTextPaymentPayed.getText().toString();

            int method = radioGroupMethod.getCheckedRadioButtonId();
            ModeOfPayment = null;
            try {
                switch (method) {
                    case R.id.radioButtonCash:
                        ModeOfPayment = "Cash";
                        break;
                    case R.id.radioButtonCC:
                        ModeOfPayment = "Credit Card";
                        break;
                    case R.id.radioButtonDC:
                        ModeOfPayment = "Debit Card";
                        break;
                    case R.id.radioButtonETransfer:
                        ModeOfPayment = "E-Transfer";
                        break;
                    default:
                        break;
                }
            }catch (NumberFormatException e) {
                Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
            }






            if (PaymentPayed.isEmpty()) {
                editTextPaymentPayed.setError("Please Enter the Payment Payed");
                editTextPaymentPayed.requestFocus();
            }else if (radioGroupMethod.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this,"Choose a Payment Method",Toast.LENGTH_LONG).show();
            }else{
                PaymentLeft=String.valueOf(Double.valueOf(total)-Double.valueOf(PaymentPayed));
                Popup();
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
            textViewFileNumber.setText("File Number : "+SearchText);
            textViewFullName.setText("Patient Name : "+FN+" "+LN);

            LinearLayoutSearch.setVisibility(View.GONE);
            LinearLayoutDataMissing.setVisibility(View.GONE);
            LinearLayoutData.setVisibility(View.VISIBLE);


            try{
                consultationCost = dataSnapshot.child("Medical Expenses Info").child("consultationCost").getValue().toString();
                testCost = dataSnapshot.child("Medical Expenses Info").child("testCost").getValue().toString();
                pharmacyCost = dataSnapshot.child("Medical Expenses Info").child("pharmacyCost").getValue().toString();
                reportCost = dataSnapshot.child("Medical Expenses Info").child("reportCost").getValue().toString();
                otherCost = dataSnapshot.child("Medical Expenses Info").child("otherCost").getValue().toString();
                tax = dataSnapshot.child("Medical Expenses Info").child("tax").getValue().toString();
                total = dataSnapshot.child("Medical Expenses Info").child("totalCost").getValue().toString();
                PaymentPayed=dataSnapshot.child("Medical Expenses Info").child("paymentPayed").getValue().toString();

                textViewConsultationCost.setText("Consultation Charges : $"+consultationCost);
                textViewTestCost.setText("Test Charges : $"+testCost);
                textViewPharmacyCost.setText("Pharmacy charges : $"+pharmacyCost);
                textViewReportCost.setText("Report Charges : $"+reportCost);
                textViewOtherCost.setText("Other Charges : $"+otherCost);
                textViewTax.setText("Taxes : $"+tax);
                textViewTotalCost.setText("Total Expenses : $"+total);
                editTextPaymentPayed.setText(PaymentPayed);

            }catch (Exception e){
                LinearLayoutSearch.setVisibility(View.GONE);
                LinearLayoutData.setVisibility(View.GONE);
                LinearLayoutDataMissing.setVisibility(View.VISIBLE);

                textViewMissingData.setText("Expenses Not uploaded yet. Please Try back again !!");
               // Toast.makeText(this,"",Toast.LENGTH_LONG).show();
            }





        }else{
            editTextSearch.setError("File Number doesn't exist");
        }
        }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }



    private void Popup() {


        final AlertDialog.Builder alert = new AlertDialog.Builder(BillingActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.custom_dialog,null);


        final TextView textViewFileNumberDia=(TextView)mView.findViewById(R.id.textViewFileNumberDia);
        final TextView textViewTotalDia=(TextView)mView.findViewById(R.id.textViewTotalDia);
        final TextView textViewPaymentPaidDia=(TextView)mView.findViewById(R.id.textViewPaymentPaidDia);
        final TextView textViewModeOfPaymentDia=(TextView)mView.findViewById(R.id.textViewModeOfPaymentDia);
        final TextView textViewPaymentLeftDia=(TextView)mView.findViewById(R.id.textViewPaymentLeftDia);



        textViewFileNumberDia.setText("File Number : "+SearchText);
        textViewTotalDia.setText("Total Payment Due : "+total);
        textViewPaymentPaidDia.setText("Payment Payed : "+PaymentPayed);
        textViewModeOfPaymentDia.setText("Mode Of Payment : "+ModeOfPayment);
        textViewPaymentLeftDia.setText("Payment Left : "+PaymentLeft);



        Button btnConfirm =(Button)mView.findViewById(R.id.btnConfirmDia);

        alert.setView(mView);
        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);




        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MedicalExpense medicalExpense = new MedicalExpense(consultationCost,testCost,pharmacyCost,reportCost,otherCost,tax,total,
                        PaymentPayed,PaymentLeft,ModeOfPayment);

                FirebaseDatabase.getInstance().getReference("MediCare Data").child("Patients Info")
                        .child(String.valueOf(SearchText)).child("Medical Expenses Info").setValue(medicalExpense).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(BillingActivity.this,"Payment Received",Toast.LENGTH_LONG).show();
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BillingActivity.this,"Failed !!",Toast.LENGTH_LONG).show();

                    }
                });;


            }
        });
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}

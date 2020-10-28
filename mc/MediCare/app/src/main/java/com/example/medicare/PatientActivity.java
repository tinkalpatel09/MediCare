package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.medicare.model.Medicine;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {


    EditText editTextSearch;
    Button btnSearch,btnChat;
    LinearLayout LinearLayoutSearch, LinearLayoutData;
    RecyclerView recyclerViewMedicine;

    TextView textViewFileNumber, textViewFullName, textViewDate, textViewAge, textViewGender, textViewBloodGroup,
            textViewAddress, textViewCity, textViewPhone, textViewEC, textViewEN, textViewReason;

    TextView textViewDatee, textViewCondition, textViewDiagonisis, textViewSeverity;

    TextView textViewConsultationCost, textViewTestCost, textViewPharmacyCost, textViewReportCost, textViewOtherCost,
            textViewTax, textViewTotalCost, textViewPaymentPayed, textViewPaymentLeft;

    TextView textViewMedicineError,textViewNoti;


    String SearchText, Date, FN, LN, age, gender, address, aptno, city, zip, phone, blood, EN, EC, reason;

    String Datee, condition, diagnosis, severity;

    String consultationCost, testCost, pharmacyCost, reportCost, otherCost, tax, total, PaymentPayed, PaymentLeft;

    List<Medicine> myMedicineList;
    Medicine mmedicine;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);


        getSupportActionBar().setTitle("Patient Detailed Info");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initiliaze();


    }

    private void initiliaze() {

        LinearLayoutSearch = findViewById(R.id.LinearLayoutSearch);
        LinearLayoutData = findViewById(R.id.LinearLayoutData);

        editTextSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        btnChat=findViewById(R.id.btnChat);
        btnChat.setOnClickListener(this);

        recyclerViewMedicine = findViewById(R.id.recycleView);
        textViewMedicineError=findViewById(R.id.textViewMedicineError);

        textViewFileNumber = findViewById(R.id.textViewFileNumber);
        textViewFullName = findViewById(R.id.textViewFullName);
        textViewDate = findViewById(R.id.textViewDate);
        textViewAge = findViewById(R.id.textViewAge);
        textViewGender = findViewById(R.id.textViewGender);
        textViewBloodGroup = findViewById(R.id.textViewBloodGroup);
        textViewAddress = findViewById(R.id.textViewAddress);
        textViewCity = findViewById(R.id.textViewCity);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewEC = findViewById(R.id.textViewEC);
        textViewEN = findViewById(R.id.textViewEN);
        textViewReason = findViewById(R.id.textViewReason);


        textViewDatee = findViewById(R.id.textViewDatee);
        textViewCondition = findViewById(R.id.textViewCondition);
        textViewDiagonisis = findViewById(R.id.textViewDiagonisis);
        textViewSeverity = findViewById(R.id.textViewSeverity);

        textViewConsultationCost = findViewById(R.id.textViewConsultationCost);
        textViewTestCost = findViewById(R.id.textViewTestCost);
        textViewPharmacyCost = findViewById(R.id.textViewPharmacyCost);
        textViewReportCost = findViewById(R.id.textViewReportCost);
        textViewOtherCost = findViewById(R.id.textViewOtherCost);
        textViewTax = findViewById(R.id.textViewTax);
        textViewTotalCost = findViewById(R.id.textViewTotalCost);
        textViewPaymentPayed = findViewById(R.id.textViewPaymentPayed);
        textViewPaymentLeft = findViewById(R.id.textViewPaymentLeft);

        textViewNoti=findViewById(R.id.textViewNoti);


    }


    @Override
    public void onClick(View v) {
        if (v == btnSearch) {
            SearchText = editTextSearch.getText().toString();

            if (SearchText.isEmpty() || SearchText.length() != 5) {
                editTextSearch.setError("Enter Valid File Number");
                btnSearch.requestFocus();
            } else {
                SearchPatient();
            }
        }else if(v==btnChat){
            Intent i=new Intent(this,PatientChatActivity.class);
            i.putExtra("fileNumber",SearchText);
            startActivity(i);
        }
    }

    private void SearchPatient() {

        DatabaseReference FileNumber = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(SearchText));

        FileNumber.addValueEventListener(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            LinearLayoutSearch.setVisibility(View.GONE);
            LinearLayoutData.setVisibility(View.VISIBLE);


            try {
                String DM=dataSnapshot.child("Chats").child("DM").getValue().toString();
                if(DM.matches("1")){
                    textViewNoti.setText("You have a new Message from doctor");
                    Toast.makeText(this,"You have a new message from doctor",Toast.LENGTH_LONG).show();
                }else {
                    textViewNoti.setText("No new Message from doctor");
                    textViewNoti.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            } catch (Exception e) {
                textViewNoti.setText("No new Message from doctor");
                textViewNoti.setTextColor(getResources().getColor(R.color.colorAccent));
            }


            try {
                Date = dataSnapshot.child("Personal Info").child("date").getValue().toString();
                FN = dataSnapshot.child("Personal Info").child("firstName").getValue().toString();
                LN = dataSnapshot.child("Personal Info").child("lastName").getValue().toString();
                age = dataSnapshot.child("Personal Info").child("age").getValue().toString();
                gender = dataSnapshot.child("Personal Info").child("gender").getValue().toString();
                blood = dataSnapshot.child("Personal Info").child("bloodGroup").getValue().toString();

                address = dataSnapshot.child("Personal Info").child("streetAddress").getValue().toString();
                aptno = dataSnapshot.child("Personal Info").child("aptNumber").getValue().toString();
                city = dataSnapshot.child("Personal Info").child("city").getValue().toString();
                zip = dataSnapshot.child("Personal Info").child("zipCode").getValue().toString();
                phone = dataSnapshot.child("Personal Info").child("phoneNumber").getValue().toString();
                EN = dataSnapshot.child("Personal Info").child("emergencyContactName").getValue().toString();
                EC = dataSnapshot.child("Personal Info").child("emergencyContactNumber").getValue().toString();
                reason = dataSnapshot.child("Personal Info").child("reason").getValue().toString();


                textViewFileNumber.setText("File Number : " + SearchText);
                textViewFullName.setText("Full Name : " + FN + " " + LN);
                textViewDate.setText("Date of Registration : " + Date);
                textViewAge.setText("Age : " + age);
                textViewGender.setText("Gender : " + gender);
                textViewBloodGroup.setText("Blood Group : " + blood);
                textViewAddress.setText("Address : " + address + " apt: " + aptno + " " + zip);
                textViewCity.setText("City : " + city);
                textViewPhone.setText("Phone Number : " + phone);
                textViewEC.setText("Emergency Contact Name : " + EN);
                textViewEN.setText("Emergency Contact Number : " + EC);
                textViewReason.setText("Reason mentioned while registration : " + reason);


            } catch (Exception e) {
                e.printStackTrace();
            }


            try {
                Datee = dataSnapshot.child("Medical Condition").child("date").getValue().toString();
                condition = dataSnapshot.child("Medical Condition").child("condition").getValue().toString();
                diagnosis = dataSnapshot.child("Medical Condition").child("diagnosis").getValue().toString();
                severity = dataSnapshot.child("Medical Condition").child("severity").getValue().toString();

                textViewDatee.setText("Date of report : " + Datee);
                textViewCondition.setText("Condition : " + condition);
                textViewDiagonisis.setText("Diagnosis Reports : " + diagnosis);
                textViewSeverity.setText("Severity of Health Conditon : " + severity);

            } catch (Exception e) {
                textViewDatee.setText("Reports are not been uploaded yet");
            }


            try {
                consultationCost = dataSnapshot.child("Medical Expenses Info").child("consultationCost").getValue().toString();
                testCost = dataSnapshot.child("Medical Expenses Info").child("testCost").getValue().toString();
                pharmacyCost = dataSnapshot.child("Medical Expenses Info").child("pharmacyCost").getValue().toString();
                reportCost = dataSnapshot.child("Medical Expenses Info").child("reportCost").getValue().toString();
                otherCost = dataSnapshot.child("Medical Expenses Info").child("otherCost").getValue().toString();
                tax = dataSnapshot.child("Medical Expenses Info").child("tax").getValue().toString();
                total = dataSnapshot.child("Medical Expenses Info").child("totalCost").getValue().toString();
                PaymentPayed = dataSnapshot.child("Medical Expenses Info").child("paymentPayed").getValue().toString();
                PaymentLeft = dataSnapshot.child("Medical Expenses Info").child("paymentLeft").getValue().toString();

                textViewConsultationCost.setText("Consultation Charges : $" + consultationCost);
                textViewTestCost.setText("Test Charges : $" + testCost);
                textViewPharmacyCost.setText("Pharmacy charges : $" + pharmacyCost);
                textViewReportCost.setText("Report Charges : $" + reportCost);
                textViewOtherCost.setText("Other Charges : $" + otherCost);
                textViewTax.setText("Taxes : $" + tax);
                textViewTotalCost.setText("Total Expenses : $" + total);
                textViewPaymentPayed.setText("Payment Payed : $" + PaymentPayed);
                textViewPaymentLeft.setText("Payment yet to be payed : $" + PaymentLeft);
            } catch (Exception e) {
                textViewConsultationCost.setText("Medical expenses are not been uploaded yet");
            }




            try {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(PatientActivity.this,1);
                recyclerViewMedicine.setLayoutManager(gridLayoutManager);
                myMedicineList = new ArrayList<>();

                final MedicineAdapter medicineAdapter = new MedicineAdapter(PatientActivity.this,myMedicineList);
                recyclerViewMedicine.setAdapter(medicineAdapter);


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                         child("Patients Info").child(String.valueOf(SearchText)).child("Medicine Details");

                ValueEventListener valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        myMedicineList.clear();
                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {

                            Medicine medicine= itemSnapshot.getValue(Medicine.class);
                            myMedicineList.add(medicine);
                        }
                        medicineAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            } catch (Exception e) {

                textViewMedicineError.setText("Medicines are not been uploaded yet");
                recyclerViewMedicine.setVisibility(View.GONE);
            }


        } else {
            editTextSearch.setError("File Number doesn't exist");
        }
    }



    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }










/*
   @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
            if(LinearLayout.VISIBLE==1){

            }else {
                finish();
            }
        return super.onOptionsItemSelected(item);
    }*/

}

package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.medicare.model.Medicine;
import com.example.medicare.model.NewPatient;
import com.example.medicare.model.Register;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity {


    RecyclerView recycleView;
    List<Register> list;
    Register register;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recycleView=findViewById(R.id.recycleView);

        getSupportActionBar().setTitle("List Of Doctors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        GridLayoutManager gridLayoutManager = new GridLayoutManager(DoctorListActivity.this,1);
        recycleView.setLayoutManager(gridLayoutManager);

        list = new ArrayList<>();

        final DoctorAdapter doctorAdapter = new DoctorAdapter(DoctorListActivity.this,list);
        recycleView.setAdapter(doctorAdapter);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Doctor Info");

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {
                    Register register = itemsnapshot.getValue(Register.class);
                    list.add(register);
                }
                doctorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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

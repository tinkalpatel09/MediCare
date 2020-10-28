package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.medicare.model.NewPatient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListChatActivity extends AppCompatActivity {

    RecyclerView recycleView;
    List<NewPatient> patientList;
    NewPatient newPatient;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chat);

        getSupportActionBar().setTitle("Chat List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        intiliaze();
    }

    private void intiliaze() {
        recycleView=findViewById(R.id.recycleView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListChatActivity.this,1);
        recycleView.setLayoutManager(gridLayoutManager);

        patientList = new ArrayList<>();

        final ChatAdapter myAdapter = new ChatAdapter(ListChatActivity.this,patientList);
        recycleView.setAdapter(myAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("MediCare Data").child("Patients Info");

        valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientList.clear();
                for(DataSnapshot itemsnapshot: dataSnapshot.getChildren()){
                    NewPatient newPatient = itemsnapshot.child("Personal Info").getValue(NewPatient.class);
                    patientList.add(newPatient);
                }
                myAdapter.notifyDataSetChanged();

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




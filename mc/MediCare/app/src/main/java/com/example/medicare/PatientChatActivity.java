package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.medicare.model.NewPatient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PatientChatActivity extends AppCompatActivity implements View.OnClickListener {

    ListView ListViewPatient, ListViewDoctor;
    EditText editTextMessages;
    Button buttonSent;
    String fileNumber;

    DatabaseReference PChat, DChat;

    List Plist = new ArrayList<String>();
    ArrayAdapter<String> ParrayAdapter;

    List Dlist = new ArrayList<String>();
    ArrayAdapter<String> DarrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_chat);

        getSupportActionBar().setTitle("Emergency Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        initiliaze();
    }

    private void initiliaze() {

        fileNumber = getIntent().getExtras().getString("fileNumber");


        ListViewPatient = findViewById(R.id.ListViewPatient);
        ListViewDoctor = findViewById(R.id.ListViewDoctor);

        editTextMessages = findViewById(R.id.editTextMessages);
        buttonSent = findViewById(R.id.buttonSent);
        buttonSent.setOnClickListener(this);

        /*String Empty = "Your Messages will be displayed here";
        String Empty1 = "Doctor's Messages will be displayed here";*/


        ParrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.chat_list, Plist);
        DarrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.chat_list_1, Dlist);

        PChat = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(fileNumber)).child("Chats").child("PatientChat");

        Plist.clear();
        PChat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {

                        String text = itemsnapshot.getValue().toString();
                        Plist.add(text);
                    }
                    ListViewPatient.setAdapter(ParrayAdapter);
                    ParrayAdapter.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        DChat = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(fileNumber)).child("Chats").child("DoctorChat");

        Dlist.clear();
        DChat.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {

                        String text = itemsnapshot.getValue().toString();
                        Dlist.add(text);
                    }
                    ListViewDoctor.setAdapter(DarrayAdapter);
                    DarrayAdapter.notifyDataSetChanged();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(fileNumber)).child("Chats").child("DM").setValue("0");


    }


    @Override
    public void onClick(View v) {
        if (v == buttonSent) {
            if (editTextMessages.getText().toString().isEmpty()) {
                editTextMessages.requestFocus();
            } else {
                PChat.push().setValue(editTextMessages.getText().toString());
                FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                        child("Patients Info").child(String.valueOf(fileNumber)).child("Chats").child("PM").setValue("1");
                editTextMessages.setText(null);
                ListViewPatient.setTranscriptMode(-1);
                Plist.clear();

            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

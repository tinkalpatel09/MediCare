package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.model.Medicine;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

TextView TextViewNotRegistered,textheading,TextViewForget;
    EditText editTextEmail,editTextPassword;
    Button buttonLogin;
    String savedtype,type,userID,email,password,fullName;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        textheading=findViewById(R.id.textheading);

        Bundle mBundle=getIntent().getExtras();
        if(mBundle!=null) {
           type = mBundle.getString("type");

        }

        textheading.setText(type+" Login");

        TextViewNotRegistered=findViewById(R.id.TextViewNotRegistered);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        TextViewForget=findViewById(R.id.TextViewForget);

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        TextViewNotRegistered.setOnClickListener(this);
        TextViewForget.setOnClickListener(this);
        fAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        if(v==buttonLogin){
            email=editTextEmail.getText().toString();
            password=editTextPassword.getText().toString();


            if(email.isEmpty()){
                editTextEmail.setError("Please Enter the Email Adress");
                editTextEmail.requestFocus();
            }else if(password.isEmpty()){
                editTextPassword.setError("Please Enter the valid Password");
                editTextPassword.requestFocus();
            }else {
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userID = fAuth.getCurrentUser().getUid();

                            if (type.matches("Doctor")) {

                                DatabaseReference FileNumber = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                                        child("Doctor Info").child(userID);

                                FileNumber.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            savedtype=dataSnapshot.child("register").getValue().toString();
                                            fullName=dataSnapshot.child("fullName").getValue().toString();
                                            if (type.matches(savedtype)){
                                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                                                Intent i =new Intent(LoginActivity.this,DoctorActivity.class);
                                                i.putExtra("FullName",fullName);
                                                startActivity(i);
                                                finish();
                                            }else {
                                                editTextEmail.setText(null);
                                                editTextPassword.setText(null);
                                                Toast.makeText(LoginActivity.this,"Secretary credentials can't be used",Toast.LENGTH_LONG).show();
                                            }
                                        }else{

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });




                            }else if (type.matches("Secretary")) {

                                DatabaseReference FileNumber = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                                        child("Secretary Info").child(userID);
                                FileNumber.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.exists()){
                                            savedtype=dataSnapshot.child("register").getValue().toString();
                                            fullName=dataSnapshot.child("fullName").getValue().toString();
                                            if (type.matches(savedtype)){
                                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                                                Intent i =new Intent(LoginActivity.this,SecretaryActivity.class);
                                                i.putExtra("FullName",fullName);
                                                startActivity(i);
                                                finish();
                                            }else {
                                                editTextEmail.setText(null);
                                                editTextPassword.setText(null);
                                                Toast.makeText(LoginActivity.this,"Doctor credentials can't be used",Toast.LENGTH_LONG).show();
                                            }
                                        }else{

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }







                        }else {
                            Toast.makeText(LoginActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }



        }else if(v==TextViewNotRegistered) {
            startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            finish();
        }else if(v==TextViewForget){



            final AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.forget_password_dialoge,null);


            final EditText editTextemaildia=(EditText) mView.findViewById(R.id.editTextemaildia);







            Button btnConfirm =(Button)mView.findViewById(R.id.btnReset);

            alert.setView(mView);
            final AlertDialog alertDialog = alert.create();
            alertDialog.setCanceledOnTouchOutside(false);




            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mail=editTextemaildia.getText().toString();

                    fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(LoginActivity.this, "Reset Link Sent To Your Email.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Error ! Reset Link is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
            alertDialog.show();


        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }


}

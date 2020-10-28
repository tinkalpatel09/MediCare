package com.example.medicare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicare.model.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    TextView TextViewAlreadyRegistered;
    EditText editTextFullName,editTextEmail,editTextPhone,editTextPassword,editTextPasswordConfirm;
    RadioGroup radioGroup_Register;
    RadioButton radioButtonDoctor, radioButtonSecretary;
    Button buttonRegister;
    FirebaseAuth fAuth;
    String userID,fullName,email,phone,password,PasswordConfirm,Type;
    Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





        TextViewAlreadyRegistered= findViewById(R.id.TextViewAlreadyRegistered);

        editTextFullName= findViewById(R.id.editTextFullName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPasswordConfirm = findViewById(R.id.editTextPasswordConfirm);

        radioGroup_Register = findViewById(R.id.radioGroup_Register);
        radioButtonDoctor = findViewById(R.id.radioButtonDoctor);
        radioButtonSecretary = findViewById(R.id.radioButtonSecretary);




        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(this);
        TextViewAlreadyRegistered.setOnClickListener(this);

        fAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        if(v==buttonRegister) {

            fullName= editTextFullName.getText().toString();
            phone= editTextPhone.getText().toString();
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();
            PasswordConfirm=editTextPasswordConfirm.getText().toString();


            int t = radioGroup_Register.getCheckedRadioButtonId();
            Type = null;
            try {
                switch (t) {
                    case R.id.radioButtonDoctor:
                        Type = "Doctor";
                        break;
                    case R.id.radioButtonSecretary:
                        Type = "Secretary";
                        break;
                    default:
                        break;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }



            if (fullName.isEmpty()) {
                editTextFullName.setError("Please Enter the Full Name");
                editTextFullName.requestFocus();
            }else if(email.isEmpty()){
                editTextEmail.setError("Please Enter the Email Adress");
                editTextEmail.requestFocus();
            }else if(phone.isEmpty()||phone.length()!=10){
                editTextPhone.setError("Please Enter the Phone Number");
                editTextPhone.requestFocus();
            }else if(password.isEmpty()||password.length()<6){
                editTextPassword.setError("Please Enter the valid Password");
                editTextPassword.requestFocus();
            }else if(PasswordConfirm.isEmpty()){
                editTextPasswordConfirm.setError("Please Enter the confirmed password");
                editTextPasswordConfirm.requestFocus();
            }else if (radioGroup_Register.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this,"Choose a Type",Toast.LENGTH_LONG).show();
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                editTextEmail.setError("Please Enter the valid Email Adress");
                editTextEmail.requestFocus();
            }else if (!password.matches(PasswordConfirm)){
                editTextPasswordConfirm.setError("Passwords Doesn't Match");
                editTextPasswordConfirm.requestFocus();
            }else {


                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            userID = fAuth.getCurrentUser().getUid();
                            register=new Register(fullName,email,phone,Type);

                            if (Type.matches("Doctor")) {
                                doctor();
                            } else if (Type.matches("Secretary")) {
                                Secretary();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }else if(v==TextViewAlreadyRegistered){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }

    private void Secretary() {
        FirebaseDatabase.getInstance().getReference("MediCare Data").child("Secretary Info")
                .child(userID).setValue(register).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseUser fuser = fAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();

                    Intent i =new Intent(RegisterActivity.this,SecretaryActivity.class);
                    i.putExtra("FullName",fullName);
                    startActivity(i);
                    finish();

                }else {
                    Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void doctor() {
        FirebaseDatabase.getInstance().getReference("MediCare Data").child("Doctor Info")
                .child(userID).setValue(register).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    FirebaseUser fuser = fAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "User Created.", Toast.LENGTH_SHORT).show();

                    Intent i =new Intent(RegisterActivity.this,DoctorActivity.class);
                    i.putExtra("FullName",fullName);
                    startActivity(i);
                    finish();

                }else {
                    Toast.makeText(RegisterActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
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

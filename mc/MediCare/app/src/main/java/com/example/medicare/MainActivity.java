package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicare.model.Medicine;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textviewRegister,textviewContactUs;
    Button btnDoctor,btnSectrary,btnPatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewRegister=findViewById(R.id.textviewRegister);
        textviewContactUs=findViewById(R.id.textviewContactUs);
        btnDoctor=findViewById(R.id.btnDoctor);
        btnSectrary=findViewById(R.id.btnSectrary);
        btnPatient=findViewById(R.id.btnPatient);

        textviewRegister.setOnClickListener(this);
        textviewContactUs.setOnClickListener(this);

        btnDoctor.setOnClickListener(this);
        btnSectrary.setOnClickListener(this);
        btnPatient.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.textviewContactUs:
                startActivity(new Intent(getApplicationContext(),ContactUsActivity.class));
                break;
            case R.id.textviewRegister:
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                break;
            case R.id.btnDoctor:

                Intent i =new Intent(MainActivity.this,LoginActivity.class);
                i.putExtra("type","Doctor");
                startActivity(i);


                //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.btnSectrary:

                Intent j =new Intent(MainActivity.this,LoginActivity.class);
                j.putExtra("type","Secretary");
                startActivity(j);

                //startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.btnPatient:
                startActivity(new Intent(getApplicationContext(),PatientActivity.class));
                break;

        }
    }

   /* public void test(View view) {
        startActivity(new Intent(getApplicationContext(),DoctorListActivity.class));
    }*/


}

package com.example.medicare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.model.MedicalExpense;
import com.example.medicare.model.NewPatient;

import java.util.List;

public class MyAdapter extends  RecyclerView.Adapter<PatientViewHolder>{

    private Context mContext;
    private List<NewPatient> myPatientList;


    public MyAdapter(Context mContext, List<NewPatient> myPatientList) {
        this.mContext = mContext;
        this.myPatientList = myPatientList;
    }


    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_list_item,viewGroup,false);
        return new PatientViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientViewHolder patientViewHolder, int i) {

        patientViewHolder.textViewFileNumber.setText("File Number : "+myPatientList.get(i).getFileNumber());
        patientViewHolder.textViewPatientName.setText("Patient Name : "+myPatientList.get(i).getFirstName()+myPatientList.get(i).getLastName());


        patientViewHolder.cardViewpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,PatientDetailsActivity.class);
                intent.putExtra("file",myPatientList.get(patientViewHolder.getAdapterPosition()).getFileNumber());
                intent.putExtra("fname",myPatientList.get(patientViewHolder.getAdapterPosition()).getFirstName());
                intent.putExtra("lname",myPatientList.get(patientViewHolder.getAdapterPosition()).getLastName());
                intent.putExtra("date",myPatientList.get(patientViewHolder.getAdapterPosition()).getDate());
                intent.putExtra("age",myPatientList.get(patientViewHolder.getAdapterPosition()).getAge());
                intent.putExtra("gender",myPatientList.get(patientViewHolder.getAdapterPosition()).getGender());
                intent.putExtra("blood",myPatientList.get(patientViewHolder.getAdapterPosition()).getBloodGroup());
                intent.putExtra("address",myPatientList.get(patientViewHolder.getAdapterPosition()).getStreetAddress());
                intent.putExtra("zip",myPatientList.get(patientViewHolder.getAdapterPosition()).getZipCode());
                intent.putExtra("apt",myPatientList.get(patientViewHolder.getAdapterPosition()).getAptNumber());
                intent.putExtra("city",myPatientList.get(patientViewHolder.getAdapterPosition()).getCity());
                intent.putExtra("phone",myPatientList.get(patientViewHolder.getAdapterPosition()).getPhoneNumber());
                intent.putExtra("emergencyName",myPatientList.get(patientViewHolder.getAdapterPosition()).getEmergencyContactName());
                intent.putExtra("EmergencyNumber",myPatientList.get(patientViewHolder.getAdapterPosition()).getEmergencyContactNumber());
                intent.putExtra("reason",myPatientList.get(patientViewHolder.getAdapterPosition()).getReason());
               mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPatientList.size();
    }
}
class PatientViewHolder extends RecyclerView.ViewHolder{

    TextView textViewFileNumber,textViewPatientName;
    CardView cardViewpatient;



    public PatientViewHolder(View itemView) {
        super(itemView);

        cardViewpatient=itemView.findViewById(R.id.cardViewpatient);
        textViewFileNumber=itemView.findViewById(R.id.textViewFileNumber);
        textViewPatientName=itemView.findViewById(R.id.textViewPatientName);


    }
}


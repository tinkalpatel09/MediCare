package com.example.medicare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.model.NewPatient;
import com.example.medicare.model.Register;

import java.util.List;

public class DoctorAdapter extends  RecyclerView.Adapter<DoctorViewHolder>{

    private Context mContext;
    private List<Register> myDoctorList;

    public DoctorAdapter(Context mContext, List<Register> myDoctorList) {
        this.mContext = mContext;
        this.myDoctorList = myDoctorList;
    }

    @Override
    public DoctorViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,parent,false);
        return new DoctorViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {

        holder.textViewFName.setText("Name : Dr. "+myDoctorList.get(position).getFullName());
        holder.textViewphone.setText("+1-"+myDoctorList.get(position).getPhone());
        holder.textViewEmail.setText(myDoctorList.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return myDoctorList.size();
    }
}

class DoctorViewHolder extends RecyclerView.ViewHolder{

    TextView textViewFName,textViewphone,textViewEmail;
    CardView cardViewDoctor;



    public DoctorViewHolder(View itemView) {
        super(itemView);

        textViewFName=itemView.findViewById(R.id.textViewFName);
        textViewphone=itemView.findViewById(R.id.textViewphone);
        textViewEmail=itemView.findViewById(R.id.textViewEmail);
        cardViewDoctor=itemView.findViewById(R.id.cardViewDoctor);


    }
}

package com.example.medicare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicare.model.NewPatient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private Context mContext;
    private List<NewPatient> myPatientList;


    public ChatAdapter(Context mContext, List<NewPatient> myPatientList) {
        this.mContext = mContext;
        this.myPatientList = myPatientList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);


        return new ChatViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatViewHolder holder, int position) {



        holder.textViewFileNumber.setText("File Number : " + myPatientList.get(position).getFileNumber());
        holder.textViewPatientName.setText("Patient Name : " + myPatientList.get(position).getFirstName() + " " + myPatientList.get(position).getLastName());
        holder.textViewDate.setText("Date of Registeration : " + myPatientList.get(position).getDate());


/*


        try {
            DatabaseReference msgvalue = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                    child("Patients Info").child(String.valueOf(myPatientList.get(position).getFileNumber())).
                    child("Chats").child("PM");

            String PM =msgvalue.toString();

            if (PM == "1") {
                message="There is a new message from this patient";
                holder.textViewMsg.setTextColor(mContext.getResources().getColor(R.color.btncolor));
            } else {
                message="No new message from this patient";
            }
        } catch (Exception e) {
            message="No new message from this patient";
        }

*/


        DatabaseReference msgvalue = FirebaseDatabase.getInstance().getReference().child("MediCare Data").
                child("Patients Info").child(String.valueOf(myPatientList.get(position).getFileNumber()));

        msgvalue.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    try {
                        String PM = dataSnapshot.child("Chats").child("PM").getValue().toString();
                        if (PM.matches("1")) {
                            holder.textViewMsg.setText("There is a New Message from this Patient");
                            holder.textViewMsg.setTextColor(mContext.getResources().getColor(R.color.btncolor));

                        } else {
                            holder.textViewMsg.setText("No New message from this patient");
                            holder.textViewMsg.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                        }
                    } catch (Exception e) {
                        holder.textViewMsg.setText("No New message from this patient");
                        holder.textViewMsg.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                    }

                } else {
                    holder.textViewMsg.setText("No New message from this patient");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                holder.textViewMsg.setText("No new message from this patient");
            }
        });


        holder.cardViewpatientChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,DoctorChatActivity.class);
                intent.putExtra("fileNumber",myPatientList.get(holder.getAdapterPosition()).getFileNumber());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return myPatientList.size();
    }


}

class ChatViewHolder extends RecyclerView.ViewHolder {

    TextView textViewFileNumber, textViewPatientName, textViewDate, textViewMsg;
    CardView cardViewpatientChat;

    public ChatViewHolder(View itemView) {
        super(itemView);

        textViewFileNumber = itemView.findViewById(R.id.textViewFileNumber);
        textViewPatientName = itemView.findViewById(R.id.textViewPatientName);
        textViewDate = itemView.findViewById(R.id.textViewDate);
        textViewMsg = itemView.findViewById(R.id.textViewMsg);

        cardViewpatientChat = itemView.findViewById(R.id.cardViewpatientChat);
    }
}
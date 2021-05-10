package com.example.dessert_club_application;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DeliverAdapter extends RecyclerView.Adapter<DeliverAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Place> place;

    public DeliverAdapter(Context context, ArrayList<Place> place) {

        this.context = context;
        this.place = place;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.delivery_list,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Place place = this.place.get(position);

        myViewHolder.DProvince.setText(place.getProvince());
        myViewHolder.DStreet.setText(place.getStreet());
        myViewHolder.DCity.setText(place.getCity());
        myViewHolder.DPhone.setText(place.getPhone());



        myViewHolder.btnDDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                alertDlg.setMessage("Are you sure?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Payment").child(place.getId());
                        databaseReference.removeValue();
                        Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(),MyAdapter.class);
                        v.getContext().startActivity(i);
                    }
                });
                alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDlg.create().show();

            }


        });



       myViewHolder.btnDEdit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i= new Intent (v.getContext(),DeliveryUpdate.class);
               i.putExtra("id",place.getId());
               i.putExtra("province",place.getProvince());
               i.putExtra("street",place.getStreet());
               i.putExtra("city",place.getCity());
               i.putExtra("phone",place.getPhone());
               v.getContext().startActivity(i);
           }
       });

        myViewHolder.DeSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),DeliverySuccess.class);

                v.getContext().startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {

        return place.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView DProvince,DStreet,DCity,DPhone;
        Button btnDEdit,btnDDelete,DeSuccess;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DProvince=itemView.findViewById(R.id.DProvince);
            DStreet=itemView.findViewById(R.id.DStreet);
            DCity=itemView.findViewById(R.id.DCity);
            DPhone=itemView.findViewById(R.id.DPhone);
            btnDEdit=itemView.findViewById(R.id.btnDEdit);
            btnDDelete=itemView.findViewById(R.id.btnDDelete);
            DeSuccess=itemView.findViewById(R.id.DeSuccess);

        }
    }
}

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<Model>model;
    public MyAdapter(Context context, ArrayList<Model> model){
        this.context = context;
        this.model = model;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        Model model = this.model.get(position);
        myViewHolder.name.setText(model.getName());
        myViewHolder.account.setText(model.getAccount());
        myViewHolder.security.setText(model.getSecurity());
        myViewHolder.expiry.setText(model.getExpiry());



        //delete
        myViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                alertDlg.setMessage("Are you sure?");
                alertDlg.setCancelable(false);
                alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Payment").child(model.getId());
                        databaseReference.removeValue();
                        Toast.makeText(context,"DELETED",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(v.getContext(), MyAdapter.class);
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


        myViewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), EditPData.class);
                i.putExtra("Id", model.getId());
                i.putExtra("name", model.getName());
                i.putExtra("account", model.getAccount());
                i.putExtra("security", model.getSecurity());
                i.putExtra("expiry", model.getExpiry());

                v.getContext().startActivity(i);

            }

        });


    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,account,security,expiry;
        Button btnEdit,btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            CardView cardView;
            name= itemView.findViewById(R.id.name_text);
            account= itemView.findViewById(R.id.account_text);
            security= itemView.findViewById(R.id.security_text);
            expiry= itemView.findViewById(R.id.expiry_text);
            btnEdit=itemView.findViewById(R.id.btnEdit);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }



}



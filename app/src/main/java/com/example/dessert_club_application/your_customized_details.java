package com.example.dessert_club_application;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class your_customized_details extends AppCompatActivity {

    TextView cake_f, cake_of, cake_t, cake_ot;
    Button btn_delete;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_customized_details);

        cake_f=findViewById(R.id.text_CF);
        cake_of=findViewById(R.id.text_OF);
        cake_t=findViewById(R.id.text_CT);
        cake_ot=findViewById(R.id.text_OT);
        btn_delete=findViewById(R.id.button_delete);



        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("cake");

       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot ds: snapshot.getChildren()){
                   cakes cakes= ds.getValue(cakes.class);
                   String flavour= cakes.getFlavor();
                   String otherF= cakes.getOtherF();
                   String topping=cakes.getTopping();
                   String otherT= cakes.getOtherT();

                   cake_f.setText(flavour);
                   cake_of.setText(otherF);
                   cake_t.setText(topping);
                   cake_ot.setText(otherT);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.child("cake").removeValue();

            }
        });

    }

}
package com.example.dessert_club_application;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> model;
    private MyAdapter myAdapter;
    DatabaseReference dbRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        model = new ArrayList<Model>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Payment");
        dbRef.addListenerForSingleValueEvent(valueEventListener);

    }



    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                Model uData = snapshot1.getValue(Model.class);
                model.add(uData);
            }
            myAdapter= new MyAdapter(Payment.this,model);
            recyclerView.setAdapter(myAdapter);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }

    };


}
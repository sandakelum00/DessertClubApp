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

public class RetrieveDelivery extends AppCompatActivity {

    private RecyclerView recycler2;
    private ArrayList<Place> place;
    private DeliverAdapter deliverAdapter;

    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_delivery);

        recycler2=findViewById(R.id.recycler2);
        recycler2.setLayoutManager(new LinearLayoutManager( this));
        place=new ArrayList<Place>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Delivery");
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot snapshot1: snapshot.getChildren()){
                Place pData = snapshot1.getValue(Place.class);
                place.add(pData);
            }
            deliverAdapter=new DeliverAdapter(RetrieveDelivery.this,place);
            recycler2.setAdapter(deliverAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}
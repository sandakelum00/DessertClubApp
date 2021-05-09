package com.example.dessertclubapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listre extends AppCompatActivity {

    private ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listre);

        listView1 = findViewById(R.id.listView1);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.user_list, list);
        listView1.setAdapter(adapter);

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("New_Users");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User inq = snapshot.getValue(User.class);
                    String txt1 = "Name: " + inq.getName();
                    String txt2 = "Email: " + inq.getEmail();
                    String txt3 = "Phone: " + inq.getPhone();
                    String txt4 = "City: " + inq.getCity();

                    list.add(txt1);
                    list.add(txt2);
                    list.add(txt3);
                    list.add(txt4);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
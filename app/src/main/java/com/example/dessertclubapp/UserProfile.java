package com.example.dessertclubapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    private TextView proname,proemail,prophone,procity,prostreet;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        proname = findViewById(R.id.textname);
        proemail = findViewById(R.id.textemail);
        prophone = findViewById(R.id.textphone);
        procity = findViewById(R.id.textcity);
        prostreet = findViewById(R.id.textstreet);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userProfile = dataSnapshot.getValue(User.class);
                proname.setText(userProfile.getName());
                proemail.setText(userProfile.getEmail());
                prophone.setText(userProfile.getPhone());
                procity.setText(userProfile.getCity());
                prostreet.setText(userProfile.getStreet());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

}

}
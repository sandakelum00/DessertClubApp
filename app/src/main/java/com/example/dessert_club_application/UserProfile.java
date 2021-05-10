package com.example.dessert_club_application;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextView a, b, c, d, e;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        a = findViewById(R.id.textname);
        b = findViewById(R.id.textemail);
        c = findViewById(R.id.textphone);
        d = findViewById(R.id.textcity);
        e = findViewById(R.id.textstreet);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("New_Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    String dName = user.getName();
                    String dEmail = user.getEmail();
                    String dPhone = user.getPhone();
                    String dCity = user.getCity();
                    String dStreet = user.getStreet();

                    a.setText("Name: " + dName);
                    b.setText("Email: " + dEmail);
                    c.setText("Phone: " + dPhone);
                    d.setText("City: " + dCity);
                    e.setText("Street: " + dStreet);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfile.this,"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
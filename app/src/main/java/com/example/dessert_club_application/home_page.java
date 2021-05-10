package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class home_page extends AppCompatActivity {

    private Button btn_logout;
    private TextView my_profile;
    //private Button btn_menu;
    //private Button btn_favourite_list;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn_logout=findViewById(R.id.button_logut);
        my_profile =findViewById(R.id.text_myprofile);
         my_profile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent profile= new Intent(home_page.this, UserProfile.class);
                 startActivity(profile);
             }
         });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoLogin= new Intent(home_page.this,login.class);
                startActivity(intoLogin);
            }
        });



    }
}
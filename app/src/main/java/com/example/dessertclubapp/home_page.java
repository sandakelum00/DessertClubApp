package com.example.dessertclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class home_page extends AppCompatActivity {

    private Button btn_logout,btn;
    private Button btn_menu;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btn_logout=findViewById(R.id.button_logut);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intoLogin= new Intent(home_page.this, login.class);
                startActivity(intoLogin);
            }
        });

        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intex = new Intent(home_page.this ,UserProfile.class);
                startActivity(intex);
            }
        });


        btn_menu=findViewById(R.id.button_menu_page);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(home_page.this, login.class);
                startActivity(intent1);
            }
        });
    }
}
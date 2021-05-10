package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityP extends AppCompatActivity {
    EditText  txtName, txtAccount, txtSecurity, txtExpiry;
    Button btnAdd,btnView;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpayment);


        txtName = findViewById(R.id.txt4);
        txtAccount = findViewById(R.id.txt5);
        txtSecurity = findViewById(R.id.txt6);
        txtExpiry = findViewById(R.id.txt8);


        btnAdd = findViewById(R.id.btnAdd);
        btnView=findViewById(R.id.btnView);

        database = FirebaseDatabase.getInstance().getReference().child("Payment");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id, name,account,security,expiry ;

                Id = database.push().getKey();

                name = txtName.getText().toString();
                account = txtAccount.getText().toString();
                security = txtSecurity.getText().toString();
                expiry = txtExpiry.getText().toString();



                if(name.equals("")) {
                    Toast.makeText(MainActivityP.this, "Name required!", Toast.LENGTH_SHORT).show();

                }
                else if(account.equals("")){
                    Toast.makeText(MainActivityP.this,"Account Number required",Toast.LENGTH_SHORT).show();
                }
                else if(security.equals("")){
                    Toast.makeText(MainActivityP.this,"Security code required",Toast.LENGTH_SHORT).show();
                }
                else if(expiry.equals("")){
                    Toast.makeText(MainActivityP.this,"Expiry Data required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Model MainActivity =new Model(Id,name,account,security,expiry);
                    database.child(Id).setValue(MainActivity);
                    Toast.makeText(MainActivityP.this,"done",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivityP.this,Payment.class);
                startActivity(i);
                finish();


            }
        });
    }

}
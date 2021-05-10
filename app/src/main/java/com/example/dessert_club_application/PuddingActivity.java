package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PuddingActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;

    ImageView imageView;
    TextView textView;
    EditText caramelQty;
    String pType, price, qty;
    private Button btnAddCaramel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pudding);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Category");

        btnAddCaramel = (Button)findViewById(R.id.btnCaramel);

        btnAddCaramel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pType = "Caramel Pudding";
                price = String.valueOf(200);
                caramelQty = (EditText)findViewById(R.id.caramel);

                qty = String.valueOf(caramelQty);

                Bundle resultsBundle = new Bundle();
                resultsBundle.putString("pType", pType);
                resultsBundle.putString("price", price);
                resultsBundle.putString("qty", String.valueOf(caramelQty));

                Intent getDetailsIntent = new Intent( com.example.dessert_club_application.PuddingActivity.this, com.example.dessert_club_application.OrdersActivity.class);
                getDetailsIntent.putExtras(resultsBundle);
                startActivity(getDetailsIntent);

                uploadFile();
            }
        });
    }

    private void uploadFile(){

        com.example.dessert_club_application.PuddingModel
                puddingModel = new com.example.dessert_club_application.PuddingModel(
            pType, price, qty
        );

        String uploadId = databaseReference.push().getKey();
        databaseReference.child(uploadId).setValue(puddingModel);
        Toast.makeText( com.example.dessert_club_application.PuddingActivity.this, "Upload Success", Toast.LENGTH_SHORT).show();

    }
}
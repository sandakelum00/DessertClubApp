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

public class DeliveryMainActivity extends AppCompatActivity {
    EditText  txtProvince,txtStreet,txtCity, txtPhone;
    Button btnDAdd,btnView;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);

        txtProvince=findViewById(R.id.txtProvince);
        txtStreet=findViewById(R.id.txtStreet);
        txtCity=findViewById(R.id.txtCity);
        txtPhone = findViewById(R.id.txtPhonen);


        btnDAdd = findViewById(R.id.btnDadd);
        btnView=findViewById(R.id.btnView);


        database = FirebaseDatabase.getInstance().getReference().child("Delivery");

        btnDAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Id,province,street,city,phone;

                Id = database.push().getKey();

                province=txtProvince.getText().toString();
                street=txtStreet.getText().toString();
                city=txtCity.getText().toString();
                phone = txtPhone.getText().toString();




                if(province.equals("")){
                    Toast.makeText(DeliveryMainActivity.this, " province Required!", Toast.LENGTH_SHORT).show();
                }
                else if(street.equals("")) {
                    Toast.makeText(DeliveryMainActivity.this, "street Required!", Toast.LENGTH_SHORT).show();
                }
                else if(city.equals("")){
                    Toast.makeText(DeliveryMainActivity.this, "city Required!", Toast.LENGTH_SHORT).show();
                }

                else if(phone.equals("")){
                    Toast.makeText(DeliveryMainActivity.this, "Phone Number Required!", Toast.LENGTH_SHORT).show();
                }


                else{
                    Place DeliveryMainActivity = new Place(Id,province,street,city,phone);
                    database.child(Id).setValue(DeliveryMainActivity);
                    Toast.makeText(DeliveryMainActivity.this, "Done", Toast.LENGTH_SHORT).show();

                }
            }
        });
       btnView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(DeliveryMainActivity.this,RetrieveDelivery.class);
               startActivity(i);
               finish();
           }
       });





    }

}
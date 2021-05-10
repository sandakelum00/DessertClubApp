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

public class DeliveryUpdate extends AppCompatActivity {

    EditText etProvince,etStreet,etCity,etPhone;
    Button btnUConfirm;
    String id,province,street,city,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_update);

        etProvince.findViewById(R.id.etProvince);
        etStreet.findViewById(R.id.etStreet);
        etCity.findViewById(R.id.etCity);
        etPhone.findViewById(R.id.etPhone);


        btnUConfirm.findViewById(R.id.btnUConfirm);

        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        province=intent.getStringExtra("province");
        street=intent.getStringExtra("street");
        city=intent.getStringExtra("city");
        phone=intent.getStringExtra("phone");


        etProvince.setText(province);
        etStreet.setText(street);
        etCity.setText(city);
        etPhone.setText(phone);


        btnUConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Delivery").child("id");

                String uProvince,uStreet,uCity,uPhone;

                uProvince=etProvince.getText().toString();
                uStreet=etStreet.getText().toString();
                uCity=etCity.getText().toString();
                uPhone=etPhone.getText().toString();


                Place place = new Place(id,uProvince,uStreet,uCity,uPhone);
                databaseReference.setValue(place);
                Toast.makeText(DeliveryUpdate.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();

            }
        });




    }
}

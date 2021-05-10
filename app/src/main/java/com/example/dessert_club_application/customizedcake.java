package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class customizedcake extends AppCompatActivity {

    private Spinner spinnerF, spinnerT;
    private EditText otherF, otherT;
    private Button btnCC;
    FirebaseDatabase database;
    DatabaseReference reference;

    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_cake);

        spinnerF = findViewById(R.id.spinner_flavours);
        spinnerT = findViewById(R.id.spinner_topping);
        otherF = findViewById(R.id.other_flavours);
        otherT = findViewById(R.id.other_topping);
        btnCC = (Button) findViewById(R.id.button_creat_cc);


        database = FirebaseDatabase.getInstance();

        reference = database.getReference().child("cake");

        btnCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String other_flavor=otherF.getText().toString();
                String other_topping=otherT.getText().toString();
                String flavour=spinnerF.getSelectedItem().toString();
                String topping=spinnerT.getSelectedItem().toString();

                if(flavour.isEmpty()&& topping.isEmpty()){
                    Toast.makeText(customizedcake.this, "Please Enter your favourites!", Toast.LENGTH_SHORT).show();

                }else{
                    String id= reference.push().getKey();
                    cakes cakes= new cakes();
                    cakes.setFlavor(flavour);
                    cakes.setOtherF(other_flavor);
                    cakes.setTopping(topping);
                    cakes.setOtherT(other_topping);

                    reference.child(id).setValue(cakes, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if(error==null){
                                Toast.makeText(customizedcake.this, "Data inserted Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(customizedcake.this,your_customized_details.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(customizedcake.this, "Error!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }


        });


    }
}

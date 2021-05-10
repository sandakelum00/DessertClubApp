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

public class EditPData extends AppCompatActivity {
    EditText etName,etAccount,etSecurity,etExpiry;
    Button btnEdit,btnConfirm;
    String id,name,account,security,expiry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pdata);

        etName=findViewById(R.id.etName);
        etAccount=findViewById(R.id.etAccount);
        etSecurity=findViewById(R.id.etSecurity);
        etExpiry=findViewById(R.id.etExpiry);


        btnEdit=findViewById(R.id.btnEdit);

        Intent intent =getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        account=intent.getStringExtra("account");
        security=intent.getStringExtra("security");
        expiry=intent.getStringExtra("expiry");

        etName.setText(name);
        etAccount.setText(account);
        etSecurity.setText(security);
        etExpiry.setText(expiry);

        btnEdit.setOnClickListener(v -> {
            DatabaseReference databaseReference  =  FirebaseDatabase.getInstance().getReference("Payment").child("id");
            String uName,uAccount,uSecurity,uExpiry;

            uName = etName.getText().toString();
            uAccount=etAccount.getText().toString();
            uSecurity=etSecurity.getText().toString();
            uExpiry=etExpiry.getText().toString();


            Model model = new Model(id,uName,uAccount,uSecurity,uExpiry);
            databaseReference.setValue(model);
            Toast.makeText(EditPData.this,"Data Updated Successfully",Toast.LENGTH_SHORT).show();
        });
        btnConfirm=(Button)findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(EditPData.this,Success.class);
                startActivity(i);
                finish();
            }
        });


    }
}
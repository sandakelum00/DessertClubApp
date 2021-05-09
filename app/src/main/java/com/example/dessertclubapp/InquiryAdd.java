package com.example.dessertclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InquiryAdd extends AppCompatActivity {

    EditText name, subject, inquiry;
    Button btnsave, btnnext;
    DatabaseReference reff;
    Inquiry inq;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_add);

        name = (EditText) findViewById(R.id.name);
        subject = (EditText) findViewById(R.id.subject);
        inquiry = (EditText) findViewById(R.id.inquiry);
        btnsave = (Button) findViewById(R.id.btnsave);
        btnnext = (Button) findViewById(R.id.btnnext);
        inq = new Inquiry();
        reff = FirebaseDatabase.getInstance().getReference().child("Inquiries");
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.name, RegexTemplate.NOT_EMPTY, R.string.invalid_name);
        awesomeValidation.addValidation(this, R.id.subject, RegexTemplate.NOT_EMPTY, R.string.invalid_subject);
        awesomeValidation.addValidation(this, R.id.inquiry, RegexTemplate.NOT_EMPTY, R.string.inquiry);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (awesomeValidation.validate()) {
                    inq.setName(name.getText().toString().trim());
                    inq.setSubject(subject.getText().toString().trim());
                    inq.setInquiry(inquiry.getText().toString().trim());
                    reff.push().setValue(inq);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityinq();
            }
        });
    }

    private void openActivityinq() {
        Intent intent = new Intent(this, InquiryRetrieve.class);
        startActivity(intent);
    }
}

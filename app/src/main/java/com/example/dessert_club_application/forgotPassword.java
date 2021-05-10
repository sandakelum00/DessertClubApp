package com.example.dessert_club_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {

    private EditText user_email;
    private Button update_password;
    private String user_email_update;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        firebaseAuth= FirebaseAuth.getInstance();
        user_email=findViewById(R.id.userEmail_forogot);
        update_password=findViewById(R.id.button_forogt);

        update_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateDate();
            }
        });

    }
    private void validateDate(){
        user_email_update=user_email.getText().toString();
        if (user_email_update.isEmpty()){
            user_email.setError("Required");
            user_email.requestFocus();
        }else{
            forgetPass();
        }
    }
    private void forgetPass(){
        firebaseAuth.sendPasswordResetEmail(user_email_update)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(forgotPassword.this, "Check your email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(forgotPassword.this, login.class));
                            finish();
                        }else{
                            Toast.makeText(forgotPassword.this,
                                    "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
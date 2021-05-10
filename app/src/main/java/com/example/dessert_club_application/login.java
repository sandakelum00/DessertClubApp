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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private EditText user_email_login,password_login;
    private Button button_login;
    private Button button_signup;
    private Button button_forogot_password;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthlistner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth= FirebaseAuth.getInstance();
        user_email_login= findViewById(R.id.username_email_login);
        password_login= findViewById(R.id.password_login);
        button_login= findViewById(R.id.button_login);

        firebaseAuthlistner= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
                if(firebaseUser!= null){
                    Toast.makeText(login.this, "You have logged in!",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(login.this, home_page.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(login.this, "Please login!",Toast.LENGTH_SHORT).show();
                }
            }
        };

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email= user_email_login.getText().toString();
                String user_Password=password_login.getText().toString();

                if(user_email.isEmpty()){
                    user_email_login.setError("Please enter your email!");
                    user_email_login.requestFocus();
                }
                else if(user_Password.isEmpty()){
                    password_login.setError("Please enter your password!");
                    password_login.requestFocus();
                }
                else if (user_email.isEmpty() && user_Password.isEmpty()){
                    Toast.makeText(login.this, "Your fields are empty!",Toast.LENGTH_SHORT).show();
                }
                else if(!(user_email.isEmpty() && user_Password.isEmpty())){
                    firebaseAuth.signInWithEmailAndPassword(user_email,user_Password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(login.this, "Login Unsuccessful, Please login again!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intentToHome= new Intent(login.this, home_page.class);
                                startActivity(intentToHome);
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(login.this, "Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Connect to SignUp Button
        button_signup= (Button) findViewById(R.id.button_signup);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openuser_register();
            }
        });

        button_forogot_password=(Button) findViewById(R.id.button_forgotpassword);
        button_forogot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_forogotPassword();
            }
        });
     }
     private void  openuser_register(){
         Intent intent1=new Intent(this, user_register.class);
         startActivity(intent1);
     }

     private void open_forogotPassword(){
        Intent intent2=new Intent(this, forgotPassword.class);
        startActivity(intent2);
     }

     protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthlistner);
     }
}
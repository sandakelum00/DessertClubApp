package com.example.dessertclubapp;

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
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    public class user_register extends AppCompatActivity {

    //variable initialising
        private EditText name_register, email_register,street_register,city_register,
        phone_register, password_register, confirm_password_register;
        private Button button_confirm_register;

        private FirebaseDatabase database= FirebaseDatabase.getInstance();
        private DatabaseReference root;
        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_register);

            //Assign variables
            name_register= findViewById(R.id.name_register);
            email_register=findViewById(R.id.email_register);
            street_register=findViewById(R.id.street_register);
            city_register=findViewById(R.id.city_register);
            phone_register=findViewById(R.id.phone_register);
            password_register=findViewById(R.id.password_register);
            confirm_password_register=findViewById(R.id.confirm_password);
            button_confirm_register= findViewById(R.id.button_confirm_register);
            firebaseAuth= FirebaseAuth.getInstance();



            firebaseAuth= FirebaseAuth.getInstance();

            button_confirm_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertData();
                    String user_email= email_register.getText().toString();
                    String user_password=password_register.getText().toString();

                    if (user_email.isEmpty()){
                        email_register.setError("Please Enter the email!");
                        email_register.requestFocus();
                    }
                    else if(user_password.isEmpty()){
                        password_register.setError("Please Enter the email!");
                        password_register.requestFocus();
                    }
                    else if (user_email.isEmpty() && user_password.isEmpty()){
                        Toast.makeText(user_register.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!(user_email.isEmpty() && user_password.isEmpty())){
                        firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(user_register.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(user_register.this, "SignUp Unsuccessful!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    startActivity(new Intent(user_register.this, home_page.class));
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(user_register.this, "Error!", Toast.LENGTH_SHORT).show();
                    }

        }

        private void insertData(){
            root= database.getReference("New_Users");

            String name= name_register.getText().toString();
            String email=email_register.getText().toString();
            String street=street_register.getText().toString();
            String city=city_register.getText().toString();
            String phone=phone_register.getText().toString();
            String password=password_register.getText().toString();
            String confirm_password=confirm_password_register.getText().toString();

            if (name.isEmpty()){
                name_register.setError("Please enter your name!");
                name_register.requestFocus();
            }
            else if(email.isEmpty()){
                email_register.setError("Please enter your email!");
                email_register.requestFocus();
            }
            else if(street.isEmpty()){
                street_register.setError("Please enter your street!");
                street_register.requestFocus();
            }
            else if(city.isEmpty()){
                city_register.setError("Please enter your city!");
                city_register.requestFocus();
            }
            else if (phone.isEmpty()){
                phone_register.setError("Please enter your mobile number!");
                phone_register.requestFocus();
            }
            else if (password.isEmpty()){
                password_register.setError("Please enter your password!");
                password_register.requestFocus();
            }
            else if (confirm_password.isEmpty()){
                confirm_password_register.setError("Please enter your password!");
                confirm_password_register.requestFocus();
            }

            else if(name.isEmpty() && email.isEmpty() && street.isEmpty() && city.isEmpty()
                    && phone.isEmpty() ){
                Toast.makeText(user_register.this, "Your fields are empty!",Toast.LENGTH_SHORT).show();
            }
            else {
                dataHolder dataHolder_object = new dataHolder(name, email, street, city, phone,password,confirm_password);

                root.push().setValue(dataHolder_object);

            }
        }

            });

        }

    }

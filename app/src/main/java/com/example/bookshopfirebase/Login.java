package com.example.bookshopfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private TextInputEditText username, password;
    private Button loginBtn;
    private TextView gotToRegPage,forget_password;
    DatabaseReference databaseUser;
    public String psdFromTable,userFromTable;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextInputEditText) findViewById(R.id.usernameLogin);
        password = (TextInputEditText) findViewById(R.id.passwordLogin);
        loginBtn = findViewById(R.id.loginBtn);
        gotToRegPage = findViewById(R.id.goToRegister);


        gotToRegPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (username.getText().toString().isEmpty()) {
                    username.setError(getText(R.string.error_user_empty));
                } else if (username.getText().toString().length() < 5) {
                    username.setError(getText(R.string.error_user_less_length));
                } else {
                    username.setError(null);
                }


                if (password.getText().toString().isEmpty()) {
                    password.setError(getResources().getString(R.string.error_pass_empty));
                } else if (password.getText().toString().length() < 5) {
                    password.setError(getResources().getString(R.string.error_pass_less_length));
                } else {
                    password.setError(null);
                }

                final String user = username.getText().toString();
                final String psd = password.getText().toString();

                if ((!user.isEmpty() && user.length() > 5) && (!psd.isEmpty() && psd.length() > 5)) {

                    databaseUser= FirebaseDatabase.getInstance().getReference().child("users").child(user);

                    ValueEventListener postListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            userDetails = dataSnapshot.getValue(UserDetails.class);
                            if (userDetails.getPassword().equals(psd) && userDetails.getUsername().equals(user)) {
                                Intent i=new Intent(getApplicationContext(),ShowBooks.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w("loadPost:onCancelled", databaseError.toException());
                        }
                    };
                    databaseUser.addValueEventListener(postListener);
                }
            }
        });


    }



}
package com.example.bookshopfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
    private EditText username,password,confirmPassword,phoneNumber,email;
    private Button regBtn;
    DatabaseReference databaseUser,databaseImage;
    private Boolean isUsernameCorrect,isPasswordCorrect,isPhoneCorrect,isEmailCorrect,isConfirmPasswordCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.usernameReg);
        password=findViewById(R.id.passwordReg);
        confirmPassword=findViewById(R.id.confirmPasswordReg);
        phoneNumber=findViewById(R.id.phoneNoReg);
        email=findViewById(R.id.emailReg);
        regBtn=findViewById(R.id.registerBtn);
        databaseUser= FirebaseDatabase.getInstance().getReference("users");
        databaseImage= FirebaseDatabase.getInstance().getReference().child("bookimages");

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameStr=username.getText().toString().trim();
                String passwordStr=password.getText().toString();
                String confirmPasswordStr=confirmPassword.getText().toString();
                String phoneStr=phoneNumber.getText().toString();
                String emailStr=email.getText().toString();

                if(usernameStr.isEmpty())
                {
                    username.setError(getText(R.string.error_user_empty));
                    isUsernameCorrect=false;
                }
                else if(usernameStr.length()<5)
                {
                    username.setError(getText(R.string.error_user_less_length));
                    isUsernameCorrect=false;
                }
                else
                {
                    username.setError(null);
                    isUsernameCorrect=true;
                }

                if(passwordStr.isEmpty())
                {
                    password.setError(getResources().getString(R.string.error_pass_empty));
                    isPasswordCorrect=false;
                }
                else if(passwordStr.length()<5)
                {
                    password.setError(getResources().getString(R.string.error_pass_less_length));
                    isPasswordCorrect=false;
                }
                else
                {
                    password.setError(null);
                    isPasswordCorrect=true;
                }

                if(confirmPasswordStr.isEmpty())
                {
                    confirmPassword.setError(getResources().getString(R.string.error_pass_empty));
                    isConfirmPasswordCorrect=false;
                }
                else if(confirmPasswordStr.length()<5)
                {
                    confirmPassword.setError(getResources().getString(R.string.error_pass_less_length));
                    isConfirmPasswordCorrect=false;
                }
                else
                {
                    confirmPassword.setError(null);
                    isConfirmPasswordCorrect=true;
                }

                if(emailStr.isEmpty())
                {
                    email.setError(getResources().getString(R.string.error_email_empty));
                    isEmailCorrect=false;
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches())
                {
                    email.setError(getResources().getString(R.string.error_email_wrong));
                    isEmailCorrect=false;
                }
                else
                {
                    email.setError(null);
                    isEmailCorrect=true;
                }

                if(phoneStr.isEmpty())
                {
                    phoneNumber.setError(getResources().getString(R.string.error_phn_empty));
                    isPhoneCorrect=false;
                }
                else if(!Patterns.PHONE.matcher(phoneStr).matches())
                {
                    phoneNumber.setError(getResources().getString(R.string.error_phn_wrong));
                    isPhoneCorrect=false;
                }
                else
                {
                    phoneNumber.setError(null);
                    isPhoneCorrect=true;
                }


                if(isUsernameCorrect && isPasswordCorrect && isConfirmPasswordCorrect && isPhoneCorrect && isEmailCorrect)
                {
                    password.setError(null);
                    confirmPassword.setError(null);
                    if((password.getText().toString()).equals(confirmPassword.getText().toString())) {
                        UserDetails artist = new UserDetails(usernameStr, passwordStr, phoneStr, emailStr);
                        /*BookImage bk1=new BookImage("Book1",R.drawable.book1);
                        BookImage bk2=new BookImage("Book2",R.drawable.book2);
                        BookImage bk3=new BookImage("Book3",R.drawable.book3);*/

                        try {
                            databaseUser.child(usernameStr).setValue(artist);
                            /*databaseImage.child("book1").setValue(bk1);
                            databaseImage.child("book2").setValue(bk2);
                            databaseImage.child("book3").setValue(bk3);*/
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_LONG).show();
                        }

                        Toast.makeText(getApplicationContext(), "User added", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ShowBooks.class);
                        i.putExtra("name", usernameStr);
                        startActivity(i);
                    }
                    else
                    {
                        confirmPassword.setError(getResources().getString(R.string.password_dont_match));
                        password.setError(getResources().getString(R.string.password_dont_match));
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You should enter details!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
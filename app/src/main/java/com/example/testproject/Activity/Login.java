package com.example.testproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testproject.R;
import com.example.testproject.databinding.ActivityLoginBinding;
import com.example.testproject.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
ActivityLoginBinding loginBinding;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
         mAuth = FirebaseAuth.getInstance();
         loginBinding.SignUp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(Login.this,Register.class));
             }
         });
        loginBinding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = loginBinding.email.getText().toString();
                String Password = loginBinding.password.getText().toString();
                ProgressDialog progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Please wait..");
                progressDialog.show();
                if (loginBinding.email.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Enter Email !!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else if (loginBinding.password.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "Enter Password !!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else{
                       mAuth.signInWithEmailAndPassword(Email,Password)
                               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                   @Override
                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                       if (task.isSuccessful()){
                                           Intent i = new Intent(Login.this,Dashboard.class);
                                           progressDialog.dismiss();
                                           startActivity(i);
                                       }
                                       else{
                                           Toast.makeText(Login.this, "Failed !!", Toast.LENGTH_SHORT).show();
                                           progressDialog.dismiss();
                                       }
                                   }
                               });
                }
            }
        });
    }
}
package com.example.testproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testproject.R;
import com.example.testproject.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
FirebaseAuth mAuth;
ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(Register.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();
                if (binding.email.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else if (binding.password.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "Enter Password !!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else if (binding.packed.getText().toString().isEmpty()){
                    Toast.makeText(Register.this, "Enter Confirm Password !!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    return;
                }
                else if(binding.password.getText().toString().equals(binding.packed.getText().toString())){
                    mAuth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                  if (task.isSuccessful()){
                                      Intent i = new Intent(Register.this,Dashboard.class);
                                      progressDialog.dismiss();
                                      startActivity(i);
                                  }else{
                                      Toast.makeText(Register.this, "Failed !!", Toast.LENGTH_SHORT).show();
                                      progressDialog.dismiss();
                                      return;
                                  }
                                }
                            });
                }
            }
        });

    }
}
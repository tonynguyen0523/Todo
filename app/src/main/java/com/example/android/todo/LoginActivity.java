package com.example.android.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.emailField)
    EditText emailEditText;
    @BindView(R.id.passwordField)
    EditText passwordEmailText;
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.signUpText)
    TextView signUpTextView;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ButterKnife.bind(this);

        // Initialize FirebaseAuth
        mFirebaseAuth = FirebaseAuth.getInstance();

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailEditText.getText().toString().trim();
                String password = passwordEmailText.getText().toString().trim();

                // Validate email and password
                if(!validateForm(email, password)){
                    return;
                }

                mFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

    }

    private boolean validateForm(String email, String password){

        boolean valid = true;

        if(TextUtils.isEmpty(email)){
            emailEditText.setError("Required.");
            valid = false;
        } else {
             emailEditText.setError(null);
        }

        if (TextUtils.isEmpty(password)){
            passwordEmailText.setError("Required.");
            valid = false;
        } else {
            passwordEmailText.setError(null);
        }

        return valid;

    }
}

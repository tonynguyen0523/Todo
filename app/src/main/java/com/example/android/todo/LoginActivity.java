package com.example.android.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

                validateForm();

            }
        });

    }

    private boolean validateForm(){

        boolean valid = true;

        String email = emailEditText.getText().toString().trim();
        String password = passwordEmailText.getText().toString().trim();

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

package com.yairn.mad.madexplicit;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText emailTextView;
    private EditText passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
    }

    public void onClickLogin(View view) {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        if(email.length() > 0 && password.length() > 0) {
            Snackbar.make(getCurrentFocus(), "Incorrect credentials",
                    Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(getCurrentFocus(), "One or more fields are missing",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}

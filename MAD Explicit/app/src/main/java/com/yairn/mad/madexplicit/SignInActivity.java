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

    /*
     * onCreate is an Android method that is called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     * Always followed by onStart().
     *
     * In this method, the contentView, or in other words the UI layout named activity_main
     * will be associated with this code, and it will create/display it.
     * Additionally, this is where the code is being bounded to the XML/UI, by using findViewByID.
     * This is where listeners can bounded to most UIs, or just use the OnClick
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickLogin, the email of the emailTextView and the password of the passwordTextView
     * are stored and checked. Only the cases where the credentials are incorrect and there is a missing
     * field are checked, and each case has a message displayed in the form of a snackbar
     */
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

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickRegister, the explicit intent is opened. In this case, the RegisterActivity.
     */
    public void onClickRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}

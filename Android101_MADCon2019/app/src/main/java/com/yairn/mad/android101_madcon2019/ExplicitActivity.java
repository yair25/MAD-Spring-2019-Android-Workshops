package com.yairn.mad.android101_madcon2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExplicitActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(text.length() > 0) {
                    textView.setText(text);
                } else {
                    Toast.makeText(getBaseContext(), "Empty String", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void onClickContinueButton(View view) {
        String text = textView.getText().toString();
        if(text.length() > 0) {
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            mainActivityIntent.putExtra("text", text);

            mainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainActivityIntent);
        } else {
            Toast.makeText(getBaseContext(), "Save Text to Continue", Toast.LENGTH_LONG).show();
        }
    }

}

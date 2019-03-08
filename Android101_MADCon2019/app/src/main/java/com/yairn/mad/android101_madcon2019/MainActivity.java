package com.yairn.mad.android101_madcon2019;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button explicitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        explicitButton = findViewById(R.id.topButton);

        Bundle extras = getIntent().getExtras();
        String text = null;

        if(extras != null) {
            text = extras.getString("text");
        }

        if(text == null) {
            text = explicitButton.getText().toString();
        }

        explicitButton.setText(text);
    }

    public void onClickExplicitButton(View view) {
        Intent mainActivityIntent = new Intent(this, ExplicitActivity.class);
        startActivity(mainActivityIntent);
    }

    public void onClickImplicitButton(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_BROWSER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Sets the url to open
        intent.setData(Uri.parse("https://www.txcsmad.com/"));

        try {
            startActivity(intent);
        } catch (Exception e) {
            // Will ensure that the app does not crash if there is no Maps app found
            Toast.makeText(this, "No Browser App Found.", Toast.LENGTH_LONG).show();
        }
    }
}

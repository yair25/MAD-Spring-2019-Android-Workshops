package com.yairn.mad.madimplicit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
     * onCreate is an Android method that is called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     * Always followed by onStart().
     *
     * In this method, the contentView, or in other words the UI layout named activity_main
     * will be associated with this code, and it will create/display it.
     * This is where listeners can bounded to most UIs, or just use the OnClick
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Bind code and Layout/UI
        setContentView(R.layout.activity_main);
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickMaps, an Intent is made to start the Maps app.
     * If the device does not have a Maps app, a message (Toast) will be displayed.
     */
    public void onClickMaps(View view) {
        Intent intent = new Intent();
        // Action is set to main, so that the app is the main focus
        intent.setAction(Intent.ACTION_MAIN);
        // Category is set to CATEGORY_APP_MAPS, so any app that is considered a map can be opened
        intent.addCategory(Intent.CATEGORY_APP_MAPS);
        // Flag is set to FLAG_ACTIVITY_NEW_TASK, because a whole new task (app) will start
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Will try to start the activity
        try {
            startActivity(intent);
        } catch (Exception e) {
            // Will ensure that the app does not crash if there is no Maps app found
            Toast.makeText(this, "No Maps App Found.", Toast.LENGTH_LONG).show();
        }

    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickBrowser, an Intent is made to start the Browser app.
     * If the device does not have a Browser app, a message (Toast) will be displayed.
     */
    public void onClickBrowser(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_BROWSER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
        } catch (Exception e) {
            // Will ensure that the app does not crash if there is no Maps app found
            Toast.makeText(this, "No Browser App Found.", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickMusic, an Intent is made to start the Music app.
     * If the device does not have a Music app, a message (Toast) will be displayed.
     */
    public void onClickMusic(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MUSIC);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No Music App Found.", Toast.LENGTH_LONG).show();
        }
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickCalc, an Intent is made to start the Calculator app.
     * If the device does not have a Calculator app, a message (Toast) will be displayed.
     */
    public void onClickCalc(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_CALCULATOR);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "No Calculator App Found.", Toast.LENGTH_LONG).show();
        }
    }
}

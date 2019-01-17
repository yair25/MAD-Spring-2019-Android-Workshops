package com.yairn.mad.madinfinitekittens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class KittenImageActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_kitten_image);

        Intent intent = getIntent();
        String kittenUrl = intent.getStringExtra("kittenUrl");

        ImageView kittenImageView = findViewById(R.id.kittenImageView);

        // If true, the Picasso API fits the image into the kittenImageView
        if(kittenUrl != null)
            Picasso.get().load(kittenUrl).fit().into(kittenImageView);
    }
}

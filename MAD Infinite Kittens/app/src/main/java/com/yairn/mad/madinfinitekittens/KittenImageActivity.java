package com.yairn.mad.madinfinitekittens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class KittenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitten_image);

        Intent intent = getIntent();
        String kittenUrl = intent.getStringExtra("kittenUrl");

        ImageView kittenImageView = findViewById(R.id.kittenImageView);

        if(kittenUrl != null)
            Picasso.get().load(kittenUrl).fit().into(kittenImageView);
    }
}

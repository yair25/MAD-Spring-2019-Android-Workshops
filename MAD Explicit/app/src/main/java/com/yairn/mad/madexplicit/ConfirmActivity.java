package com.yairn.mad.madexplicit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {

    private ImageView picImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        picImageView = findViewById(R.id.picImageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView genderTextView = findViewById(R.id.genderTextView);

        Bundle extras = getIntent().getExtras();

        nameTextView.setText("Name: " + extras.getString("Name"));
        emailTextView.setText("Email: " + extras.getString("Email"));
        genderTextView.setText("Gender: " + extras.getString("Gender"));
        setPic(extras.getString("PicturePath"));
    }

    public void onClickConfirm(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void setPic(String currentPhotoPath) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmapPicture = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        picImageView.setImageBitmap(bitmapPicture);
    }

}

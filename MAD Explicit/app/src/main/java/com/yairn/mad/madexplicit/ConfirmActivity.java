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
        setContentView(R.layout.activity_confirm);

        picImageView = findViewById(R.id.picImageView);
        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView genderTextView = findViewById(R.id.genderTextView);

        // How to retrieve data
        Bundle extras = getIntent().getExtras();

        // How to retrieve data based on the key
        nameTextView.setText(R.string.name_placeholder + extras.getString("Name"));
        emailTextView.setText(R.string.email_placeholder + extras.getString("Email"));
        genderTextView.setText(R.string.gender_placeholder + extras.getString("Gender"));
        setPic(extras.getString("PicturePath"));
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickConfirm, the SignInActivity will start, but the stack of the previous
     * activities will be popped.
     */
    public void onClickConfirm(View view) {
        Intent intent = new Intent(this, SignInActivity.class);

        // The FLAG_ACTIVITY_CLEAR_TOP flag pops of the previous activities
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /*
     * The setPic method sets the image of the picImageView from the currentPhotoPath parameter.
     */
    private void setPic(String currentPhotoPath) {
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmapPicture = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        picImageView.setImageBitmap(bitmapPicture);
    }

}

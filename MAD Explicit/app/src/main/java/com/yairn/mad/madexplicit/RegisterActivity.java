package com.yairn.mad.madexplicit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    private static final int ALL_PERM_GRANTED = 0;
    private static final int REQUEST_TAKE_PHOTO = 1;

    private ImageButton addPhotoButton;
    private EditText nameEditTextView;
    private EditText emailEditTextView;
    private EditText passwordEditText;
    private Spinner genderSpinner;

    private String currentPhotoPath;
    private Bitmap bitmapPicture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        addPhotoButton = findViewById(R.id.addPhotoButton);
        nameEditTextView = findViewById(R.id.nameEditText);
        emailEditTextView = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        genderSpinner = findViewById(R.id.genderSpinner);

        addPhotoButton.setBackgroundResource(R.drawable.ic_add_photo);
    }

    public void onClickPic(View view) {
        dispatchTakePictureIntent();
    }

    public void onClickNext(View view) {
        String firstName = nameEditTextView.getText().toString();
        String email = emailEditTextView.getText().toString();
        String password = passwordEditText.getText().toString();

        if(firstName.length() > 0 && email.length() > 0 &&
                password.length() > 0 && genderSpinner.getSelectedItemPosition() > 0 &&
                bitmapPicture != null) {
            Intent confirmIntent = new Intent(this, ConfirmActivity.class);

            confirmIntent.putExtra("Name", firstName);
            confirmIntent.putExtra("Email", email);
            confirmIntent.putExtra("Gender", genderSpinner.getSelectedItem().toString());
            confirmIntent.putExtra("PicturePath", currentPhotoPath);

            startActivity(confirmIntent);

        } else {
            Snackbar.make(getCurrentFocus(), "One or more fields are missing",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.yairn.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

//    private void galleryAddPic() {
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(currentPhotoPath);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        this.sendBroadcast(mediaScanIntent);
//    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = addPhotoButton.getWidth();
        int targetH = addPhotoButton.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        bitmapPicture = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        addPhotoButton.setImageBitmap(bitmapPicture);
    }


}

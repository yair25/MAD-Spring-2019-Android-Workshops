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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        setContentView(R.layout.activity_register);

        addPhotoButton = findViewById(R.id.addPhotoButton);
        nameEditTextView = findViewById(R.id.nameEditText);
        emailEditTextView = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        genderSpinner = findViewById(R.id.genderSpinner);

        // Listener for the spinner. If clicked, then the keyboard will be hidden
        genderSpinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });

        // Image is put on the addPhotoButton
        addPhotoButton.setBackgroundResource(R.drawable.ic_add_photo);
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickPic, the permission are checked and requested, and then the camera
     * intent is dispatched to take a picture.
     */
    public void onClickPic(View view) {
        checkPermissions();
        dispatchTakePictureIntent();
    }

    /*
     * If the Button View's onClick attribute is set, it is required to make the exact named method.
     * It must take a View parameter.
     * This is where the all the logic for clicking the button goes.
     *
     * In the method onClickNext, the strings of the textViews are checks to make sure they are not
     * empty, as well as a valid gender, and a picture/bitmap is included.
     * If every field is valid and present, then they are passed to the intent that will start the
     * ConfirmationActivity, else a message (snackbar) will be displayed.
     */
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

    /*
     * The method onActivityResult, is called after the camera is done, and returns the picture.
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            setPic();
        }
    }

    /*
     * Creates a file for the image that will be taken, so it can later be saved on the device.
     */
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

    /*
     * The dispatchTakePictureIntent method starts the camera intent, creates the file for the image
     * to be taken, uses the FileProvider to know where the file will be stored on the device.
     * Finally, it starts the camera intent with the request to take a picture, so the onActivityResult
     * method can be called later.
     */
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

    /*
     * The setPic method reduces the size of the image to fit correctly into the addPhotoButton.
     * This method also reduce the amount of dynamic heap used by expanding the JPEG into a memory
     * array that's already scaled to match the size of the destination view.
     */
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

    /*
     * The hideKeyboard method hides the keyboard
    */
    private void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
     * The checkPermissions method checks to make sure that the user has granted the
     * WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, and the CAMERA permissions.
     * If the permissions have not been granted, the permission will be requested in runtime.
     */
    private void checkPermissions() {
        String[] permissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        for(String permission: permissions) {
            if(checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, ALL_PERM_GRANTED);
            }
        }

    }

}

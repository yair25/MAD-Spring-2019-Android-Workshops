package com.yairn.mad.maddice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImageView;
//    private Button rollButton;

    // Array that stores the Images/drawables
    private final int[] diceImages = new int[] {
            R.drawable.dice_one, R.drawable.dice_two, R.drawable.dice_three, R.drawable.dice_four,
            R.drawable.dice_five, R.drawable.dice_six};

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

        // Bind of code and Layout/UI
        setContentView(R.layout.activity_main);

        // Linking/Bounding UI to the code
        diceImageView = findViewById(R.id.DiceImageView);

//         // Optional, but we can also not use onClick and set up a listener for the button
//        rollButton = findViewById(R.id.RollButton);
//
//        rollButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int rndImage = (int) (Math.random() * 6);
//                diceImageView.setImageResource(diceImages[rndImage]);
//                rndImage++;
//                Toast.makeText(this, "You Rolled : " + rndImage, Toast.LENGTH_LONG).show();

//            }
//        });
    }

    /*
    * If the Button View's onClick attribute is set, it is required to make the exact named method.
    * It must take a View parameter.
    * This is where the all the logic for clicking the button goes.
    *
    * In the method onClickRollButton, the roll of a dice is being simulated by getting a random integer,
    * and setting the image resource based on the result. Finally a toast (message) is shown on the screen.
    */
    public void onClickRollButton(View view) {
        // Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0
        int rndImage = (int) (Math.random() * 6);

        // set the image resource to the Image View
        diceImageView.setImageResource(diceImages[rndImage]);
        rndImage++;

        // Will show the message ""You Rolled: rndImage"
        Toast.makeText(this, "You Rolled: " + rndImage, Toast.LENGTH_LONG).show();
    }
}

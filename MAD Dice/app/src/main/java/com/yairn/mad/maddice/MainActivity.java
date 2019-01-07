package com.yairn.mad.maddice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImageView;
//    private Button rollButton;

    private final int[] diceImages = new int[] {
            R.drawable.dice_one, R.drawable.dice_two, R.drawable.dice_three, R.drawable.dice_four,
            R.drawable.dice_five, R.drawable.dice_six};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImageView = findViewById(R.id.DiceImageView);
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

    public void onClickRollButton(View view) {
        // Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0
        int rndImage = (int) (Math.random() * 6);
        diceImageView.setImageResource(diceImages[rndImage]);
        rndImage++;
        Toast.makeText(this, "You Rolled : " + rndImage, Toast.LENGTH_LONG).show();
    }
}

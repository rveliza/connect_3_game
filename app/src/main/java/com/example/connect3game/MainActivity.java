package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.GridLayout;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow; 1: red; 2: empty
    int activePlayer = 0;
    boolean gameActive = true;
    int[] gamesState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gamesState[tappedCounter] == 2 && gameActive) {
            gamesState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(500);

            for (int[] winningPosition : winningPositions) {
                if (gamesState[winningPosition[0]] == gamesState[winningPosition[1]]
                        && gamesState[winningPosition[1]] == gamesState[winningPosition[2]]
                        && gamesState[winningPosition[0]] != 2){
                    // someone has won!
                    gameActive = false;
                    String winner = "";

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won");
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setText("Play Again!");
                    playAgainButton.setVisibility(View.VISIBLE);

                }
            }
        }

    }

    public void playAgain (View view) {
//        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for (int i = 0; i<gamesState.length; i++){
            gamesState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
 package com.example.connect3tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.connect3tictactoe.R.id.gridLayout;

 public class MainActivity extends AppCompatActivity {
     int activePlayer = 0; // 0: blue , 1: red , 2: empty
     int [] gameState = {2,2,2,2,2,2,2,2,2};
     int [][] winningStates = {{0,1,2},{0,3,6},{0,4,8},{1,4,7},{2,4,6},{2,5,8},{3,4,5},{6,7,8}};
     boolean gameActive = true;
     int drawCounter = 0;
     String winner = "";
    public void dropIn(View view){
        ImageView counter = (ImageView)view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        // 1condtion to restrict user to change its decison
        // 2 condtion not to continue the game after someone has won
        if(gameState[tappedCounter] ==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.blue);
                activePlayer = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;

            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            drawCounter++;

            for (int[] winningPosition : winningStates) {
                // Someone has won
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    gameActive = false;

                    if (activePlayer == 1) {
                        winner = "Blue";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = findViewById(R.id.playAgain);

                    TextView winnerTextView =  findViewById(R.id.txtWinner);

                    winnerTextView.setText( winner + " has Won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerTextView.setVisibility(View.VISIBLE);


                }
            }
            if(drawCounter == 9){
                Button playAgainButton = findViewById(R.id.playAgain);
                Log.i("tag","come inside this");
                TextView winnerTextView =  findViewById(R.id.txtWinner);
                if(winner.isEmpty()){
                    winnerTextView.setText( "Draw! play again");
                }

                playAgainButton.setVisibility(View.VISIBLE);

                winnerTextView.setVisibility(View.VISIBLE);

            }

        }

    }
    public void playAgain(View view){
        Button playAgainButton = findViewById(R.id.playAgain);
        TextView winnerTextView = findViewById(R.id.txtWinner);
        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);
        GridLayout myGridLayout = findViewById(gridLayout);
        for(int i = 0; i < myGridLayout.getChildCount();i++){
            ImageView counter = (ImageView)myGridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i = 0; i < gameState.length;i++){
            gameState[i] = 2;
        }
        activePlayer = 0;
        gameActive = true;
        drawCounter = 0;
         winner = "";


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


 }
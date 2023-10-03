package com.example.thepiggame;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.thepiggame.gameFun.RollDices;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    //Game
    private final RollDices game = new RollDices();


    private Button hold;

    //TextViews
    private TextView winnerTextView1, winnerTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Background
        //bakgroundLeft = findViewById(R.id.backgroundLeft);

        //TextViews
        winnerTextView1 = findViewById(R.id.winner1);
        winnerTextView2 = findViewById(R.id.winner2);

        //Set the text views invisible.
        winnerTextView1.setVisibility(View.INVISIBLE);
        winnerTextView2.setVisibility(View.INVISIBLE);


        //Buttons
        Button restartGame = findViewById(R.id.newGameButton);
        //Buttons
        Button rollDice = findViewById(R.id.rollDiceButton);
        hold = findViewById(R.id.holdButton);





        //Set the roll dice button.
        setRollDice(rollDice);

        //Set the restart button.
        setRestartGame(restartGame);

        //Set the hold button.
        setHoldGame();


        
    }

    /**
     * This method is used to set the hold button.
     */
    private void setHoldGame() {

        hold.setOnClickListener(view -> {

            //If the player is not hold, the player 1 is playing, else the player 2 is playing.
            if (game.invertedHold()) {
                //Change the background.
                switchBackground();

                //Update the current value of the player 1.
                game.sumDice("player1", RollDices.getRoundScore());
                RollDices.setRoundScore(0);

                //Update the current value of the player 1.
                TextView player1View = findViewById(R.id.player1Contador);
                player1View.setText(String.valueOf(game.getPuntuacion("player1")));



            } else {
                //Change the background.
                switchBackground();

                //Update the current value of the player 2.
                game.sumDice("player2", RollDices.getRoundScore());
                RollDices.setRoundScore(0);

                //Update the current value of the player 2.
                TextView player2View = findViewById(R.id.player2Contador);
                player2View.setText(String.valueOf(game.getPuntuacion("player2")));


            }

            //Change the background.
            switchBackground();
            
            //Check if the player has won.
            checkWinner();


            //Change the player.
            game.setHold(game.invertedHold());


            //Update the current value of the player 2.
            TextView currentValuePlayer1 = findViewById(R.id.currentValuePlayer1);
            currentValuePlayer1.setText(getResources().getString(R.string.numberZero));

            //Update the current value of the player 2.
            TextView currentValuePlayer2 = findViewById(R.id.currentValuePlayer2);
            currentValuePlayer2.setText(getResources().getString(R.string.numberZero));
        });
    }

    /**
     * This method is used to set the restart button.
     *
     * @param restartGame is the restart button.
     */
    private void setRestartGame(Button restartGame) {
        restartGame.setOnClickListener(view -> {
            game.deletePoints();

            //Update the current value of the player 2.
            TextView player2View = findViewById(R.id.player2Contador);
            player2View.setText(getResources().getString(R.string.numberZero));

            //Update the current value of the player 1.
            TextView currentValuePlayer2 = findViewById(R.id.currentValuePlayer2);
            currentValuePlayer2.setText(getResources().getString(R.string.numberZero));

            //Update the current value of the player 1.
            TextView player1View = findViewById(R.id.player1Contador);
            player1View.setText(getResources().getString(R.string.numberZero));

            //Update the current value of the player 1.
            TextView currentValuePlayer1 = findViewById(R.id.currentValuePlayer1);
            currentValuePlayer1.setText(getResources().getString(R.string.numberZero));


            //Set the text views invisible.
            winnerTextView1.setVisibility(View.INVISIBLE);
            winnerTextView2.setVisibility(View.INVISIBLE);
        });
    }

    /**
     * This method is used to set the roll dice button.
     *
     * @param rollDice is the roll dice button.
     */
    private void setRollDice(Button rollDice) {
        rollDice.setOnClickListener(view -> {
            //Change the background.
            switchBackground();
            
            //If the player is not hold, the player 1 is playing, else the player 2 is playing.
            if (game.invertedHold()) {

                game.play();

                //Update the current value of the player 1.
                TextView player1View = findViewById(R.id.currentValuePlayer1);
                player1View.setText(String.valueOf(RollDices.getRoundScore()));
            } else {
                
                game.play();

                //Update the current value of the player 2.
                TextView player2View = findViewById(R.id.currentValuePlayer2);
                player2View.setText(String.valueOf(RollDices.getRoundScore()));
            }

            //Check if the player has won.
            checkWinner();
        });
    }
    /**
     * This method is used to switch the background.
     */
    private void switchBackground() {

        TextView player2View = findViewById(R.id.player2);
        TextView player1View = findViewById(R.id.player1);
        TextView player1Contador = findViewById(R.id.player1Contador);
        TextView player2Contador = findViewById(R.id.player2Contador);

        MaterialToolbar bakgroundLeft = findViewById(R.id.materialToolbar1);
        MaterialToolbar bakgroundRight = findViewById(R.id.materialToolbar2);

        if (game.invertedHold()) {
            //Change the background.
            bakgroundLeft.setBackgroundColor(getResources().getColor(R.color.gray, getTheme()));
            bakgroundRight.setBackgroundColor(getResources().getColor(R.color.white, getTheme()));

            //Change the color of the text views.
            player1View.setTextColor(getResources().getColor(R.color.white, getTheme()));
            player2View.setTextColor(getResources().getColor(R.color.black, getTheme()));

            //Change the color of the text views.
            player1Contador.setTextColor(getResources().getColor(R.color.white, getTheme()));
            player2Contador.setTextColor(getResources().getColor(R.color.black, getTheme()));
        } else {
            //Change the background.
            bakgroundRight.setBackgroundColor(getResources().getColor(R.color.gray, getTheme()));
            bakgroundLeft.setBackgroundColor(getResources().getColor(R.color.white, getTheme()));

            //Change the color of the text views.
            player2View.setTextColor(getResources().getColor(R.color.white, getTheme()));
            player1View.setTextColor(getResources().getColor(R.color.black, getTheme()));

            //Change the color of the text views.
            player2Contador.setTextColor(getResources().getColor(R.color.white, getTheme()));
            player1Contador.setTextColor(getResources().getColor(R.color.black, getTheme()));
        }
    }
    /**
     * This method is used to check if the player has won.
     */
    private void checkWinner() {
        //Check if the player has won.
        String winner = game.winner();
        if (!winner.equalsIgnoreCase("")) {
            updateWinner(winner);
        }
    }

    /**
     * This method is used to update the winner.
     *
     * @param winner is the winner.
     */
    private void updateWinner(String winner) {
        //Set the text views visible.
        if (winner.equalsIgnoreCase("player1")) {
            winnerTextView1.setVisibility(View.VISIBLE);

        } else {
            winnerTextView2.setVisibility(View.VISIBLE);
        }
    }
}
package com.example.thepiggame.gameFun;

import java.util.HashMap;

/**
 * This class is the game.
 */
public class RollDices {

    private final HashMap<String,Integer> players = new HashMap<>(); //private String player1 = "player1", player2 = "player2";


    private boolean hold = false; //private boolean player1Playing = true, player2Playing = false;


    private static int roundScore = 0;  //private int player1Score = 0, player2Score = 0;

    /**
     * This constructor is used to initialize the players.
     */
    public RollDices(){
            players.put("player1", 0);
            players.put("player2", 0);
    }


    /**
     * This method is used to throw the dices.
     * @return the value of the dices.
     */
    public static int throwDices(){
        return (int) (Math.random() *6) + 1;
    }


    /**
     * This method is used to play the game.
     * If the player has not hold, the player is playing, else the player is not playing.
     */
    public void play(){
        int n;

        //If n is not 1, the player is playing, else the player is not playing.
        if(!((n = throwDices())==1)){
            roundScore += n;
        }else{
            this.setHold(this.invertedHold());
            roundScore=0;
        }

    }
    /**
     * This method is used to check if the player has won.
     */
    public String winner(){
        String winner = "";

            //Check if the players are not null.
            //Check if the player 1 or the player 2 has won.
            if(players.get("player1") != null && players.get("player1") >= 100){
                winner = "player1";
            }else if(players.get("player1") != null && players.get("player2") >= 100){
                winner = "player2";
            }

        //Return the winner.
        return winner;
    }

    /**
     * This method is used to sum the dice to the player.
     * @param player is the player.
     * @param roll is the value of the dice.
     */
    public void sumDice(String player, int roll){
        players.merge(player, roll, Integer::sum);
    }

    /**
     * This method is used to delete the points of the players.
     */
    public void deletePoints(){
       this.players.replace("player1", 0);
        this.players.replace("player2", 0);
    }

    /**
     * This method is used to set the player.
     */
    public void setHold(Boolean hold){
        this.hold = hold;
    }

    /**
     * This method is used to check if the player is hold.
     */
    public boolean invertedHold(){
        return !this.hold;
    }

    /**
     * This method is used to set the round score.
     * @param roundScore Current round score.
     */
    public static void setRoundScore(int roundScore) {
        RollDices.roundScore = roundScore;
    }

    /**
     * This method is used to get the round score.
     * @return Current round score.
     */
    public static int getRoundScore() {
        return roundScore;
    }

    /**
     * This method is used to get the score of the player.
     * @param player Player.
     * @return Score of the player.
     */
    public int getPuntuacion(String player){
        Integer score = players.get(player);
        if (score == null) {
            return 0;
        } else {
            return score;
        }
    }
}

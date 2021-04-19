package games.model;

import java.util.Date;

import games.util.Result;

public class Game {

    private Player player1;
    private Player player2;

    private boolean player1Win;
    private Result result;

    private Date date;
    private String score;

    public Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean isPlayer1Win() {
        return player1Win;
    }

    public void setPlayer1Win(boolean player1Win) {
        this.player1Win = player1Win;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
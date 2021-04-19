package games.model;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String username;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;

    public Player(){
        this.username = "Default";
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesDrawn = 0;
    }

    public Player(String username){
        this.username = username;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesDrawn = 0;
    }

    public Player(String username,int gamesPlayed,int gamesWon,int gamesLost,int gamesDrawn){
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesDrawn = gamesDrawn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGamesDrawn() {
        return gamesDrawn;
    }

    public void setGamesDrawn(int gamesDrawn) {
        this.gamesDrawn = gamesDrawn;
    }

    public void gameWon(){
        this.gamesWon++;
        incrementGamesPlayed();
    }

    public void incrementGamesPlayed(){
        this.gamesPlayed++;
    }

    public void gameDrawn(){
        this.gamesDrawn++;
        incrementGamesPlayed();
    }

    public void gameLost(){
        this.gamesLost++;
        incrementGamesPlayed();
    }
}
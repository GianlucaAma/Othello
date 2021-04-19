package games.controller;


import games.controller.Controller;
import games.main.MainApp;
import games.model.Game;
import games.model.Othello;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import games.util.Owner;
public class GameViewController extends Controller {

    Othello gameModel;
    Game currentGame;

    @FXML Button menuButton;
    @FXML Button restartButton;
    @FXML Text p1ScoreBox;
    @FXML Text p2ScoreBox;
    @FXML Text p1RemainingBox;
    @FXML Text p2RemainingBox;
    @FXML GridPane gameBoard;
    @FXML Label p1ScoreLabel;
    @FXML Label p2ScoreLabel;
    @FXML Text congratsText;

    @FXML public void initialize(){
        this.currentGame = MainApp.getCurrentGame();
        System.out.println("HERE");

        gameModel = Othello.getInstance();
        gameModel.initBoard();
        gameModel.turn.setValue(Owner.WHITE);

        loadPlayerNames();
        configureScoreBindings();
        configureRemainingBindings();
        setupBoard();

        p1RemainingBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if(Integer.parseInt(newValue) == 0){
                handleEndgameResult();
            }
        });

        congratsText.setVisible(false);
    }

    @FXML public void handleMenuButtonAction(ActionEvent event){
        mainApp.loadNewView("MainMenuView");
    }

    private void setupBoard(){
        loadBoardSquares();
        loadStartPosition();
    }

    private void loadBoardSquares(){
        for(int i = 0; i < gameModel.boardSize; i++){
            for(int j = 0; j < gameModel.boardSize; j++){
                OthelloBoardSquare square = new OthelloBoardSquare(i,j);
                gameBoard.add(square, i, j);
            }
        }

    }

    private void loadStartPosition(){
        for(int i = 0; i < this.gameModel.boardSize; i++){
            for(int j = 0; j < this.gameModel.boardSize; j++){
                OthelloPiece piece = new OthelloPiece();
                piece.ownerProperty().bind(gameModel.board[i][j]);
                gameBoard.add(piece, i, j);
            }
        }

    }

    public void handleRestartButtonAction(ActionEvent event){
        mainApp.setCurrentGame(currentGame.getPlayer1(), currentGame.getPlayer2());

        gameModel.initBoard();

        gameModel.turn.setValue(Owner.WHITE);
    }

    private void loadPlayerNames(){
        p1ScoreLabel.setText(currentGame.getPlayer1().getUsername());
        p2ScoreLabel.setText(currentGame.getPlayer2().getUsername());
    }

    private void configureScoreBindings(){
        p1ScoreBox.textProperty().bind(gameModel.getScore(Owner.WHITE).asString());
        p2ScoreBox.textProperty().bind(gameModel.getScore(Owner.BLACK).asString());

    }

    private void configureRemainingBindings(){
        p1RemainingBox.textProperty().bind(gameModel.getTurnsRemaining(Owner.WHITE).asString());
        p2RemainingBox.textProperty().bind(gameModel.getTurnsRemaining(Owner.BLACK).asString());
    }

    private void handleEndgameResult(){
        congratsText.setVisible(true);

        int p1Score = Integer.parseInt(p1ScoreBox.getText());
        int p2Score = Integer.parseInt(p2ScoreBox.getText());

        if(p1Score > p2Score){
            congratsText.setText("Congratulations "+currentGame.getPlayer1().getUsername() +
                    "your win has been added to your stats");
        }else if(p2Score > p1Score){
            congratsText.setText("Congratulations "+currentGame.getPlayer2().getUsername() +
                    "your win has been added to your stats");
        }
    }
}
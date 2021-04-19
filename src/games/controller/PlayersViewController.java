package games.controller;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import games.controller.Controller;
import games.model.Player;
import games.util.PreferenceFileHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class PlayersViewController extends Controller {

    @FXML
    ChoiceBox<String> player1Selection;
    @FXML
    ChoiceBox<String> player2Selection;
    @FXML
    Button playButton;
    @FXML
    Button backButton;
    @FXML
    TextField usernameInputField;
    Player chosenPlayer1 = null;
    Player chosenPlayer2 = null;


    @FXML
    protected void initialize() {
        prefFile = new PreferenceFileHelper();
        loadPlayerList();

        player1Selection.getSelectionModel().selectFirst();
        player2Selection.getSelectionModel().selectLast();
    }

    private void loadPlayerList(){
        if(player1Selection.getItems().isEmpty() == false){
            player1Selection.getItems().clear();
            player2Selection.getItems().clear();


        }

        for (int i = 0; i < prefFile.getPlayerData().size(); i++) {
            player1Selection.getItems().add(prefFile.getPlayerData().get(i).getUsername());
            player2Selection.getItems().add(prefFile.getPlayerData().get(i).getUsername());
        }
    }

    @FXML
    private void handlePlayButtonAction(ActionEvent event) {
        if(samePlayerChosen()){
            //show tooltip
            System.out.println("Same Player chosen,choose different opponent");
        }else{
            prefFile.savePlayerDataToFile(prefFile.getPlayerFilePath());
            mainApp.setCurrentGame(getChosenPlayer1(),getChosenPlayer2());
            mainApp.loadNewView("GameView");
        }

    }

    @FXML
    private void handleBackButtonAction(ActionEvent event) {
        prefFile.savePlayerDataToFile(prefFile.getPlayerFilePath());
        mainApp.loadNewView("MainMenuView");
    }

    @FXML
    private void handleNewPlayerButtonAction(ActionEvent event){
        if(getUsernameInput().isEmpty()){

        }else{
            prefFile.getPlayerData().add(new Player(getUsernameInput()));
            clearUsernameInputField();
            loadPlayerList();
        }
    }

    private String getUsernameInput(){
        return usernameInputField.getText();
    }

    private void clearUsernameInputField(){
        usernameInputField.clear();
    }

    private Player getChosenPlayer1(){
        String choice = player1Selection.getSelectionModel().getSelectedItem();

        for (int i = 0; i < prefFile.getPlayerData().size(); i++) {
            if(prefFile.getPlayerData().get(i).getUsername() == choice){
                return prefFile.getPlayerData().get(i);
            }
        }
        return null;
    }

    private Player getChosenPlayer2(){
        String choice = player2Selection.getSelectionModel().getSelectedItem();

        for (int i = 0; i < prefFile.getPlayerData().size(); i++) {
            if(prefFile.getPlayerData().get(i).getUsername() == choice){
                return prefFile.getPlayerData().get(i);
            }
        }
        return null;
    }
	/*private boolean samePlayerChosen(){
		if(player1Selection.)
	} */

    private boolean samePlayerChosen(){
        if(player1Selection.getSelectionModel().getSelectedItem() == player2Selection.getSelectionModel().getSelectedItem()){
            return true;
        }else{
            return false;
        }
    }
}
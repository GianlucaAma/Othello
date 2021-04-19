package games.controller;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import games.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class MainMenuController extends Controller {

    @FXML Button playButton;
    @FXML Button learnButton;
    @FXML Button statsButton;
    @FXML Button exitButton;

    @FXML
    private void handlePlayButtonAction(ActionEvent event){
        mainApp.loadNewView("PlayersView");
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event){
        mainApp.exitApp();
    }

    @FXML
    private void handleStatsButtonAction(ActionEvent event){
        mainApp.loadNewView("StatsView");
    }

    @FXML
    private void handleLearnButtonAction(ActionEvent event){
        mainApp.loadNewView("LearnView");
    }
}
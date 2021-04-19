package games.controller;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import games.controller.Controller;
import games.model.Player;
import games.util.PreferenceFileHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class StatsViewController extends Controller {

    @FXML
    ChoiceBox<String> playerChoiceBox;
    @FXML
    Label title;
    @FXML Button doneButton;
    @FXML PieChart statsPieChart;
    @FXML Label playedEntryLabel;
    @FXML Label wonEntryLabel;
    @FXML Label percentageEntryLabel;

    @FXML
    protected void initialize() {
        prefFile = new PreferenceFileHelper();
        loadPlayerList();
        loadPieChart();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void loadPlayerList(){
        for (int i = 0; i < prefFile.getPlayerData().size(); i++) {
            playerChoiceBox.getItems().add(prefFile.getPlayerData().get(i).getUsername());
        }
        playerChoiceBox.getSelectionModel().selectFirst();

        playerChoiceBox.valueProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable,Object oldValue,Object newValue){
                loadPieChart();
            }
        });
    }

    @FXML
    private void handleDoneButtonAction(ActionEvent event){
        mainApp.loadNewView("MainMenuView");
    }

    @FXML
    private void handleChoiceBoxAction(ActionEvent event){
        loadPieChart();
    }

    @FXML
    private void loadPieChart(){
        Player chosenPlayer = null;
        for (int i = 0; i < prefFile.getPlayerData().size(); i++) {
            if(prefFile.getPlayerData().get(i).getUsername() == playerChoiceBox.getSelectionModel().getSelectedItem()){
                chosenPlayer = prefFile.getPlayerData().get(i);
            }
        }
        System.out.println("choen player games won= "+chosenPlayer.getGamesWon());
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Won", chosenPlayer.getGamesWon()),
                        new PieChart.Data("Lost", chosenPlayer.getGamesLost()),
                        new PieChart.Data("Drawn", chosenPlayer.getGamesDrawn())
                );
        statsPieChart.setData(pieChartData);
        playedEntryLabel.setText(String.valueOf(chosenPlayer.getGamesPlayed()));
        wonEntryLabel.setText(String.valueOf(chosenPlayer.getGamesWon()));
        percentageEntryLabel.setText(String.valueOf(((double)chosenPlayer.getGamesWon()/(double)chosenPlayer.getGamesPlayed())*100));
    }
}

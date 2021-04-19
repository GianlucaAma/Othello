package games.controller;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import games.main.MainApp;
import javafx.fxml.FXML;
import games.util.PreferenceFileHelper;

public class Controller {

    protected MainApp mainApp;
    protected PreferenceFileHelper prefFile;

    @FXML
    protected void initialize() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public MainApp getMainApp(){
        return mainApp;
    }


}
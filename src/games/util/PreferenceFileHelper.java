package games.util;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import java.io.File;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import games.controller.PlayersViewController;
import games.model.Game;
import games.model.Player;
import games.model.PlayerListWrapper;


public class PreferenceFileHelper {
    public static ObservableList<Player> playerData = FXCollections
            .observableArrayList();
    public static ObservableList<Game> gameData = FXCollections
            .observableArrayList();

    public PreferenceFileHelper(){

        File prefFile = getPlayerFilePath();
        if(prefFile != null){
            loadPlayerDataFromFile(prefFile);
        }else{
            prefFile = new File(System.getProperty("user.home") + File.separator + "MainApp.xml");
            setPlayerFilePath(prefFile);
            System.out.println("setting file path to :"+prefFile.getAbsolutePath());
        }
    }

    public ObservableList<Player> getPlayerData(){
        return playerData;
    }

    public ObservableList<Game> getGameData(){
        return gameData;
    }

    public File getPlayerFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(PlayersViewController.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            System.out.println("returning null in getPlayerFilePath");
            return null;
        }
    }

    public void setPlayerFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(PlayersViewController.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }

    public void loadPlayerDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PlayerListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PlayerListWrapper playerWrapper = (PlayerListWrapper) um.unmarshal(file);
            //GameListWrapper gameWrapper = (GameListWrapper) um.unmarshal(file);

            playerData.clear();
            //	gameData.clear();
            playerData.addAll(playerWrapper.getPlayers());
            //gameData.addAll(gameWrapper.getGames());


            // Save the file path to the registry.
            setPlayerFilePath(file);

        } catch (Exception e) { // catches ANY exception
			/*
			 * Alert alert = new Alert(AlertType.ERROR);
			 * alert.setTitle("Error");
			 * alert.setHeaderText("Could not load data");
			 * alert.setContentText("Could not load data from file:\n" +
			 * file.getPath());
			 *
			 * alert.showAndWait();
			 */
            System.out.println("Could not load data from file");
        }
    }

    public void savePlayerDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PlayerListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PlayerListWrapper playerWrapper = new PlayerListWrapper();
            //	GameListWrapper gameWrapper = new GameListWrapper();

            playerWrapper.setPlayers(playerData);
            //gameWrapper.setGames(gameData);

            // Marshalling and saving XML to the file.
            m.marshal(playerWrapper, file);
            //m.marshal(gameWrapper, file);


            // Save the file path to the registry.
            setPlayerFilePath(file);
        } catch (Exception e) { // catches ANY exception
			/*
			 * Alert alert = new Alert(AlertType.ERROR);
			 * alert.setTitle("Error");
			 * alert.setHeaderText("Could not save data");
			 * alert.setContentText("Could not save data to file:\n" +
			 * file.getPath());
			 *
			 * alert.showAndWait();
			 */
            System.out.println("Could not save data to file: \n" + file.getPath());
        }
    }

    public void updateStats(String player1,String player2,Result result){
        if(result == Result.P1WIN){
            getPlayerFromUsername(player1).gameWon();
            getPlayerFromUsername(player2).gameLost();
        }else if(result == Result.P2WIN){
            getPlayerFromUsername(player2).gameWon();
            getPlayerFromUsername(player1).gameLost();
        }else{
            getPlayerFromUsername(player2).gameDrawn();
            getPlayerFromUsername(player1).gameDrawn();
        }

    }

    private Player getPlayerFromUsername(String username){
        for (int i = 0; i < getPlayerData().size(); i++) {
            if(getPlayerData().get(i).getUsername() == username){
                return getPlayerData().get(i);
            }
        }
        return null;
    }
}
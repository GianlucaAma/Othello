package games.main; /**
 * Created by gianlucaamato on 05/07/16.
 */
import java.io.IOException;            // IOException class
import games.controller.Controller;
import games.model.Game;
import games.model.Player;
import games.util.PreferenceFileHelper;
import javafx.application.Application; // JavaFX super class
import javafx.stage.Stage;             // Create the stage
import javafx.scene.Scene;             // Set the scene
import javafx.fxml.FXMLLoader;         // Allow FXML to be loaded
import javafx.scene.layout.AnchorPane; // AnchorPane is the base of your PersonViewer hierarchy
import javafx.scene.layout.BorderPane; // BorderPane is the base of your RootLayout hierarchy

public class MainApp extends Application {

    private Stage 	   primaryStage;   // Stage of GUI
    private BorderPane rootLayout;     // UI Root
    private static Game 	currentGame;
    private PreferenceFileHelper prefFile;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("MainApp");
        this.primaryStage.setResizable(false);


        prefFile = new PreferenceFileHelper();

        // Load FXML files and display in GUI
        initRootLayout();
        //showMainMenuView();
        loadNewView("MainMenuView");

    }

    public void initRootLayout(){
        try{
            // Load RootLayout.fxml from 'view' package
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/app/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            // Set the scene
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadNewView(String newView){
        try{
            // Load mainMenuView.fxml from 'view' package
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/app/view/"+newView+".fxml"));
            AnchorPane mainMenuView = (AnchorPane) loader.load();
            // Set PersonView in centre of RootLayout
            rootLayout.setCenter(mainMenuView);

            // Give the controller access to MainApp
            Controller controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public void exitApp(){
        primaryStage.close();
    }

    public void setCurrentGame(Player player1, Player player2){
        MainApp.currentGame = new Game(player1,player2);

    }

    public static Game getCurrentGame(){
        return MainApp.currentGame;
    }

    public PreferenceFileHelper getPrefFile(){
        return prefFile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
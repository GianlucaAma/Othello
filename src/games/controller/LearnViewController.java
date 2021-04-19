package games.controller;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import games.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class LearnViewController extends Controller {

    final String[] textPages = new String[]{
            "Object of the Game\n"
                    + "The object of the game is to have the majority of your colour"
                    + "discs on the board at the end of the game",
            "A Minute To Learn\n"
                    + "Each player takes 32 discs and chooses one colour to use throughout the game."
                    +"Black places two black discs and White places two white discs"
                    + ". The game always begins with this setup."
                    + "A move consists of 'outflanking' your opponent's disc(s),"
                    + " then flipping the outflanked disc(s) to your colour."
                    +"To outflank means to place a disc on the board so that your"
                    + " opponent's row (or rows) of disc(s) is bordered at each end"
                    + " by a disc of your colour. (A 'row' may be made up of one or more discs).",
            "MainApp Rules\n"
                    + "1. Black always moves first.\n"
                    + "2. If on your turn you cannot outflank and flip at least "
                    + "one opposing disc, your turn is forfeited and your"
                    + " opponent moves again. However, if a move is available to you,"
                    + " you may not forfeit your turn.\n"
                    + "3. A disc may outflank any number of discs in one or more rows"
                    + " in any number of directions at the same time - horizontally,"
                    + " vertically or diagonally. (A row is defined as one or more discs"
                    + " in a continuous straight line )."
    };

    @FXML Label title;
    @FXML Button doneButton;
    @FXML Pagination paginationBox;

    @FXML
    public void initialize(){
        paginationBox.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                if (pageIndex >= textPages.length) {
                    return null;
                } else {
                    return createPage(pageIndex);
                }
            }
        });
    }

    @FXML
    private void handleDoneButtonAction(ActionEvent event){
        mainApp.loadNewView("MainMenuView");
    }

    public VBox createPage(int pageIndex) {
        VBox box = new VBox(5);
        int page = pageIndex * itemsPerPage();
        for (int i = page; i < page + itemsPerPage(); i++) {
            TextArea text = new TextArea(textPages[i]);
            text.setWrapText(true);
            box.getChildren().add(text);
        }
        return box;
    }

    public int itemsPerPage() {
        return 1;
    }
}

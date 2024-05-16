package Searching;

import ClubPlayers.Player;
import MainPage.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchResultsController implements Initializable {

    @FXML
    private ListView<HBox> searchResultsListView;

    private static Stage primaryStage;

    private static List<Player> playerList;

    public void showMain(){
        SceneChanger.showMainPage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(Player p:playerList) {

            Label label = new Label(p.getName());
            label.setPrefWidth(160);
            label.setPrefHeight(50);
            label.setAlignment(Pos.CENTER_LEFT);

            Button button = new Button("Profile");
            button.setPrefWidth(50);
            button.setPrefHeight(50);
            button.setAlignment(Pos.CENTER);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    PlayerProfileController.setPlayer(p);
                   SceneChanger.showPlayerDetails();
                }
            });

            Region r1 = new Region();
            HBox.setHgrow(r1, Priority.ALWAYS);


            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(label, r1 , button);
            searchResultsListView.getItems().add(hBox);
        }
    }

    public static List<Player> getPlayerList() {
        return playerList;
    }
    public static void setPlayerList(List<Player> playerList) {
        SearchResultsController.playerList = playerList;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void setPrimaryStage(Stage primaryStage) {
        SearchResultsController.primaryStage = primaryStage;
    }

}

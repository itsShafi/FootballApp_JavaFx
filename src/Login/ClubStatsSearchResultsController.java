package Login;

import ClubPlayers.Player;
import MainPage.Main;
import MainPage.SceneChanger;
import Searching.PlayerProfileController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClubStatsSearchResultsController implements Initializable {

    @FXML
    private ListView<HBox> clubStatsSearchResultsListView;

    private static Stage stage = new Stage();

    private List<Player> playerList = ClubStatsController.searchedPlayerList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clubStatsSearchResultsListView.setBackground(new Background(new BackgroundFill(Color.rgb(44, 47, 51), CornerRadii.EMPTY, Insets.EMPTY)));
        for (Player p: playerList) {
            try {
                Image image = new Image(Main.class.getResourceAsStream("/photos/" + p.getName() + ".png"));
                p.setImage(image);
            } catch (Exception e) {
                Image image = new Image(Main.class.getResourceAsStream("/photos/Sample Player.png"));
                p.setImage(image);
            }


            ImageView playerImage = new ImageView(p.getImage());
            playerImage.setFitHeight(60);
            playerImage.setFitWidth(60);

            Button button = new Button("Profile");
            button.setPrefWidth(60);
            button.setPrefHeight(60);
            button.setBackground(new Background(new BackgroundFill(Color.rgb(44, 47, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            button.setTextFill(Color.WHITE);
            button.setAlignment(Pos.CENTER);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    PlayerProfileController.setPlayer(p);
                    SceneChanger.showPlayerDetails();
                }
            });

            Label label = new Label(p.getName());
            label.setPrefWidth(150);
            label.setPrefHeight(60);
            label.setBackground(new Background(new BackgroundFill(Color.rgb(44, 47, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            label.setTextFill(Color.WHITE);
            label.setAlignment(Pos.CENTER_LEFT);

            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);


            HBox hBox = new HBox(20);
            hBox.setBackground(new Background(new BackgroundFill(Color.rgb(44, 47, 51), CornerRadii.EMPTY, Insets.EMPTY)));
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll( label, region1,  button);
            clubStatsSearchResultsListView.getItems().add(hBox);
            System.out.println(hBox.getParent());
        }

    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
    public List<Player> getPlayerList() {
        return playerList;
    }

    public static void setStage(Stage stage) {
        ClubStatsSearchResultsController.stage = stage;
    }
    public static Stage getStage() {
        return stage;
    }
}

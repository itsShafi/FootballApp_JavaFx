package Login;

import ClubPlayers.Club;
import ClubPlayers.Player;
import MainPage.Main;
import MainPage.SceneChanger;
import Searching.PlayerProfileController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import server.Order;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClubManagerController implements Initializable {

    @FXML
    private Label clubManagerNameLabel;
    @FXML
    private AnchorPane clubManagerAnchorPane1;
    @FXML
    private Label clubManagerTotalPlayersLabel;
    @FXML
    private ImageView clubManagerImage;


    private ListView<HBox> roasterListView = new ListView<>();
    private ListView<HBox> marketListView = new ListView<>();

    private double maxSalary, maxHeight;
    private int maxAge;
    private Club club;

    private ObservableList<Player> playerList = FXCollections.observableArrayList();
    private ObservableList<Player> availablePlayerList = FXCollections.observableArrayList();

    private ObservableList<HBox> hBoxObservablePlayerList = FXCollections.observableArrayList();
    private ObservableList<HBox> hBoxObservableAvailablePlayerList = FXCollections.observableArrayList();



    private ClubLoginController loginController;

    public void init(ClubLoginController loginController , String clubName) {
        this.loginController = loginController;
        clubManagerNameLabel.setText(clubName);
        Image img = new Image(Main.class.getResourceAsStream("/photos/" + clubName + ".png"));
        clubManagerImage.setImage(img);
    }

    public void actionLogout() {

        try {
            loginController.getNetworkUtil().closeConnection();
            Stage stage = (Stage)clubManagerNameLabel.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SceneChanger.showLoginPage();
    }


    public void clubPlayers(ActionEvent actionEvent) {
        clubManagerAnchorPane1.getChildren().clear();
        clubManagerAnchorPane1.getChildren().add(roasterListView);
    }

   public void clubSearchPlayers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/ClubSearchPlayers.fxml"));
            Parent root = loader.load();
            ClubSearchPlayersController controller = loader.getController();
            controller.setList(playerList,club.getName());
            clubManagerAnchorPane1.getChildren().clear();
            clubManagerAnchorPane1.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roasterListView.setPrefHeight(285);
        roasterListView.setPrefWidth(485);
        roasterListView.setItems(hBoxObservablePlayerList);
        marketListView.setPrefHeight(285);
        marketListView.setPrefWidth(485);
        marketListView.setItems(hBoxObservableAvailablePlayerList);
    }


    public void showMarket(){
        clubManagerAnchorPane1.getChildren().clear();
        clubManagerAnchorPane1.getChildren().add(marketListView);
    }

    public void clubStats(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login/ClubStats.fxml"));
            Parent root = loader.load();
            ClubStatsController controller = loader.getController();
            controller.set(playerList, maxAge, maxHeight, maxSalary,  playerList.size(), club.getName());
            clubManagerAnchorPane1.getChildren().clear();
            clubManagerAnchorPane1.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Club getClub() {
        return club;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList.clear();
        for (Player p: playerList) {
            this.playerList.add(p);
        }
        observableListView(this.playerList, hBoxObservablePlayerList);
    }

    private void observableListView(List<Player> playerList, ObservableList<HBox> hBoxObservablePlayerList) {
        hBoxObservablePlayerList.clear();
        for (Player p : playerList) {
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

            Button detailsButton = new Button("Profile");
            detailsButton.setPrefWidth(60);
            detailsButton.setPrefHeight(60);
            detailsButton.setAlignment(Pos.CENTER);
            detailsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    PlayerProfileController.setPlayer(p);
                    SceneChanger.showPlayerDetails();
                }
            });

            Button sellButton = new Button();
            if (hBoxObservablePlayerList == this.hBoxObservablePlayerList) {
                p.setClub(club.getName());
                sellButton(sellButton, p);
            }
            else {
                buyButton(sellButton, p);
            }

            clubManagerTotalPlayersLabel.setText("Total Players\n" + playerList.size());


            Label label = new Label(p.getName());
            label.setPrefWidth(150);
            label.setPrefHeight(60);
            label.setAlignment(Pos.CENTER_LEFT);


            Region region1 = new Region();
            Region region2 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);
            HBox.setHgrow(region2, Priority.ALWAYS);

            HBox hBox = new HBox(12);
            hBox.getChildren().addAll(label, region1, detailsButton, region2, sellButton);
            hBoxObservablePlayerList.add(hBox);

        }
    }

    private void sellButton(Button sellButton, Player p) {
        sellButton.setText("Sell");
        sellButton.setPrefWidth(60);
        sellButton.setPrefHeight(60);
        sellButton.setAlignment(Pos.CENTER);
        sellButton.setOnAction(event -> {
            if (sellButton.getText().equals("Sell")) {
                try {
                    sellButton.setText("For Sale");
                    Order order = new Order();
                    order.setPlayerName(p.getName());
                    order.setOrder("Sell");
                    loginController.getNetworkUtil().write(order);
                } catch (IOException e) {
                    System.out.println("sell error");
                }
            }
        });
    }
    private void buyButton(Button sellButton, Player p) {
        sellButton.setText("Buy");
        sellButton.setPrefWidth(60);
        sellButton.setPrefHeight(60);
        sellButton.setAlignment(Pos.CENTER);
        sellButton.setOnAction(event -> {
            try {
                Order order = new Order();
                order.setPlayerName(p.getName());
                order.setOrder("Buy");
                order.setClubName(club.getName());
                loginController.getNetworkUtil().write(order);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setAvailablePlayerList(List<Player> availablePlayerList) {
        this.availablePlayerList.clear();
        for (Player p: availablePlayerList) {
            if (this.availablePlayerList.contains(p)) {
                continue;
            }
            if (!p.getClub().equals(club.getName())) {
                // System.out.println(club.getName() + " -> " + p.getName());
                this.availablePlayerList.add(p);
            }

        }
        observableListView(this.availablePlayerList, hBoxObservableAvailablePlayerList);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public double getMaxSalary() {
        return maxSalary;
    }
    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMaxHeight() {
        return maxHeight;
    }
    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

}



package Login;

import ClubPlayers.Player;
import MainPage.SceneChanger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.net.URL;
import java.util.*;

public class ClubStatsController implements Initializable {

    @FXML
    private Button clubInfoMaxAgePlayerButton;
    @FXML
    private Button clubInfoMaxHeightPlayerButton;
    @FXML
    private Button clubInfoMaxSalaryPlayerButton;
    @FXML
    private Label clubInfoTotalPlayersLabel;
    @FXML
    private Label clubInfoAvailableFinancesLabel;
    @FXML
    private ListView<HBox> clubInfoCountryListView;
    @FXML
    private ImageView clubInfoImageView;
    @FXML
    private Label clubInfoClubName;

    private ObservableList<Player> playerList = FXCollections.observableArrayList();
    private ObservableList<HBox> hBoxObservablePlayerList = FXCollections.observableArrayList();

    public static List<Player> searchedPlayerList = new ArrayList<>();

    double maxSalary, maxHeight, availableFinances;
    int maxAge, totalPlayers;

    public void showMaxAgePlayer() {
        searchedPlayerList.clear();
        for (Player p: playerList) {
            if (p.getAge() == maxAge) {
                searchedPlayerList.add(p);
            }
        }
        SceneChanger.showClubStatsSearchResults();
    }
    public void showMaxHeightPlayer() {
        searchedPlayerList.clear();
        for (Player p: playerList) {
            if (p.getHeight() == maxHeight) {
                searchedPlayerList.add(p);
            }
        }
        SceneChanger.showClubStatsSearchResults();
    }
    public void showMaxSalaryPlayer() {
        searchedPlayerList.clear();
        for (Player p: playerList) {
            if (p.getWeeklySalary() == maxSalary) {
                searchedPlayerList.add(p);
            }
        }
        SceneChanger.showClubStatsSearchResults();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clubInfoCountryListView.setItems(hBoxObservablePlayerList);
    }

    public void set(ObservableList<Player> playerList, int maxAge, double maxHeight, double maxSalary, int totalPlayers, String clubName) {
        for (Player p: playerList) {
            this.playerList.add(p);
        }
        observableListView();
        this.maxAge = maxAge;
        this.maxHeight = maxHeight;
        this.maxSalary = maxSalary;
        this.availableFinances = availableFinances;
        this.totalPlayers = totalPlayers;

        try {
            clubInfoImageView.setImage(new Image(getClass().getResourceAsStream("/photos/" + clubName + ".png")));
        }
        catch (Exception e) {
            //clubInfoImageView.setImage(new Image(getClass().getResourceAsStream("/photos/Icon.png")));
        }

        clubInfoClubName.setText(clubName);
        clubInfoTotalPlayersLabel.setText("Total Players: " + this.totalPlayers);

    }


    private void observableListView() {
        hBoxObservablePlayerList.clear();
        HashMap<String, Integer> countryPlayerCount = new HashMap();

        // Getting the available countries
        List<String> countryNames = new ArrayList();
        for (Player p : playerList) {
            if (!countryNames.contains(p.getCountry())) {
                countryNames.add(p.getCountry());
            }
        }
        // Creating Map Elements
        for (String c : countryNames) {
            countryPlayerCount.put(c, 0);
        }

        // Counting Players Based On Country
        for (Player p : playerList) {
            countryPlayerCount.put(p.getCountry(), countryPlayerCount.get(p.getCountry())+1);
        }

        // Printing The Counts
        for (Map.Entry<String, Integer> c : countryPlayerCount.entrySet()) {
            String countryName = c.getKey();
            int playerCount = c.getValue();


            Label countryLabel = new Label(countryName);
            countryLabel.setPrefHeight(30);
            countryLabel.setPrefWidth(70);

            Label countLabel = new Label("Player Count: " + playerCount);
            countLabel.setPrefWidth(100);
            countLabel.setPrefHeight(30);

            Region region1 = new Region();
            HBox.setHgrow(region1, Priority.ALWAYS);


            HBox hBox = new HBox(15);
            hBox.getChildren().addAll( countryLabel, region1, countLabel);
            hBoxObservablePlayerList.add(hBox);
        }
    }



}

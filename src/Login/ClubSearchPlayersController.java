package Login;

import ClubPlayers.Player;
import MainPage.Data;
import MainPage.SceneChanger;
import Searching.SearchPlayers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClubSearchPlayersController implements Initializable {
    @FXML
    private TextField searchPlayersByNameTextField;
    @FXML
    private TextField searchPlayersLowSalaryTextField;
    @FXML
    private TextField searchPlayersHighSalaryTextField;
    @FXML
    private ChoiceBox<String>  searchPlayersByCountryChoiceBox;
    @FXML
    private ChoiceBox<String>  searchPlayersByPositionChoiceBox;

    private SearchPlayers objSearchPlayers = new SearchPlayers();

    private List<Player> playerList = new ArrayList<>();
    public static List<Player> playerListFound = new ArrayList<>();
    String clubName;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchPlayersByPositionChoiceBox.getItems().addAll(Data.positions);
        searchPlayersByPositionChoiceBox.setValue("GoalKeeper");
        searchPlayersByCountryChoiceBox.getItems().addAll(Data.countries);
        searchPlayersByCountryChoiceBox.setValue("Spain");

    }

    public void clubSearchPlayersByName(ActionEvent e) {
        String name = searchPlayersByNameTextField.getText();
        playerListFound = objSearchPlayers.SearchByName(name);
        ClubSearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showClubSearchResults();
    }
    public void clubSearchPlayersByCountry(ActionEvent e) {
        String country = searchPlayersByCountryChoiceBox.getValue();

        playerListFound = objSearchPlayers.SearchByClubAndCountry(country,clubName);
        ClubSearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showClubSearchResults();
    }
    public void clubSearchPlayersByPosition(ActionEvent e) {
        String pos = searchPlayersByPositionChoiceBox.getValue();
        playerListFound = objSearchPlayers.SearchByPosition(pos);
        ClubSearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showClubSearchResults();
    }
    public void clubSearchPlayersBySalaryRange(ActionEvent e){
        double LowSal  = Double.parseDouble(searchPlayersLowSalaryTextField.getText());
        double HighSal = Double.parseDouble(searchPlayersHighSalaryTextField.getText());

        for(Player p:playerList ) {
            if(LowSal<=p.getWeeklySalary() && HighSal>=p.getWeeklySalary()) {
                playerListFound = objSearchPlayers.SearchBySalaryRange(LowSal , HighSal);
            }
        }
        ClubSearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showClubSearchResults();
    }


    public void setList(ObservableList<Player> playerList,String clubName) {

       for(Player p:playerList){
            this.playerList.add(p);
        }
        objSearchPlayers.setsplayers(this.playerList);
        this.clubName = clubName ;
    }

}

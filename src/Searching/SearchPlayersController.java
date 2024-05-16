package Searching;

import ClubPlayers.Player;
import MainPage.Data;
import MainPage.Main;
import MainPage.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPlayersController implements Initializable {
    @FXML
    private TextField searchPlayersByNameTextField;
    @FXML
    private TextField searchPlayersLowSalaryTextField;
    @FXML
    private TextField searchPlayersHighSalaryTextField;
    @FXML
    private ChoiceBox<String>  searchPlayersByCountryChoiceBox;
    @FXML
    private ChoiceBox<String>  searchPlayersByClubChoiceBox;
    @FXML
    private ChoiceBox<String>  searchPlayersByPositionChoiceBox;


    String clubName;
    private Main main;
    private SearchPlayers objSearchPlayers = new SearchPlayers();
    private SearchClubs objSearchClubs = new SearchClubs();

    private List<Player> playerList = Main.getPlayerList();
    public static List<Player> playerListFound = new ArrayList<>();


    public void showMainPage() {
        SceneChanger.showMainPage();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        searchPlayersByPositionChoiceBox.getItems().addAll(Data.positions);
        searchPlayersByPositionChoiceBox.setValue("GoalKeeper");
        searchPlayersByCountryChoiceBox.getItems().addAll(Data.countries);
        searchPlayersByCountryChoiceBox.setValue("Spain");
        searchPlayersByClubChoiceBox.getItems().add("ANY");
        searchPlayersByClubChoiceBox.getItems().addAll(Data.clubs);
        searchPlayersByClubChoiceBox.setValue("ANY");
    }

    public void searchPlayersByName(ActionEvent e) {
        String name = searchPlayersByNameTextField.getText();
        playerListFound = objSearchPlayers.SearchByName(name);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }
    public void searchPlayersByCountryAndClub(ActionEvent e) {
        String country = searchPlayersByCountryChoiceBox.getValue();
        String club = searchPlayersByClubChoiceBox.getValue();
        playerListFound = objSearchPlayers.SearchByClubAndCountry(country,club);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }
    public void searchPlayersByPosition(ActionEvent e) {
        String pos = searchPlayersByPositionChoiceBox.getValue();
        playerListFound = objSearchPlayers.SearchByPosition(pos);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }
    public void searchPlayersBySalaryRange(ActionEvent e){
        double LowSal  = Double.parseDouble(searchPlayersLowSalaryTextField.getText());
        double HighSal = Double.parseDouble(searchPlayersHighSalaryTextField.getText());

        for(Player p:playerList ) {
            if(LowSal<=p.getWeeklySalary() && HighSal>=p.getWeeklySalary()) {
                playerListFound = objSearchPlayers.SearchBySalaryRange(LowSal , HighSal);
            }
        }
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }


    public void setMain(Main main) {
        this.main = main;
        objSearchPlayers.setsplayers(playerList);
    }

}

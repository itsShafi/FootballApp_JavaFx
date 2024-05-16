package Searching;

import ClubPlayers.Club;
import ClubPlayers.Player;
import MainPage.Main;
import MainPage.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.util.ArrayList;
import java.util.List;



public class SearchClubsController  {

    @FXML
    private RadioButton rArsenal,rChelsea,rLiverpool,rManCity,rManU;
    String clubName;
    private Main main;
    private SearchPlayers objSearchPlayers = new SearchPlayers();
    private SearchClubs objSearchClubs = new SearchClubs();

    private List<Player> playerList = Main.getPlayerList();
    private Club[] clubs = Main.getClubs();
    public static List<Player> playerListFound = new ArrayList<>();


    public void showMainPage() {
        SceneChanger.showMainPage();
    }


    public void getClub(ActionEvent e){

        if(rArsenal.isSelected()){
            clubName = rArsenal.getText();
        }
        else if(rChelsea.isSelected()){
            clubName = rChelsea.getText();
        }
        else if(rLiverpool.isSelected()){
            clubName = rLiverpool.getText();
        }
        else if(rManCity.isSelected()){
            clubName = rManCity.getText();
        }
        else if(rManU.isSelected()){
            clubName = rManU.getText();
        }
    }
    public void searchClubsByMaxSalary(ActionEvent e) {
        System.out.println(SearchClubs.getClubs().length);
        playerListFound = objSearchClubs.maxSalaryPlayers(clubName);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }
    public void searchClubsByMaxAge(ActionEvent e) {
        playerListFound = objSearchClubs.maxAgePlayers(clubName);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }
    public void searchClubsByMaxHeight(ActionEvent e) {
        playerListFound = objSearchClubs.maxHeightPlayers(clubName);
        SearchResultsController.setPlayerList(playerListFound);
        SceneChanger.showSearchResults();
    }


    public void setMain(Main main) {
        this.main = main;
        objSearchPlayers.setsplayers(playerList);
    }

}

package MainPage;


import ClubPlayers.Club;
import ClubPlayers.Player;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    Stage mainStage;
    private static List<Player> playerList;
    private static Club[] clubs;


    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage;
        SceneChanger.setStage(this.mainStage);
        SceneChanger.showMainPage();
    }


    public static void main(String[] args) {
        mainPlayerList();
        launch(args);
    }

    public static void mainPlayerList() {
        try {
            playerList = FileOperations.readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }


        clubs = new Club[5];
        for (int i = 0; i < 5; i++) {
            clubs[i] = new Club();
            clubs[i].setName(Data.clubs[i]);
        }

        // Adding the given players to the clubs
        for (Player p : playerList) {

            try {
                Image image = new Image(Main.class.getResourceAsStream("/photos/" + p.getName() + ".png"));
                p.setImage(image);
            } catch (Exception e) {
                Image image = new Image(Main.class.getResourceAsStream("/photos/Sample Player.png"));
                p.setImage(image);
            }

                for (int i = 0; i < 5; i++) {
                    if (p.getClub().equalsIgnoreCase(clubs[i].getName())) {
                        clubs[i].addPlayer(p);
                        break;
                    }
                }
            }
        }


        public static void setPlayerList (List < Player > playerList) {
            Main.playerList = playerList;
        }
        public static List<Player> getPlayerList () {
            return playerList;
        }

        public static void setClubs (Club[]clubs){
            Main.clubs = clubs;
        }
        public static Club[] getClubs () {
            return clubs;
        }


}


package MainPage;

import Login.ClubManagerController;
import Login.ClubLoginController;
import Login.ClubStatsSearchResultsController;
import Searching.SearchClubsController;
import Searching.SearchPlayersController;
import Searching.SearchResultsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    private static Stage stage;
    private static Main main;

    public static void showMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("MainPage.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));


            Image mainIcon = new Image(SceneChanger.class.getResourceAsStream("/photos/mainIcon.png"));
            stage.getIcons().add(mainIcon);
            stage.setTitle("Main Page");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginPage()  {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Login/ClubLogin.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            ClubLoginController controller = loader.getController();
            stage.setTitle("Login as a Club Manager");

            controller.setStage(stage);
            controller.connectToServer();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showSearchByPlayers() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Searching/SearchPlayers.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));

            SearchPlayersController controller = loader.getController();
            controller.setMain(main);

            stage.setTitle("Search By Players");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showSearchByClubs() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Searching/SearchClubs.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));

            SearchClubsController controller = loader.getController();
            controller.setMain(main);

            stage.setTitle("Search By Clubs");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showSearchResults() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Searching/SearchResults.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            SearchResultsController.setPrimaryStage(stage);

            stage.setTitle("Search Results");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showPlayerDetails() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Searching/PlayerProfile.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.setTitle("Player Profile");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // networking
    public static ClubManagerController showClubManager(ClubLoginController loginController, Stage stage , String clubName) {
        ClubManagerController controller = new ClubManagerController();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Login/ClubManager.fxml"));
            Parent root = loader.load();

            controller = loader.getController();
            controller.init(loginController , clubName);

            stage.setScene(new Scene(root));
            stage.setTitle(clubName +" Manager");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return controller;
    }


    public static void showClubSearchResults() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Login/ClubSearchResults.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            stage.setTitle("Search Results");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showClubStatsSearchResults() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SceneChanger.class.getResource("/Login/ClubStatsSearchResults.fxml"));
            Parent root = loader.load();

            Stage stage = ClubStatsSearchResultsController.getStage();
            stage.setScene(new Scene(root));

            Image iconMain = new Image(SceneChanger.class.getResourceAsStream("/photos/mainIcon.png"));
            stage.getIcons().add(iconMain);
            stage.setTitle("Search Results");

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void setMain(Main main) {
        SceneChanger.main = main;
    }
    public static Main getMain() {
        return main;
    }

    public static void setStage(Stage stage) {
        SceneChanger.stage = stage;
    }
    public static Stage getStage() {
        return stage;
    }


}

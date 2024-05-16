package Searching;

import ClubPlayers.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerProfileController implements Initializable {
    @FXML
    private ImageView playerProfilePlayerImageView;
    @FXML
    private Label playerProfileNameLabel;
    @FXML
    private Label playerProfileAgeLabel;
    @FXML
    private Label playerProfileHeightLabel;
    @FXML
    private Label playerProfileCountryLabel;
    @FXML
    private Label playerProfileClubLabel;
    @FXML
    private Label playerProfilePositionLabel;
    @FXML
    private Label playerProfileNumberLabel;
    @FXML
    private Label playerProfileWeeklySalaryLabel;

    private static Player p;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerProfilePlayerImageView.setImage(p.getImage());

        playerProfileNameLabel.setText(p.getName());
        playerProfileAgeLabel.setText(p.getAge() + " years");
        playerProfileHeightLabel.setText(p.getHeight() + " meters");
        playerProfileCountryLabel.setText(p.getCountry());
        playerProfileClubLabel.setText(p.getClub());
        playerProfilePositionLabel.setText(p.getPosition());
        playerProfileNumberLabel.setText(p.getNumber()+"");
        playerProfileWeeklySalaryLabel.setText(p.getWeeklySalary()+" dollars");

    }
    public static void setPlayer(Player p){
        PlayerProfileController.p=p;
    }
    public static Player getPlayer(){
        return p;
    }

}

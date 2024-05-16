package Login;

import MainPage.Main;
import MainPage.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.LoginDTO;
import server.NetworkUtil;

import java.io.IOException;


public class ClubLoginController {

    private Main main;

    @FXML
    private TextField userText;
    @FXML
    private TextField passwordText;
    @FXML
    private Button loginButton;

    private NetworkUtil networkUtil;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage(){
        return stage;
    }
    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }


    public void loginAction() {
        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        try {
            networkUtil.write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);
        passwordText.setText(null);
    }

    public void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33339;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
//        stage = (Stage)loginButton.getScene().getWindow();
        new ReadThread(this);
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showMainPage(){
        SceneChanger.showMainPage();
    }

}

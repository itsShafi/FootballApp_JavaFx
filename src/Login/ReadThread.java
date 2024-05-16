package Login;

import ClubPlayers.Club;
import ClubPlayers.Player;
import MainPage.SceneChanger;
import javafx.application.Platform;
import javafx.stage.Stage;
import server.Order;
import server.SendUpdate;
import server.LoginDTO;

import java.io.IOException;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final ClubLoginController loginController;
    private final Stage stage;

    public ReadThread(ClubLoginController loginController) {
        this.loginController = loginController;
        stage = loginController.getStage();
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            final ClubManagerController[] controller = {new ClubManagerController()};
            while (true) {
                Object o = loginController.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                LoginDTO loginDTO = (LoginDTO) o;

                                if (loginDTO.isStatus()) {
                                    try {
                                        controller[0] = SceneChanger.showClubManager(loginController, stage, loginDTO.getUserName());
                                        Order order = new Order();
                                        order.setOrder("Login");
                                        order.setClubName(loginDTO.getUserName());
                                        loginController.getNetworkUtil().write(order);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    loginController.showAlert();
                                }

                            }
                        });
                    }
                }
                if (o instanceof SendUpdate) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            SendUpdate sendUpdate = (SendUpdate) o;
                            Club club = sendUpdate.getClub();
                            List<Player> availablePlayerList = sendUpdate.getAvailablePlayerList();
                            controller[0].setClub(club);
                            List<Player> playerList = sendUpdate.getPlayerList();
//                                for (Player p: playerList) {
//                                    System.out.println(p.getName() + " " + p.getClub());
//                                }
                            controller[0].setPlayerList(playerList);
                            controller[0].setAvailablePlayerList(availablePlayerList);
                            controller[0].setMaxAge(sendUpdate.getMaxAge());
                            controller[0].setMaxHeight(sendUpdate.getMaxHeight());
                            controller[0].setMaxSalary(sendUpdate.getMaxSalary());

                            System.out.println(playerList.size());
                            System.out.println(club.getName());
                        }
                    });

                }

            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                loginController.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




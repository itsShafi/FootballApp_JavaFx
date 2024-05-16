package server;

import ClubPlayers.Club;
import ClubPlayers.Player;
import MainPage.Data;
import MainPage.FileOperations;
import MainPage.Main;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class PlayerMarket {

    public static void populatePlayerList() {
        try {
            List<Player> playerList = FileOperations.readFromFile();
            for (Player p: playerList) {
                Server.playerList.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Server.clubs = new Club[5];
        // Creating the clubs and setting their names
        for (int i = 0; i < 5; i++) {
            Server.clubs[i] = new Club();
            Server.clubs[i].setName(Data.clubs[i]);

            try {
                Server.clubs[i].setImage(new Image(Main.class.getResourceAsStream("/photos/" + Data.clubs[i] + ".png")));
            } catch (Exception e) {
                Server.clubs[i].setImage(new Image(Main.class.getResourceAsStream("/photos/mainIcon.png")));
            }
        }

        // Adding the given players to the clubs
        for (Player p : Server.playerList) {
            p.setFreeAgent(0);
            p.setPrice(p.getWeeklySalary()*200);
            try {
                Image image = new Image(Main.class.getResourceAsStream("/photos/" + p.getName() + ".png"));
                p.setImage(image);
            } catch (Exception e) {
                Image image = new Image(Main.class.getResourceAsStream("/photos/Sample Player.png"));
                p.setImage(image);
            }


            for (int i = 0; i < 5; i++) {
                if (p.getClub().equalsIgnoreCase(Server.clubs[i].getName())) {
                    Server.clubs[i].addPlayer(p);
                    break;
                }
            }
        }
    }

    public static int searchClub(String name) {
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            if (name.equalsIgnoreCase(Server.clubs[i].getName())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static int searchPlayer(String name) {
        int idx = 0;
        for (int i = 0; i < Server.playerList.size(); i++) {
            if (name.equalsIgnoreCase(Server.playerList.get(i).getName())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    public static boolean buy(String clubName, Player player) {
        int idx = searchClub(clubName);
        if (player.getPrice() <0 ) {
            return false;
        }
        idx = searchClub(player.getClub());
        Server.clubs[idx].removePlayer(player);

        idx = searchClub(clubName);
        player.setClub(clubName);
        player.setFreeAgent(0);
        Server.clubs[idx].addPlayer(player);
        Server.sendAvailablePlayerList = new ArrayList<>();
        if (Server.availablePlayerList.contains(player)) {
            Server.availablePlayerList.remove(player);
        }
        for (Player p: Server.availablePlayerList) {
            Server.sendAvailablePlayerList.add(p);
        }
        Server.send = true;
        return true;
    }

    public static void sell(Player player) {
        if (!Server.availablePlayerList.contains(player)) {
            Server.availablePlayerList.add(player);
        }

        Server.sendAvailablePlayerList = new ArrayList<>();

        int sIdx = searchPlayer(player.getName());
        Server.playerList.get(sIdx).setFreeAgent(1);

        int idx = searchClub(player.getClub());
        Player[] players = Server.clubs[idx].getPlayers();
        for (int i = 0; i < Server.clubs[idx].getPlayerNumber(); i++) {
            if (player.getName().equals(players[i].getName())) {
                players[i].setFreeAgent(1);
            }
        }
        Server.clubs[idx].setPlayers(players);
        for (Player p: Server.availablePlayerList) {
            Server.sendAvailablePlayerList.add(p);
        }
    }
}

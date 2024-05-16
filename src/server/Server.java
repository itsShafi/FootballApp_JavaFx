package server;


import ClubPlayers.Club;
import ClubPlayers.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    static ObservableList<Player> playerList = FXCollections.observableArrayList();
    static ObservableList<Player> availablePlayerList = FXCollections.observableArrayList();
    static List<Player> sendAvailablePlayerList = new ArrayList<>();
    static Club[] clubs;
    static boolean send = false;

    private ServerSocket serverSocket;
    private HashMap<String, String> userMap = new HashMap<>(){{
        put("Arsenal", "abc");
        put("Chelsea", "abc");
        put("Liverpool", "abc");
        put("Manchester City", "abc");
        put("Manchester United", "abc");
    }};
    private Map<String, List<NetworkUtil>> clientMap;
    private List<NetworkUtil> clientNetworkUtilList;

    Server() {
        clientMap = new HashMap<>();
        try {
            serverSocket = new ServerSocket(33339);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        Object o = null;
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setStatus(false);
        while (!loginDTO.isStatus()) {
            try {
                o = networkUtil.read();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (o != null) {
                if (o instanceof LoginDTO) {
                    loginDTO = (LoginDTO) o;
                    String password = userMap.get(loginDTO.getUserName());
                    loginDTO.setStatus(loginDTO.getPassword().equals(password));
                    networkUtil.write(loginDTO);
                }
            }
        }

        if (clientMap.containsKey(loginDTO.getUserName())) {
            clientNetworkUtilList = clientMap.get(loginDTO.getUserName());
            clientNetworkUtilList.add(networkUtil);
            clientMap.put(loginDTO.getUserName(), clientNetworkUtilList);
            new ReadThreadServer(clientMap, userMap, networkUtil);
        }
        else {
            clientNetworkUtilList = new ArrayList<>();
            clientNetworkUtilList.add(networkUtil);
            clientMap.put(loginDTO.getUserName(), clientNetworkUtilList);
            new ReadThreadServer(clientMap, userMap, networkUtil);
        }

    }

    public static void main(String[] args) {
        PlayerMarket.populatePlayerList();
        new Server();
    }

    public static void setPlayerList(ObservableList<Player> playerList) {
        Server.playerList = playerList;
    }
    public static ObservableList<Player> getPlayerList() {
        return playerList;
    }

    public static void setClubs(Club[] clubs) {
        Server.clubs = clubs;
    }
    public static Club[] getClubs() {
        return clubs;
    }
}


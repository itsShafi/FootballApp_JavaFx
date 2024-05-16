package server;

import ClubPlayers.Club;
import ClubPlayers.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    public Map<String, List<NetworkUtil>> clientMap;


    public ReadThreadServer(Map<String, List<NetworkUtil>> clientMap, HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.clientMap = clientMap;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();
                if (o != null) {
                    if (o instanceof Order) {
                        Order order = (Order) o;
                        System.out.println(order.getOrder());
                        if (order.getOrder().equals("Login")) {
                            String clubName = order.getClubName();
                            SendUpdate sendUpdate = new SendUpdate();
                            Club club = Server.clubs[PlayerMarket.searchClub(clubName)];
                            update(sendUpdate, club);
                            System.out.println(club.getName());
                            try {
                                networkUtil.write(sendUpdate);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        if (order.getOrder().equals("Sell")) {
                            String name = order.getPlayerName();
                            int pos = PlayerMarket.searchPlayer(name);
                            Player player = Server.playerList.get(pos);
                            PlayerMarket.sell(player);
                            System.out.println(player.getName());

                            System.out.println(clientMap.size());

                            for (Map.Entry<String, List<NetworkUtil>> m : clientMap.entrySet()) {
                                for (NetworkUtil n : m.getValue()) {
                                    System.out.println(911);
                                    SendUpdate sendUpdate = new SendUpdate();
                                    System.out.println(999);
                                    Club club = Server.clubs[PlayerMarket.searchClub(m.getKey())];
                                    update(sendUpdate, club);
                                    try {
                                        n.write(sendUpdate);
                                    } catch (IOException e) {
                                        System.out.println(12345);
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (order.getOrder().equals("Buy")) {
                            String name = order.getPlayerName();
                            String clubName = order.getClubName();
                            int pos = PlayerMarket.searchPlayer(name);
                            Player player = Server.playerList.get(pos);
                            boolean buyPossible = PlayerMarket.buy(clubName, player);
                            SendUpdate sendUpdate = new SendUpdate();
                            if (!buyPossible) {
                                for (Map.Entry<String, List<NetworkUtil>> m : clientMap.entrySet()) {
                                    for (NetworkUtil n : m.getValue()) {
                                        Club club = Server.clubs[PlayerMarket.searchClub(m.getKey())];
                                        if (clubName == m.getKey()) {
                                            sendUpdate.setBuy(false);
                                        } else {
                                            sendUpdate.setBuy(true);
                                        }
                                        update(sendUpdate, club);
                                        n.write(sendUpdate);
                                    }
                                }
                            } else {
                                for (Map.Entry<String, List<NetworkUtil>> m : clientMap.entrySet()) {
                                    for (NetworkUtil n : m.getValue()) {
                                        Club club = Server.clubs[PlayerMarket.searchClub(m.getKey())];
                                        update(sendUpdate, club);
                                        n.write(sendUpdate);
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(SendUpdate sendUpdate, Club club) {
        sendUpdate.setClub(club);
        Player[] players = club.getPlayers();
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < club.getPlayerNumber(); i++) {
            playerList.add(players[i]);
        }
        sendUpdate.setMaxAge(club.getMaxAge());
        sendUpdate.setMaxHeight(club.getMaxHeight());
        sendUpdate.setMaxSalary(club.getMaxSalary());
        sendUpdate.setPlayerList(playerList);
        sendUpdate.setAvailablePlayerList(Server.sendAvailablePlayerList);
        System.out.println(Server.sendAvailablePlayerList.size());
    }
}





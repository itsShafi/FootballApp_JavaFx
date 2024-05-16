package server;

import java.io.Serializable;

public class Order implements Serializable {
    private String playerName;
    private String clubName;
    private String order;

    public Order() {
        playerName = "";
        clubName = "";
        order = "";
    }
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

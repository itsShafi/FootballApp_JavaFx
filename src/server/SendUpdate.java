package server;


import ClubPlayers.Club;
import ClubPlayers.Player;

import java.io.Serializable;
import java.util.List;

public class SendUpdate implements Serializable {
    private Club club;
    private List<Player> playerList;
    private List<Player> availablePlayerList;
    private boolean buy;
    private double availableBalance;
    private double maxSalary;
    private double maxHeight;
    private int maxAge;

    SendUpdate() {
        buy = true;
    }

    public Club getClub() {
        return club;
    }
    public void setClub(Club club) {
        this.club = club;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public boolean isBuy() {
        return buy;
    }
    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public List<Player> getAvailablePlayerList() {
        return availablePlayerList;
    }
    public void setAvailablePlayerList(List<Player> availablePlayerList) {
        this.availablePlayerList = availablePlayerList;
    }

    public double getMaxSalary() {
        return maxSalary;
    }
    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public double getMaxHeight() {
        return maxHeight;
    }
    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
    public double getAvailableBalance() {
        return availableBalance;
    }
}

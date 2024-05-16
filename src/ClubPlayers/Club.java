package ClubPlayers;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Club implements Serializable {
    private String Name;
    private int playerNumber;
    private Player[] players;
    private int maxAge;
    private double maxSalary;
    private double maxHeight;
    private double SalaryTotal;
    private transient Image image;


    public Club() {
        playerNumber = 0;
        maxAge = 0;
        maxHeight = 0;
        maxSalary = 0;
        SalaryTotal = 0;
        players = new Player[7];
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
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

    public double getSalaryTotal() {
        return SalaryTotal;
    }

    public void setSalaryTotal(double salaryTotal) {
        this.SalaryTotal = salaryTotal;
    }


    public boolean addPlayer(Player p) {
        players[playerNumber++] = p;
        if (p.getAge() > maxAge) {
            maxAge = p.getAge();
        }
        if (p.getWeeklySalary() > maxSalary) {
            maxSalary = p.getWeeklySalary();
        }
        if (p.getHeight() > maxHeight) {
            maxHeight = p.getHeight();
        }
        SalaryTotal += p.getWeeklySalary();
        return true;
    }

    public void removePlayer(Player p) {
        int idx = -1;
        for (int i = 0; i < playerNumber; i++) {
            if (p.getName().equalsIgnoreCase(players[i].getName())) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            return;
        }
        for (int i = idx; i < playerNumber - 1; i++) {
            players[i] = players[i + 1];
        }
        playerNumber--;

        maxAge = 0;
        maxHeight = maxSalary = 0.0;

        for (int i = 0; i < playerNumber; i++) {
            if (players[i].getAge() > maxAge) {
                maxAge = players[i].getAge();
            }
            if (players[i].getWeeklySalary() > maxSalary) {
                maxSalary = players[i].getWeeklySalary();
            }
            if (players[i].getHeight() > maxHeight) {
                maxHeight = players[i].getHeight();
            }
        }
    }
}




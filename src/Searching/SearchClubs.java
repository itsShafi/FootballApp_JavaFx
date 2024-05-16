package Searching ;

import ClubPlayers.Club;
import ClubPlayers.Player;
import MainPage.Main;

import java.util.ArrayList;
import java.util.List;


public class SearchClubs {
    private static Club[] Clubs;

    SearchClubs(){
        Clubs = Main.getClubs();
    }
    public void setClub(Club[] c)
    {
        Clubs =c;
    }
    public static Club[] getClubs()
    {
        return Clubs;
    }


    public List<Player> maxSalaryPlayers(String clubName) {
        List<Player> maxSalaryPlayerList = new ArrayList();
        for (Club c : Clubs) {
            if (clubName.equalsIgnoreCase(c.getName())) {
                Player[] players = c.getPlayers();
                double maxSalary = c.getMaxSalary();
                for (int i = 0; i < c.getPlayerNumber(); i++) {
                    if (players[i].getWeeklySalary() == maxSalary) {
                        maxSalaryPlayerList.add(players[i]);
                    }
                }
                break;
            }
        }
        return maxSalaryPlayerList;
    }

    public List<Player> maxAgePlayers(String clubName) {
        List<Player> maxAgePlayerList = new ArrayList();
        for (Club c : Clubs) {
            if (clubName.equalsIgnoreCase(c.getName())) {
                Player[] players = c.getPlayers();
                int maxAge = c.getMaxAge();
                for (int i = 0; i < c.getPlayerNumber(); i++) {
                    if (players[i].getAge() == maxAge) {
                        maxAgePlayerList.add(players[i]);
                    }
                }
                break;
            }
        }
        System.out.println(maxAgePlayerList.size());
        return maxAgePlayerList;
    }


    public List<Player> maxHeightPlayers(String clubName) {
        List<Player> maxHeightPlayerList = new ArrayList();
        for (Club c : Clubs) {
            if (clubName.equalsIgnoreCase(c.getName())) {
                Player[] players = c.getPlayers();
                double maxHeight = c.getMaxHeight();
                for (int i = 0; i < c.getPlayerNumber(); i++) {
                    if (players[i].getHeight() == maxHeight) {
                        maxHeightPlayerList.add(players[i]);
                    }
                }
                break;
            }
        }
        System.out.println(maxHeightPlayerList.size());
        return maxHeightPlayerList;
    }

}





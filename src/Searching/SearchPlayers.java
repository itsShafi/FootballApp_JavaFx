package Searching ;

import ClubPlayers.Player;

import java.util.ArrayList;
import java.util.List;


public class SearchPlayers {
    private List <Player> players;

    public void setsplayers (List <Player> p) {
        players = p;
    }
    public List <Player> getsplayers(){
        return players;
    }


    public  List<Player> SearchByName(String s) {
        List<Player> ans = new ArrayList<>();
        for (Player p : players) {
            if (!p.getName().equalsIgnoreCase(s)) {
                continue;
            }
            ans.add(p);
        }
        return ans;
    }

    public List<Player> SearchByClubAndCountry( String country,String club )
    {
        List < Player > ans =new ArrayList<>();

        for(Player p:players ) {
            if( (country.equalsIgnoreCase(p.getCountry()) && ( club.equalsIgnoreCase(p.getClub()) || club.equals("ANY") ))) {
                ans.add(p);
            }
        }
        return ans;
    }


    public List<Player> SearchByPosition( String pos )
    {
        List < Player > ans =new ArrayList<>();

        for (Player p : players) {
            if (pos.equalsIgnoreCase(p.getPosition())) {
                ans.add(p);
            }
        }
        return ans;
    }

    public List<Player> SearchBySalaryRange( double lowSal,double highSal )
    {
        List < Player > ans =new ArrayList<>();

        for(Player p:players ) {
            if(lowSal<=p.getWeeklySalary() && highSal>=p.getWeeklySalary()) {
                ans.add(p);
            }
        }
        return ans;
    }


    public List<Player> SearchByHeightRange( double lowHeight,double highHeight )
    {
        List < Player > ans =new ArrayList<>();

        for(Player p:players ) {
            if(lowHeight<=p.getHeight() && highHeight>=p.getHeight()) {
                ans.add(p);
            }
        }
        return ans;
    }

}

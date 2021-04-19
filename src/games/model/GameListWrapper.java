package games.model;

/**
 * Created by gianlucaamato on 05/07/16.
 */
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "games")
public class GameListWrapper {

    private List<Game> games;

    @XmlElement(name = "game")
    public List<Game> getGames(){
        return games;
    }

    public void setGames(List<Game> games){
        this.games = games;
    }
}
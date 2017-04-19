package database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mines {

    @Id
    private int id;
    private int distanceToMine;
    private int possibleGold;
    private String mineName;

    public int getDistanceToMine() {
        return distanceToMine;
    }

    public int getPossibleGold() {
        return possibleGold;
    }

    public String getMineName() {
        return mineName;
    }

    public void setDistanceToMine(int distanceToMine) {
        this.distanceToMine = distanceToMine;
    }

    public void setPossibleGold(int possibleGold) {
        this.possibleGold = possibleGold;
    }

    public void setMineName(String mineName) {
        this.mineName = mineName;
    }
}
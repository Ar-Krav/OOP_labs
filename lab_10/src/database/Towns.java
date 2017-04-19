package database;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Towns {
    @Id
    private int id;
    private String name;
    private int distanceToTown;
    private int availableValueOfGold;
    private int priceOfGold;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDistanceToTown() {
        return distanceToTown;
    }

    public int getAvailableValueOfGold() {
        return availableValueOfGold;
    }

    public int getPriceOfGold() {
        return priceOfGold;
    }
}
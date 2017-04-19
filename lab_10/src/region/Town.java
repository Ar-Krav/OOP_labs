package region;

import database.Towns;

public class Town {
    public Town(Towns town){
        this.name = town.getName();
        this.distanceToTown = town.getDistanceToTown();
        this.availableValueOfGold = town.getAvailableValueOfGold();
        this.priceOfGold = town.getPriceOfGold();
        this.valueOfEarnedMoney = 0;
    }

    private String name;
    private int distanceToTown;
    private int availableValueOfGold;
    private int priceOfGold;
    private int restOfUserGold;
    private int valueOfEarnedMoney;

    public String getName() {
        return name;
    }

    public int getAvailableValueOfGold() {
        return availableValueOfGold;
    }

    public int getPriceOfGold() {
        return priceOfGold;
    }

    public int getDistanceToTown() {
        return distanceToTown;
    }

    public int getRestOfUserGold() {
        return restOfUserGold;
    }

    public void setAvailableValueOfGold(int availableValueOfGold) {
        this.availableValueOfGold = availableValueOfGold;
    }

    public void setValueOfEarnedMoney(int valueOfEarnedMoney) {
        this.valueOfEarnedMoney = valueOfEarnedMoney;
    }

    public int getValueOfEarnedMoney() {
        return valueOfEarnedMoney;
    }

    public int earnMoneyAtTown(int valueForSell){
        return getAvailableValueForSell(valueForSell) * priceOfGold;
    }

    private int getAvailableValueForSell(int valueForSell){
        if (valueForSell <= availableValueOfGold) {
            restOfUserGold = 0;
            return valueForSell;
        }
        else {
            restOfUserGold = valueForSell - availableValueOfGold;
            valueForSell = availableValueOfGold;
            return valueForSell;
        }
    }
}
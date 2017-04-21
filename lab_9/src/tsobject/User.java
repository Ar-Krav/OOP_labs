package tsobject;


public class User {
    public User(int priceForKilometr) {
        this.priceForKilometr = priceForKilometr;
        this.stockGold = 0;
    }

    private int priceForKilometr;
    private int stockGold;
    private int earnedMoney;

    public int getStockGold() {
        return stockGold;
    }

    public void setStockGold(int stockGold) {
        this.stockGold = stockGold;
    }

    public int getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(int earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public int payMoneyForRoad(int distance){
        return distance * priceForKilometr;
    }
}
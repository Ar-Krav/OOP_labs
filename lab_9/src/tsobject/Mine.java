package tsobject;


public class Mine {
    public Mine(int distanceToMine, int possibleGold, String mineName){
        this.distanceToMine = distanceToMine;
        this.possibleGold = possibleGold;
        this.mineName = mineName;
    }

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
}
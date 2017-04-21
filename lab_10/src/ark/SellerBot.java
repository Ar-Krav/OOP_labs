package ark;

import database.*;
import tsobject.*;
import java.util.ArrayList;


public class SellerBot {

    public SellerBot(ArrayList<Town> townsList) {
        this.townsList = townsList;
    }

    private User user = new User(10);
    private Mines selectedMine;
    private ArrayList <Town> townsList;

    public void setSelectedMine(Mines selectedMine) {
        this.selectedMine = selectedMine;
    }

    public ArrayList<String[]> getChaneOfTownsWithBestPrice(){
        user.setStockGold(selectedMine.getPossibleGold());
        ArrayList <String[]> chaneOfTownsForSel = new ArrayList<>();
        int indexOfTheRichestTown = 0;

        while (user.getStockGold() > 0){
            sellGoldInTowns(user.getStockGold());
            indexOfTheRichestTown = getIndexOfTownWithHigesCash();

            if (townsList.get(indexOfTheRichestTown).getValueOfEarnedMoney() <= 0) break;

            user.setStockGold(townsList.get(indexOfTheRichestTown).getRestOfUserGold());
            user.setEarnedMoney(user.getEarnedMoney() + townsList.get(indexOfTheRichestTown).getValueOfEarnedMoney());
            townsList.get(indexOfTheRichestTown).setAvailableValueOfGold(0);

            String [] turnResult = {"" + indexOfTheRichestTown, townsList.get(indexOfTheRichestTown).getName(),"" + townsList.get(indexOfTheRichestTown).getValueOfEarnedMoney(),""+ user.getEarnedMoney()};
            chaneOfTownsForSel.add(turnResult);
        }
        String [] turnResult = {"--", "Дорога до шахти","-" + user.payMoneyForRoad(selectedMine.getDistanceToMine()), user.getEarnedMoney() - user.payMoneyForRoad(selectedMine.getDistanceToMine()) + ""};
        chaneOfTownsForSel.add(turnResult);

        return chaneOfTownsForSel;
    }

    private void sellGoldInTowns(int valueOfGoldForSell){
        for (int i = 0, priceForRoadToTown = 0; i < townsList.size(); i++){
            priceForRoadToTown = user.payMoneyForRoad(townsList.get(i).getDistanceToTown());
            townsList.get(i).setValueOfEarnedMoney(townsList.get(i).earnMoneyAtTown(valueOfGoldForSell) - priceForRoadToTown);
        }
    }

    private int getIndexOfTownWithHigesCash(){
        int index = 0;
        for (int i = 0, max = 0; i < townsList.size(); i++){
            if (townsList.get(i).getValueOfEarnedMoney() > max){
                max = townsList.get(i).getValueOfEarnedMoney();
                index = i;
            }
        }
        return index;
    }
}
package jdbc;

import java.sql.SQLException;
import java.util.ArrayList;


public class JDBCUtil {

    JDBCAdapter jdbc = new JDBCAdapter("jdbc:mysql://localhost/gold_industry?autoReconnect=true&useSSL=false","com.mysql.jdbc.Driver","root","04091998a");

    public ArrayList<ArrayList> getTowns(){
        ArrayList<ArrayList> tableData = jdbc.executeQuery("SELECT * from towns");
        return tableData;
    }

    public ArrayList<ArrayList> getMines(){
        ArrayList<ArrayList> tableData = jdbc.executeQuery("SELECT * from mines");
        return tableData;
    }

    public ArrayList<ArrayList> getMineByName(String mineName){
        ArrayList<ArrayList> tableData = jdbc.executeQuery("SELECT * FROM mines WHERE mineName IN (\"" + mineName + "\")");
        return tableData;
    }

    public  void addMine(int mDistance, int mGoldValue, String mName){
        jdbc.addRow("INSERT INTO mines VALUES (null," + mDistance + "," + mGoldValue + ",\""+ mName +"\")");
    }

    public void closeConnection(){
        try {
            jdbc.close();
        }
        catch (SQLException e){
            System.out.println("Connecting cannot be close!");
        }
    }
}
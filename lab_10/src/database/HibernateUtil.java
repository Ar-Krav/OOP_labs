package database;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import tsobject.Town;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class HibernateUtil {
    public HibernateUtil(){
        try {
            sessionFactory = new Configuration().configure("database/hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Throwable ex) {
            System.err.println("Unfortenatly Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private SessionFactory sessionFactory;
    private Session session;


    private List<Towns> getTownsList(){
        return session.createQuery("from Towns").list();
    }

    public ArrayList<Town> getTownArray(){
        ArrayList<Town> townArray = new ArrayList<>();
        List<Towns> townsList = getTownsList();
        for(Towns towns : townsList) townArray.add(new Town(towns));

        return  townArray;
    }

    public DefaultTableModel getTownTableModel(){
        String [] headerCity = {"Номер", "Назва", "Відстань", "Наявність золота", "Ціна золота"};
        DefaultTableModel dfTableModel = new DefaultTableModel(headerCity,0);
        List<Towns> townsList = getTownsList();

        for(Towns towns : townsList){
            Vector<Object> row = new Vector();
                row.add(towns.getId());
                row.add(towns.getName());
                row.add(towns.getDistanceToTown());
                row.add(towns.getAvailableValueOfGold());
                row.add(towns.getPriceOfGold());

            dfTableModel.addRow(row);
        }
        return dfTableModel;
    }

    public List<Mines> getMinesList(){
        return session.createQuery("from Mines").list();
    }

    public Mines getMineByName (String mineName){
        return (Mines) session.createQuery("from Mines where mineName = :mineName").setParameter("mineName",mineName).uniqueResult();
    }

    public void addNewMine(int distance, int possibleGold, String mineName){
        Transaction transaction = session.beginTransaction();

        Mines mines = new Mines();
            mines.setDistanceToMine(distance);
            mines.setPossibleGold(possibleGold);
            mines.setMineName(mineName);

        session.save(mines);
        transaction.commit();
    }
}

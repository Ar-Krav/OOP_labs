package ark;

import jdbc.JDBCUtil;
import region.Mine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window {
    public Window() {
        ArrayList<ArrayList> tableDataMines = jdbcUtil.getMines();

        for (int i = 0; i < tableDataMines.size(); i++){
            ArrayList row = tableDataMines.get(i);
            this.minesBox.addItem((String) row.get(3));
        }
    }

    private JDBCUtil jdbcUtil = new JDBCUtil();
    private SellerBot sellerBot = new SellerBot(jdbcUtil);
    private JComboBox minesBox = new JComboBox();

    public void drawWindow(){
        JFrame window = new JFrame();
            window.setSize(800, 400);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setLayout(null);

        //CITY PANEL
        JPanel cityPanel = new JPanel();
            cityPanel.setLayout(null);
            setComponentPosition(cityPanel,new Point(350,window.getHeight()-100), new Point(0,100));
            cityPanel.setBackground(Color.ORANGE);

            String [] headerCity = {"Номер", "Назва", "Відстань", "Наявність золота", "Ціна золота"};

            JTable cityTable = new JTable(getCityTableDataModel(),headerCity);
            JScrollPane cityScrollPane = new JScrollPane(cityTable);
                setComponentPosition(cityScrollPane,new Point(330,window.getHeight()-150), new Point(10,10));
        cityPanel.add(cityScrollPane);

        //RESULT PANEL
        JPanel resultPanel = new JPanel();
            resultPanel.setLayout(null);
            setComponentPosition(resultPanel,new Point(window.getWidth()-350,window.getHeight()-100), new Point(350,100));
            resultPanel.setBackground(Color.RED);

            String [] resultTableHeader = {"ІНДЕКС МІСТА","НАЗВА МІСТА","КІЛЬКІСТЬ ЗОЛОТА ЗА ХІД","ЗАГАЛЬНА КІЛЬКІСТЬ ЗОЛОТА"};
            DefaultTableModel resultTableModel = new DefaultTableModel(resultTableHeader,0);
            JTable resultTable = new JTable(resultTableModel);

            JScrollPane resultScrollPane = new JScrollPane(resultTable);
                setComponentPosition(resultScrollPane,new Point(window.getWidth()-375,window.getHeight()-150), new Point(10,10));
        resultPanel.add(resultScrollPane);

        //MINE PANEL
        JPanel minePanel = new JPanel();
            minePanel.setLayout(null);
            setComponentPosition(minePanel,new Point(window.getWidth(),100),new Point(0,0));
            minePanel.setBackground(Color.blue);

            JTextField inputName = new JTextField("Введіть назву Шахти");
                setComponentPosition(inputName,new Point(230,25),new Point(70,10));
            JTextField inputDistance = new JTextField("Введіть відстань");
                setComponentPosition(inputDistance,new Point(115,25),new Point(70,35));
            JTextField inputGoldValue = new JTextField("Введіть золото");
                setComponentPosition(inputGoldValue,new Point(115,25),new Point(185,35));
            JButton addMineButton = new JButton("Додати шахту");
                setComponentPosition(addMineButton,new Point(230,30),new Point(70,60));
                addMineButton.addActionListener(getAddButtonActionListener(inputName,inputDistance,inputGoldValue,resultTableModel));

            JLabel comboBoxLabel = new JLabel("Оберіть вже існуючу шахту ⇓");
                setComponentPosition(comboBoxLabel,new Point(230,25),new Point(470,10));
                setComponentPosition(minesBox,new Point(230,25),new Point(450,50));
                minesBox.addActionListener(getMineBoxActionListener(resultTableModel));
        minePanel.add(inputName);
        minePanel.add(inputDistance);
        minePanel.add(inputGoldValue);
        minePanel.add(addMineButton);
        minePanel.add(comboBoxLabel);
        minePanel.add(minesBox);

        window.add(minePanel);
        window.add(cityPanel);
        window.add(resultPanel);
        window.setVisible(true);
    }

    private void setComponentPosition(Component component, Point size, Point location){
        component.setSize(size.x,size.y);
        component.setLocation(location.x,location.y);
    }

    private ActionListener getAddButtonActionListener(JTextField inName, JTextField inDistance,JTextField inValue, DefaultTableModel model){
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minesBox.addItem(inName.getText());
                jdbcUtil.addMine(Integer.parseInt(inDistance.getText()),Integer.parseInt(inValue.getText()),inName.getText());
                sellerBot.setSelectedMine(getNewMine(inName.getText()));
                ArrayList<String[]> resultList = sellerBot.getChaneOfTownsWithBestPrice();
                while(model.getRowCount() > 0) model.removeRow(0);
                for (String[] row : resultList) model.addRow(row);
            }
        };
        return action;
    }

    private ActionListener getMineBoxActionListener(DefaultTableModel model){
        ActionListener action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellerBot = new SellerBot(jdbcUtil);
                sellerBot.setSelectedMine(getNewMine((String) minesBox.getSelectedItem()));
                ArrayList<String[]> resultList = sellerBot.getChaneOfTownsWithBestPrice();
                while(model.getRowCount() > 0) model.removeRow(0);
                for (String[] row : resultList) model.addRow(row);
            }
        };
        return action;
    }

    private Mine getNewMine(String mineName){
        ArrayList<ArrayList> tableData = jdbcUtil.getMineByName(mineName);
        ArrayList row = tableData.get(0);

        Mine mine = new Mine((int) row.get(1),(int) row.get(2),(String) row.get(3));
        return mine;
    }

    private Object[][] getCityTableDataModel(){
        Object[][] tableData = new Object[jdbcUtil.getTowns().size()][];

        for (int i = 0; i < jdbcUtil.getTowns().size(); i++){
            ArrayList row = jdbcUtil.getTowns().get(i);
            tableData[i] = row.toArray(new Object[row.size()]);
        }

        return tableData;
    }
}
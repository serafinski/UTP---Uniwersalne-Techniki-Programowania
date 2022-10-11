package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountryTable extends JTable{

    JTable table = new JTable();

    DefaultTableModel model = new DefaultTableModel();
    String linia;
    String[] naglowki;
    String[] tmp;

    public CountryTable(String countriesFileName) throws IOException {
        FileReader fileReader = new FileReader(countriesFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //uzyskanie nagłówków
        linia = bufferedReader.readLine();
        naglowki = linia.split("\t");
        model.setColumnIdentifiers(naglowki);
        linia = bufferedReader.readLine();

        //Reszta pliku
        while (linia != null){
            tmp = linia.split("\t");
            model.addRow(tmp);
            linia = bufferedReader.readLine();
        }

    }
//
//    @Override
//    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
//        Component component = super.prepareRenderer(renderer,row,column);
//
//        for(int i = 0; i<195;i++){
//            long tmp = (long)this.getModel().getValueAt(row,2);
//            System.out.println(tmp);
//            if(tmp >20000000){
//                component.setForeground(Color.RED);
//            }
//            else{
//                component.setForeground(Color.black);
//            }
//        }
//
//        return component;
//    }

    public JTable create() {
        table = new JTable(){
            @Override
            public Class<?> getColumnClass(int column){
                switch (column) {
                    case 2:
                        return Long.class;
                    case 3:
                        return Icon.class;
                    case 0:
                    case 1:
                    default:
                        return String.class;
                }
            }
        };
        table.setModel(model);
        return table;
    }
}

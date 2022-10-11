package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CountryTable {

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

    public JTable create() {
        table = new JTable(){
            @Override
            public Class<?> getColumnClass(int column){
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Long.class;
                    case 3:
                        return Icon.class;
                    default:
                        return String.class;
                }
            }
        };
        table.setModel(model);
        return table;
    }
}

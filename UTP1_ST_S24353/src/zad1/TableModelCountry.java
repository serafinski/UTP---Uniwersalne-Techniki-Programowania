package zad1;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class TableModelCountry extends AbstractTableModel {

    private final Object[][] dane;

    public String[] naglowki;

    private final Path countriesFileName;

    public TableModelCountry(Path sciezka) {
        this.dane = new Object[190][4];
        this.naglowki = new String[4];
        this.countriesFileName = sciezka;
    }

    public void start() throws IOException {
        FileReader fileReader = new FileReader(String.valueOf(countriesFileName));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        naglowki = line.split("\t");


        for (int i = 0; i < dane.length; i++) {
            line = bufferedReader.readLine();
            if (line != null) {
                Object[] tmp = line.split("\t");
                for (int j = 0; j < dane[i].length; j++) {
                    dane[i][j] = tmp[j];
                    if (j == 3) {
                        dane[i][j] = new ImageIcon("data/flags/" + tmp[j] + ".png");
                    }
                }
            } else {
                break;
            }
        }
    }

    @Override
    public int getRowCount() {
        return dane.length;
    }

    @Override
    public String getColumnName(int column) {
        return naglowki[column];
    }

    @Override
    public int getColumnCount() {
        return dane[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dane[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

}

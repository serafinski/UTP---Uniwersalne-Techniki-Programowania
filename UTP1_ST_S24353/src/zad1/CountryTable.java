package zad1;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CountryTable extends JTable {

    private final String countriesFileName;

    public CountryTable(String countriesFileName) {
        this.countriesFileName = countriesFileName;
    }

    public JTable create() throws IOException {

        Path sciezka = Paths.get(countriesFileName);

        TableModelCountry tableModelCountry = new TableModelCountry(sciezka);

        tableModelCountry.start();


        return new JTable(tableModelCountry) {
            @Override
            public Class<?> getColumnClass(int column) {
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
    }
}

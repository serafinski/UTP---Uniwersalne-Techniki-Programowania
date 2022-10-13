package zad1;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
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

        JTable table = new JTable(tableModelCountry){
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 2:
                        return Integer.class;
                    case 3:
                        return ImageIcon.class;
                    case 0:
                    case 1:
                    default:
                        return String.class;
                }
            }
        };

        table.setDefaultRenderer(Integer.class, new DefaultTableCellRenderer(){

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                if(value != null){
                    Component component = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
                    component.setForeground(((Integer)value) > 20000000 ? Color.red : Color.BLACK);
                    return component;
                }else{
                    return null;
                }
            }

        });

        return table;

        //return new JTable(tableModelCountry) {

        };
    }

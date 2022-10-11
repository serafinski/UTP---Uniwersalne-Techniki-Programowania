package zad1;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

public class CountryTable {

    String linia;
    String[] naglowki;

    Vector<String[]> dane = new Vector<String[]>();
    String[] tmp;
    public CountryTable(String countriesFileName) throws IOException {
        FileReader fileReader = new FileReader(countriesFileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //uzyskanie nagłówków
        linia = bufferedReader.readLine();
        naglowki = linia.split("\t");
        linia = bufferedReader.readLine();

        //
        while (linia != null){
            tmp = linia.split("\t");
            dane.add(tmp);
            System.out.println(Arrays.toString(tmp));
            linia = bufferedReader.readLine();
        }

    }

    public JTable create() {
        System.out.println();
        String [][] data = {{"Republic of Poland", "Warsaw", "38500"},{"Chech Republic", "Prague", "10500"}};
        String []column = naglowki;
        return new JTable(data,column);
    }
}

/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad2;


/*<-- niezbędne importy */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    // Lista destynacji: port_wylotu port_przylotu cena_EUR
    List<String> dest = Arrays.asList(
      "bleble bleble 2000",
      "WAW HAV 1200",
      "xxx yyy 789",
      "WAW DPS 2000",
      "WAW HKT 1000"
    );
    double ratePLNvsEUR = 4.30;
    List<String> result = dest.stream()
            .filter(n -> n.startsWith("WAW"))
            .map(n->{
                String[] tekst = n.split(" ");
                return "to " + tekst[1] + " - price in PLN:\t" + (int)(Double.parseDouble(tekst[2])* ratePLNvsEUR);
            })
            .collect(Collectors.toCollection(ArrayList<String>::new));

    for (String r : result) System.out.println(r);
  }
}

/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {

    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */

    // A lambda expression that takes a path to a file and returns a list of lines from that file.
    Function<String, List<String>> flines = (path) -> {
      try {
        List<String> linesList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
          linesList.add(line);
        }

        br.close();

        return linesList;
      } catch (IOException e) {
        e.printStackTrace();
        return new ArrayList<>();
      }
    };

    // A lambda expression that takes a list of strings and returns a string that is a concatenation of all the strings in
    // the list.
    Function<List<String>, String> join = (list) -> String.join("", list);

    // A lambda expression that takes a string and returns a list of integers. It uses regex to find all integers in the
    // string.
    Function<String, List<Integer>> collectInts = (s) -> {
      List<Integer> allMatches = new ArrayList<>();
      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(s);

      while (matcher.find()) {
        allMatches.add(Integer.parseInt(matcher.group()));
      }

      return allMatches;
    };

    // A lambda expression that takes a list of integers and returns an integer. It sums all the integers in the list.
    Function<List<Integer>, Integer> sum = (ints) -> {
      Integer s = 0;

      for (Integer n : ints) {
        s += n;
      }

      return s;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt"; 
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

  }
}

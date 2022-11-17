/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Anagrams {
    private List<String> wordsList;


    // It's a constructor. It reads all words from file and adds them to wordsList.
    public Anagrams(String allWords) {
        this.wordsList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(allWords));

            String line;

            while((line = bufferedReader.readLine()) != null) {
                this.wordsList.addAll(Arrays.asList(line.split(" ")));
            }

            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * It splits the words into characters, sorts them, and compares the sorted lists
     *
     * @param word1 the first word
     * @param word2 the word to check if it's an anagram of word1
     * @return The method returns true if the two words are anagrams, false otherwise.
     */
    private boolean areWordsAnagrams(String word1, String word2) {
        TreeSet<String> set1 = new TreeSet<>(Arrays.asList(word1.split("")));
        TreeSet<String> set2 = new TreeSet<>(Arrays.asList(word2.split("")));

        return set1.equals(set2);
    }

    /**
     * We create a new list of lists, then we iterate over the words list, and for each word we check if it's an anagram of
     * any of the words in the new list, if it is we add it to the list, if it isn't we create a new list with the word and
     * add it to the new list
     *
     * @return A list of lists of strings.
     */
    public List<ArrayList<String>> getSortedByAnQty() {
        ArrayList<ArrayList<String>> out = new ArrayList<>();

        for (String word : this.wordsList) {
            boolean found = false;

            for (ArrayList<String> list: out) {
                String listWord = list.get(0);

                if (this.areWordsAnagrams(listWord, word)) {
                    list.add(word);
                    found = true;
                    break;
                }
            }

            if (!found) {
                ArrayList<String> newList = new ArrayList<>();
                newList.add(word);
                out.add(newList);
            }
        }

        return out
                .stream()
                .sorted((element1, element2) -> {
                    int diffrence = element2.size() - element1.size();

                    if (diffrence == 0) {
                        return element1.get(0).compareTo(element2.get(0));
                    }

                    return diffrence;
                })
                .collect(Collectors.toList());
    }

    /**
     * It takes a word, checks if it's an anagram of any other word in the dictionary, and if it is, it returns a list of
     * all the words that are anagrams of the searched word
     *
     * @param searchedWord the word we're looking for anagrams for
     * @return A string containing the searched word and the anagrams of it.
     */
    public String getAnagramsFor(String searchedWord) {
        ArrayList<String> arrayList = this
                .getSortedByAnQty()
                .stream()
                .filter((element) -> this.areWordsAnagrams(searchedWord, element.get(0)))
                .findAny()
                .orElse(null);

        if (arrayList == null) {
            return searchedWord + ": null";
        }

        List<String> lWithoutSearched = arrayList
                .stream()
                .filter(element -> !element.equals(searchedWord))
                .collect(Collectors.toList());

        return searchedWord + ": " + lWithoutSearched;
    }
}  

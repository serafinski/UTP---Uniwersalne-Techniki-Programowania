/**
 *
 *  @author Serafiński Tomasz S24353
 *
 */

package zad2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class Finder {
    public String path;
    public int ifCount = 0;
    public int wordCount = 0;


    // It's a constructor. It's used to create an object of the Finder class.
    Finder(String path){
        this.path = path;
    }

    /**
     * It reads the lines of the file, finds the indexes of all if statements, and then checks if they are commented out
     *
     * @return The number of if statements in the file.
     */
    public int getIfCount() {
        List<String> lines = readLines();
        List<Integer> ifIndex = new ArrayList<>();

        // It's iterating through the list of lines and adding the index of the line to the ifIndex list if the line
        // contains an if statement.
        for (int i = 0; i < lines.size(); i++) {
            if (findIf(lines.get(i))) {
                ifIndex.add(i);
            }
        }

        // It's iterating through the list of indexes of if statements and checking if the if statement is commented out.
        for (int index:ifIndex) {
            if(isCommented(lines, index)){
                ifCount++;
            }
        }
        return this.ifCount;
    }


    /**
     * It checks if the if statement is commented out
     *
     * @param lines The list of lines in the file
     * @param ifIndex The index of the if statement in the list of lines.
     * @return The method is returning a boolean value.
     */
    private boolean isCommented(List<String> lines, int ifIndex){
        boolean commentFlag = false;

        // It's checking if the if statement is commented out.
        if(!lines.get(ifIndex).contains("//")){
            for (int i = 0; i <= ifIndex; i++) {

                // It's checking if the line contains a comment.
                if(lines.get(i).contains("/*")){
                    commentFlag = true;
                }

                // It's checking if the if statement is commented out.
                if(commentFlag){
                    if(lines.get(i).contains("*/") && i != ifIndex){
                        commentFlag = false;
                    }
                }
            }
        }

        else{
            commentFlag = true;
        }

        return !commentFlag;
    }


    /**
     * It takes a line of code as a string and returns true if the line contains an if statement
     *
     * @param line The line of code that is being checked
     * @return A boolean value.
     */
    private boolean findIf(String line){
        Pattern pattern = Pattern.compile("if\\s*(?=\\().*");
        Matcher matcher = pattern.matcher(line);

        return matcher.find();
    }

    /**
     * It reads the file at the given path and returns a list of strings, one for each line in the file
     *
     * @return A list of strings
     */
    private List<String>readLines(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.path));
            return reader.lines().collect(toList());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * It reads the lines of the file, and then for each line it checks if the line contains the word we're looking for. If
     * it does, it increments the wordCount variable
     *
     * @param string the word you want to count
     * @return The number of times the word appears in the file.
     */
    public int getStringCount(String string) {
        Pattern pattern = Pattern.compile("\"" + string + "\"");

        List<String> lines = readLines();
        for (String line : lines){
            if(pattern.matcher(line).find()){
                wordCount++;
            }
        }
        return this.wordCount;
    }
}

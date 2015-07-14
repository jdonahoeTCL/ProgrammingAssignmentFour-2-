
package assignmentFour;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Roland Moore and Joshua Donahoe
 */
public class AssignmentFour {
    public MyLinkedList[] dictList = new MyLinkedList[26];
    private long wordsFound;
    private long wordsNotFound;
    private long compsFound;
    private long compsNotFound;
    private int[] count = {0};
    private File dictionary;

    /**
     * Default constructor assigns all of the counters to zero
     * and initializes the dictionary to be used for the comparisons
     * and calls the loadDict and textChecker methods
     */
    public AssignmentFour() {
        dictionary = new File("random_dictionary.txt");
        wordsFound = 0;
        wordsNotFound = 0;
        compsFound = 0;
        compsNotFound = 0;
        for (int i = 0; i < dictList.length; i++) {
            dictList[i] = new MyLinkedList();            
        }
        loadDict();
        textChecker();

    }

    /**
     * Loads and stores each word from the given dictionary into the dictList in alphabetical order
     */
    public final void loadDict() {
        try {
            FileInputStream inf = new FileInputStream(dictionary);
            char let;
            String str = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {                    
                    dictList[str.charAt(0) - 97].add(str);
                    str = "";

                }

            }
            inf.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
    
    /**
     * @requires the user to input a file path to a text file
     * @ensures that the contents of the file will be spell checked
     * this method separates each individual word from a given text and 
     * checks the spelling of the words against a dictionary. It will then 
     * increment counters wordsFound wordsNotFound compsFound and compsNotFound
     * depending on the results of the search
     */
    public final void textChecker() {
        System.out.println("Enter the file name to be read: ");
        System.out.print("> ");
        Scanner inputStream = new Scanner(System.in);
        String fileName = inputStream.next();
        inputStream.close();
        try {
            FileInputStream inf = new FileInputStream(new File(fileName));
            char let;
            String str = "";
            int n = 0;
            while ((n = inf.read()) != -1) {
                let = (char) n;
                if (Character.isLetter(let)) {
                    str += Character.toLowerCase(let);
                }
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty()) {
                    if (dictList[str.charAt(0) - 97].contains(str, count)) {
                        wordsFound++;
                        compsFound += count[0];
                    } else {
                        wordsNotFound++;
                        compsNotFound += count[0];
                    }
                    str = "";
                    count[0] = 0;
                }

            }
            inf.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }//end textChecker

    /**
     * @return returns the average comparisons per found word as a double
     */
    public double averageCompsFound() {
        return (double) compsFound / wordsFound;
    }//end averageFoundComparisons

    /**
     * @return returns the average comparisons per word not found as a double
     */
    public double averageCompsNotFound() {
        return (double) compsNotFound / wordsNotFound;
    }//end averageNotFoundComparisons

    /**
     *
     * @return returns the value of wordsFound as an integer
     */
    public long getWordsFound() {
        return wordsFound;
    }//end getWordsFound

    /**
     *
     * @return returns the value of wordsFoundComparisons as an integer
     */
    public long getCompsFound() {
        return compsFound;
    }//end getWordsFoundComparisons

    /**
     *
     * @return returns the value of wordsNotFound as an integer
     */
    public long getWordsNotFound() {
        return wordsNotFound;
    }//end getWordsNotFound

    /**
     *
     * @return returns the value of wordsNotFoundComparisons as an integer
     */
    public long getCompsNotFound() {
        return compsNotFound;
    }//end getWordsNotFoundComparisons

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        AssignmentFour four = new AssignmentFour();
        System.out.println("Total words found: " + four.getWordsFound());
        System.out.println("Total comparisons for words found: " + four.getCompsFound());
        System.out.println("Average comparisons per word found: " + four.averageCompsFound());
        System.out.println("\nTotal words not found: " + four.getWordsNotFound());
        System.out.println("Total comparisons for words not found: " + four.getCompsNotFound());
        System.out.println("Average comparisons per word not found: " + four.averageCompsNotFound());
    }

}

/*
Enter the file name to be read: 
> oliver.txt
Total words found: 939674
Total comparisons for words found: 3289725222
Average comparisons per word found: 3500.9218324653016

Total words not found: 52466
Total comparisons for words not found: 388324176
Average comparisons per word not found: 7401.444287729196
BUILD SUCCESSFUL (total time: 47 seconds)

*/
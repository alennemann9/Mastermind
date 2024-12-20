//Murph Lennemann

import java.util.ArrayList;
import java.util.HashMap;

public class Code {
    private ArrayList<Integer> colorArray = new ArrayList<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_GRAY = "\u001B[37m";

    public ArrayList<Integer> getColorArray() {
        return colorArray;
    }
   // default constructor for creating the answer code
    public Code() {
        for(int i=0; i<4; i++) {
            int randResult = (int) (Math.random() * 6);
            colorArray.add(randResult);
        }
    }
    //A custom constructor that takes a string for the guess and returns an ArrayList of Integers
    public Code(String Guess) {
        HashMap<String, Integer> colorList = new HashMap<>();
        colorList.put("r", 0);
        colorList.put("g", 1);
        colorList.put("y", 2);
        colorList.put("b", 3);
        colorList.put("p", 4);
        colorList.put("a", 5);
        for (int i = 0; i < 4; i++) {
            String colorChar = Guess.substring(i, i + 1);
            colorArray.add(colorList.get(colorChar));
        }
    }
    //Prints out the Array of Integers as 4 blocks
    public String ToString() {
        String squarePrint="";
        for (Integer c: colorArray) {
            // replace with hashmap lookup
            if (c==0) {
                squarePrint+= ANSI_RED + "\u25A0" + ANSI_RESET;
            }
            else if (c==1) {
                squarePrint+=ANSI_GREEN + "\u25A0" + ANSI_RESET;
            }
            else if (c==2) {
                squarePrint+=ANSI_YELLOW + "\u25A0" + ANSI_RESET;
            }
            else if (c==3) {
                squarePrint+=ANSI_BLUE + "\u25A0" + ANSI_RESET;
            }
            else if (c==4) {
                squarePrint+=ANSI_PURPLE + "\u25A0" + ANSI_RESET;
            }
            else if (c==5) {
                squarePrint+=ANSI_GRAY + "\u25A0" + ANSI_RESET;
            }
        }
        return squarePrint;
    }
    //Goes through each item in the array list and checks if it is a hit
    // If not then it will go through and check for arrays, eliminating answers that have already been cliamed.
    //Then it will return a string of feedback
    public String feedback(Code answer) {
        String feedbackString="";
        ArrayList<Integer> answerArray = new ArrayList<>();
        ArrayList<Integer> nonHits = new ArrayList<>();
        ArrayList<Integer> indexArray = new ArrayList<>();
        for (int i=0; i<4; i++) {
            if (colorArray.get(i)==answer.getColorArray().get(i)) {
                feedbackString+="h";
            }
            else {
                nonHits.add(colorArray.get(i));
                indexArray.add(i);

            }
        }
        for (int j=0; j<nonHits.size(); j++) {
            answerArray.add(answer.getColorArray().get(indexArray.get(j)));
        }
        for (int j=0; j<nonHits.size(); j++) {
            for (int k=0; k<answerArray.size(); k++) {
                if (nonHits.get(j) == answerArray.get(k)) {
                    feedbackString+="p";
                    answerArray.remove(k);
                }
            }
        }
        return feedbackString;
    }
}

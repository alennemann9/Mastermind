//Murph Lennemann

import java.util.ArrayList;

public class History {
    ArrayList<String> historyList;
// a default constructor which initializes the ArrayList for history
    public History() {
        historyList = new ArrayList<>();
    }

    // This adds any new guesses to the history list
    public void newGuess(Code guess, String feedback) {
        String printable = "Code: " + guess.ToString() + " Feedback: " + feedback;
        historyList.add(printable);
    }

    //Prints out the history list one by one
    public void printHistory() {
        for (String r: historyList) {
            System.out.println(r);
        }
    }
}

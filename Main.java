// Murph Lennemann

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    // getter for a hashmap that converts colors into their strings
    public static HashMap<String, Integer> getColorInputCheck() {
        HashMap<String, Integer> colorInputCheck = new HashMap<>();
        colorInputCheck.put("r", 0);
        colorInputCheck.put("g", 1);
        colorInputCheck.put("y", 2);
        colorInputCheck.put("b", 3);
        colorInputCheck.put("p", 4);
        colorInputCheck.put("a", 5);
        return colorInputCheck;
    }
    //accepts the first guess and returns the results as an ArrayList
    public static ArrayList<String> FirstGuess() {
        ArrayList<Integer> colorArray = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        System.out.print("Make your guess (or type \"history\"): ");
        Scanner scnr = new Scanner(System.in);
        String guessInput = scnr.nextLine();
        if (guessInput.length()==4) {
            for (int i = 0; i < 4; i++) {
                String colorChar = guessInput.substring(i, i + 1);
                if (getColorInputCheck().get(colorChar) == null) {
                    colorArray.add(300);
                } else {
                    colorArray.add(getColorInputCheck().get(colorChar));
                }
            }
        }
        int colorSum = 0;
        for (Integer d : colorArray) {
            colorSum += d;
        }
        returnList.add(guessInput);
        returnList.add(Integer.toString(colorSum));
        return returnList;
    }
    //takes the results of the first guess and if it isn't a valid guess,
    // loops through the process until a valid guess is made
    public static String Guess(History myHistory) {
        ArrayList<String> firstGuess = FirstGuess();
        String guessInput = firstGuess.get(0);
        int colorSum = Integer.parseInt(firstGuess.get(1));
        while (guessInput.length() != 4 || colorSum > 20) {
            ArrayList<Integer> colorArray=new ArrayList<>();
            if (guessInput.equals("history")) {
                myHistory.printHistory();
            }
            else{
                System.out.println("That is not a valid color input");
            }
            System.out.print("Make your guess (or type \"history\"): ");
            Scanner scnr = new Scanner(System.in);
            guessInput = scnr.nextLine();
            if (guessInput.length()==4) {
                for (int i = 0; i < 4; i++) {
                    String colorChar = guessInput.substring(i, i + 1);
                    if (getColorInputCheck().get(colorChar) == null) {
                        colorArray.add(300);
                    } else {
                        colorArray.add(getColorInputCheck().get(colorChar));
                    }
                }
            }
            colorSum = 0;
            for (Integer d : colorArray) {
                colorSum += d;
            }
        }
        return guessInput;
    }
//   This will determine if cheat mode should be enabled and print out the answer if it should be
    public static void CheatMode(Code answer) {
        System.out.println("Do you want cheat mode enabled (y/n)?");
        Scanner scnr = new Scanner(System.in);
        String cheater = scnr.nextLine();
        if (cheater.equals("y")) {
            System.out.println("The answer is " + answer.ToString() + " but keep it quiet");
        }
    }
    // prints out the instructions for the game
    public static void Instructions() {
        System.out.println("Welcome to Mastermind! Become a code breaker!");
        System.out.println("Your computer opponent has created a secret code.");
        System.out.println("You have 10 guesses to break it! Create your guess");
        System.out.println("by choosing any combination of the 4 colors listed");
        System.out.println("(repeats are allowed");
        System.out.println();
        System.out.println("Colors: r (red), y (yellow), g (green), a (gray), p (purple), b (blue)");
        System.out.println("EX: To guess red,red,blue,blue, enter rrbb");
        System.out.println();
    }
//    Creates an answer, and enters the user into a loop that plays the game
    public static void main(String[] args) {
        Instructions();
        Code answer = new Code();
        History myHistory = new History();
        CheatMode(answer);
        int guessCount = 0;
        boolean gameEnd = false;
        while (guessCount < 10 && !gameEnd) {
            String myGuess = Guess(myHistory);
            Code guessCode = new Code(myGuess);
            String feedback = guessCode.feedback(answer);
            guessCount += 1;
            if (feedback.equals("hhhh")) {
                gameEnd = true;
                System.out.println("You won after only " + guessCount + " guesses!");
                System.out.println("The answer was " + answer.ToString());
            } else {
                myHistory.newGuess(guessCode, feedback);
                System.out.println("Code: " + guessCode.ToString() + " Feedback: " + feedback);
            }
        }
        if (guessCount==10) {
            System.out.println("You ran out of guesses :(");
            System.out.println(answer.ToString());
        }
    }
}


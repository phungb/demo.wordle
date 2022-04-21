import java.util.Scanner;

public class Display {
    WordleBoard board;
    Scanner input;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public Display(WordleBoard board, Scanner input) {
        this.board = board;
        this.input = input;
    }

    public void print() {
        printGuesses();
        printBlanks();
        clear();
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String promptGuess() {
        while (true) {
            System.out.println("Enter a word:");
            String guess = input.nextLine().toUpperCase();
            if (guess.length() != board.getWord().length()) {
                System.out.println("Please enter word of length " + board.getWord().length());
            } else {
                board.guess(guess);
                return guess;
            }
        }
    }

    public void printGuesses() {

        StringBuilder b = new StringBuilder();
        for (String s : board.guesses) {

            for (int i = 0; i < s.length(); i++) {
                String answer = board.getWord();
                char c = s.charAt(i);
                if (answer.charAt(i) == c) {
                    b.append(ANSI_GREEN + c + ANSI_RESET);
                } else if (answer.contains(Character.toString(c))) {
                    b.append(ANSI_YELLOW + c + ANSI_RESET);
                } else {
                    b.append(c);
                }
                b.append("|");
            }

            b.setLength((b.length() - 1));
            System.out.println(b.toString());
            b = new StringBuilder();
        }
    }

    public void printBlanks() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < board.getAttemptsRemaining(); i++) {
            for (int j = 0; j < board.getWord().length(); j++) {
                b.append("_|");
            }
            b.setLength(b.length() - 1);
            System.out.println(b.toString());
            b = new StringBuilder();
        }
    }
}

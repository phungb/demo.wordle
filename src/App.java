import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String[] words = {"HELLO", "WORLD", "LEARN", "CRATE"};
        String answer = words[new Random().nextInt(words.length)].toUpperCase();

        WordleBoard board = new WordleBoard(words);

        Scanner input = new Scanner(System.in);

        Display display = new Display(board, input);
        while (!board.isGameOver()) {
            display.print();
            display.promptGuess();
        }
        display.print();
        if (board.didWin()) {
            System.out.println("Congratulations, you guessed correctly");
        } else {
            System.out.println("Sorry, you did not guess correctly");
        }
        System.out.println("The answer was " + board.getWord());
        input.close();
    }
}

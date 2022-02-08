import java.util.Scanner;

public class Quint {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String answer = WordList.getWord();
        Puzzle puzzle = new Puzzle(answer);
        System.out.println(answer+"\n");

        System.out.print("=========\nQ U I N T\n=========\n\nGuess a 5-letter word\nguess ");
        int guesses = 0;

        while(!puzzle.isSolved() && in.hasNextLine()) {
            System.out.print(puzzle.compareGuess(in.nextLine()) + " ");
            guesses++;
        }
        System.out.println("\nGuessed in " + guesses + " tries");
    }
}


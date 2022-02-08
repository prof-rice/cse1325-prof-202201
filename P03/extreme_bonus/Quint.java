import java.util.Scanner;

public class Quint {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String answer = WordList.getWord();
        Puzzle puzzle = new Puzzle(answer);
        // System.out.println(answer+"\n");

        System.out.print("=========\nQ U I N T\n=========\n\nGuess a 5-letter word (? to give up)\nguess ");
        int guesses = 0;

        while(!puzzle.isSolved() && in.hasNextLine()) {
            try {
                String guess = in.nextLine();
                if(guess.equals("?")) break;
                System.out.print(puzzle.compareGuess(guess) + " ");
                guesses++;
            } catch (Exception e) {
                System.err.print("      Invalid guess: " + e.getMessage() + "\n      ");
            }
        }
        if(puzzle.isSolved()) 
            System.out.println("\nGuessed in " + guesses + " tries");
        else
            System.out.println("\nThe word was " + answer + "\nBetter luck next time!");
        
    }
}


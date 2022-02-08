public class Puzzle {
    private Word solution;
    private boolean solved;
    public Puzzle(String solution) {
        this.solution = new Word(solution);
        this.solved = false;
    }
    public boolean isSolved() {
        return solved;
    }
    public String compareGuess(String guess) {
        // For actual and check, letters will be replaced with spaces when found
        Word actual = new Word(solution.toString()); // Solution to the puzzle
        Word check = new Word(guess);                // Guess at the solution
        // For result, uppercase for correct, lower if in wrong position, '.' wrong
        Word result = new Word(".....");             // The result to be returned

        // Check for correct letter in correct position
        for(int i=0; i<5; ++i) {
            if(check.charAt(i) == actual.charAt(i)) {  // Correct letter!
                result.setCharAt(Character.toUpperCase(solution.charAt(i)), i);
                check.setCharAt(' ', i);
                actual.setCharAt(' ', i);
            }
        }
        
        // Determine if guess was right
        solved = (check.toString().equals("     "));
        
        // Check for correct letter in incorrect position
        for(int i=0; i<5; ++i) {  // iterate through actual and result
            for(int j=0; j<5; ++j) { // iterate through check (the guess)
                if(check.charAt(j) == ' ') continue; // This letter isn't available anymore
                if(check.charAt(j) == actual.charAt(i)) {  // Correct letter, wrong position
                    result.setCharAt(Character.toLowerCase(solution.charAt(i)), j);
                    check.setCharAt(' ', j);
                    actual.setCharAt(' ', i);
                    continue;
                }
            }
        }
        
        // Return the clue
        return addColor(guess, result.toString());
    }
    
    // Foreground color
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    // Background color
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    
    // ALWAYS append to string to reset colors to the defaults
    public static final String ANSI_RESET = "\u001B[0m";

    // Extreme Bonus 1: Colored Text
    private String addColor(String guess, String result) {
        StringBuilder colorful = new StringBuilder(ANSI_WHITE);  // Background color varies
        for(int i=0; i<5; ++i) {
            char c = result.charAt(i);
            if(c == '.')                      colorful.append(ANSI_BLACK_BACKGROUND);
            else if(Character.isLowerCase(c)) colorful.append(ANSI_YELLOW_BACKGROUND);
            else                              colorful.append(ANSI_GREEN_BACKGROUND);
            colorful.append(guess.charAt(i));
        }
        return colorful.append(ANSI_RESET).toString();
    }
}


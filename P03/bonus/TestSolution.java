// TestSolution verifies that solutions are correctly detected by Puzzle

public class TestSolution {
    private static int result = 0;
    private static void fail(String message) {
        System.err.println(message);
        ++result;
    }
    public static void main(String[] args) {
        String solution =  "RAISE";
        Puzzle puzzle = new Puzzle(solution);
        if(puzzle.isSolved())
            fail("FAIL: Puzzle claimed solution prematurely");
        if(!puzzle.compareGuess(solution).equals(solution))
            fail("FAIL: Puzzle did not store solution");
        if(!puzzle.isSolved())
            fail("FAIL: Puzzle did not recognize solution");
        System.exit(result);
    }

}

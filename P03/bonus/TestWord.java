// TestWord verifies data validation rules for Word

public class TestWord {
    public static class MissingException extends Exception {
        public MissingException(String message) {
            super("Didn't detect invalid setCharAt: " + message);
        }
    }
    public static void main(String[] args) throws MissingException {
        Word word = new Word("RAISE");
        try {
            word.setCharAt('A', 0);
            word.setCharAt('m', 1);
            word.setCharAt('z', 2);
            word.setCharAt(' ', 3);
            word.setCharAt('.', 4);
            try {
                word.setCharAt('$', 0);
                throw new MissingException("$");
            } catch (IllegalArgumentException e) {
            }
            try {
                word.setCharAt('~', 0);
                throw new MissingException("~");
            } catch (IllegalArgumentException e) {
            }
            try {
                word.setCharAt('A', -1);
                throw new MissingException("-1");
            } catch (IllegalArgumentException e) {
            }
            try {
                word.setCharAt('A', 5);
                throw new MissingException("5");
            } catch (IllegalArgumentException e) {
            }
        } catch (Exception e) {
            System.err.println("FAIL: " +  e.getMessage());
            System.exit(-1);
        }
            
    }

}

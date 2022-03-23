// A student asked for a less contrived example for try / catch. 
// Here's one that converts digits into their morse code equivalent.
// If you have questions about how this works, particularly,
//   the try / multiple catch clause, ask!

public class MorseCode {
    private static final String[] numbers = {
        "-----",
        ".----",
        "..---",
        "...--",
        "....-",
        ".....",
        "-....",
        "--...",
        "---..",
        "----."};
    public static String morse(int digit) { // converts digit to morse code representation
        if (0 > digit || digit > 9) throw new IllegalArgumentException("Argument " + digit + " must be between 0 and 9, inclusive");
        return numbers[digit];
    }
    public static void main(String[] args) {
        if(args.length == 0)
            System.err.println("usage: java MorseCode singleDigit [singleDigit...]");
        for(int i=0; i<args.length; ++i) {
            try { // watch for exceptions from method morse
                System.out.print(morse(Integer.parseInt(args[i])) + " ");
            } catch(NumberFormatException e) {
                System.err.println("\n" + args[i] + " is not an integer: " + e);
            } catch(IllegalArgumentException e) {
                System.err.println("\n" + e);
            } catch(Exception e) {
                System.err.println("\nAn unexpected exception occurred with " + args[i] + ": " + e);
            }
        }
        System.out.println();
    }
}

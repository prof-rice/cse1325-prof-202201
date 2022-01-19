public class Test {
    public static void main(String[] args) {
        String d1 = System.console().readLine("Enter a double: ");
        String d2 = System.console().readLine("Enter a double: ");
        System.console().printf("%f\n", Double.parseDouble(d1)
                                      + Double.parseDouble(d2));
    }
}   

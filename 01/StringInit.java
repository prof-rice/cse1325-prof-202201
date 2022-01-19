public class StringInit {
    public static void main(String[] args) {
        String s1 = "Hello ";
        String s2 = new String("World ");
        String s3 = new String(s1);
        //String s4("Everybody ");
        System.out.println(s1 + s2 + s3);
        
        int a = 1;
        int b = 2;
        int c = 3;
        System.out.println("" + a + " " + b + " " + c);
        System.out.println(String.valueOf(a) + " " + Integer.toString(b) + " " + String.format("%d", c));
        System.out.printf("%d %d %d\n", a, b, c);
        
    }
}

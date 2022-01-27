public class Hello {
    public static void main(String[] args) {
        String name = System.getenv("USER");
        System.out.println("Hello, " + name + "!");
    }
}   

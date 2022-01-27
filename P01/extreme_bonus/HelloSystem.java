import java.io.IOException;

public class HelloSystem {
    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(
            Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    public static void main(String[] args) throws IOException {
        System.out.print("Hello, " + execCmd("git config user.name"));
    }
}

import java.util.Scanner;
import java.io.IOException;

public class Crypto {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        boolean isRot13 = false;
        boolean isOTP = false;

        String key = null;
        if(args.length > 0) {
            if(args[0].toLowerCase().equals("rot13")) {
                isRot13 = true;
            } else if(args[0].toLowerCase().equals("otp")) {
                isOTP = true;
                key = args[1];
            } else {
                key = args[0];
                if(key.length() != 26) {
                    System.err.println("usage: java Crypto [key | rot13 | otp filename]");
                    System.exit(0);
                }
            }
        } else {
            char[] k = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            char temp = 'a';
            for(int i=0; i<100; ++i) {
                int a = (int) (k.length * Math.random());
                int b = (int) (k.length * Math.random());
                temp = k[a];
                k[a] = k[b];
                k[b] = temp;
            }
            key = new String(k);
            System.out.println("Key = " + key);
        }

        Cypher cypher;
        if(isRot13) cypher = new Rot13();
        else if(isOTP) cypher = new OneTimePad(key);
        else cypher = new Substitution(key);

        char command = ' ';
        String line = null;
        while(true) {
            do {
                System.out.print("(e)ncrypt, (d)ecrypt, or (q)uit? ");
                command = in.nextLine().toCharArray()[0];
            } while(command != 'e' && command != 'd' && command != 'q');
            if(command == 'q') break;
            System.out.println("Enter text to " + ((command == 'e') ? "encrypt" : "decrypt"));
            while(true) {
                line = in.nextLine();
                if (line.isEmpty()) break;
                if(command == 'e') System.out.println(cypher.encrypt(line));
                if(command == 'd') System.out.println(cypher.decrypt(line));
            }
        }
    }
}

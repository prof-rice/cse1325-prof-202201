import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// WARNING: Not compatible with Windows native, not tested on WSL2 or Mac
// Tested successfully on Ubuntu and Chromebook Linux
public class HelloPasswd {
    public static void main(String[] args) 
            throws IOException {
        String name = "Friend";
        String username = System.getenv("USER");
        BufferedReader in = new BufferedReader(new FileReader("/etc/passwd"));
        while (true) {
            String s = in.readLine();
            if(s.indexOf(username) >= 0) { // Found user's record, looks like
                // ricegf:x:1000:1000:George F. Rice,,,:/home/ricegf:/bin/bash
                name = s.split(":")[4].split(",")[0];
                break;
            }
        }
        System.out.println("Hello, " + name + "!");
    }
}   

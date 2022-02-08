import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordList {
    public static final String FILENAME = "wordlist.txt";
    public static String getWord() {
        if(words == null) { // load words on first call
            try {
                BufferedReader br = new BufferedReader(new FileReader((FILENAME)));
                words = new ArrayList<>();
                String line;
                while((line=br.readLine())!=null) words.add(line);
            } catch(IOException e) {
                System.err.println("Unable to load words from " + FILENAME);
                return "ERROR";
            }
        }        
        return words.get((int) (words.size() * Math.random()));
    }
    private static ArrayList<String> words;
}


// This prints a frequency distribution for words returned by WordList.getWord();
// Additional analysis will be required to determine any anomalous conditions.
//
// A HashMap is an ArrayList that can use any non-primitive type as its index.
// The index is called the "key", and the value the, well, "value".
// Instead of add(value), it uses put(key, value).

import java.util.HashMap;

public class TestWordList {
    public static void main(String[] args) {
        HashMap<String, Integer> words = new HashMap<>();
        for(int i=0; i<10000; ++i) {
            String word = WordList.getWord();
            int count = 1 + ((words.containsKey(word)) ? words.get(word) : 0);
            words.put(word, count);
        }
        for(String word : words.keySet()) 
            System.out.printf("%5s %6d\n", word, words.get(word));
    }
}

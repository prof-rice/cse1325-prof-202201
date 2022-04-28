import java.util.ArrayList;
import java.util.Objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindMinHash {
    public final long PACKET_SIZE = 100000; // Number of hashes to find per packet
    class WordWrapper {
        public WordWrapper() {
            word = "default";
            value = Integer.MAX_VALUE;
        }
        public WordWrapper(int word0, int word1) {
            word = wordList.get(word0) + " " + wordList.get(word1);
            value = word0 * word0 - 3 * word1 + 17;
        }
        @Override
        public int hashCode() {
            return Math.abs(Objects.hash(word, value)); // Not a normal hashCode!
        }
        public final String word;
        public final int value;
    }
    public FindMinHash(String filename, long maxHashes) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while((word = br.readLine()) != null) wordList.add(word);
        } catch(Exception e) {
            throw new IOException("Unable to load word list from " + filename);
        }
        this.maxHashes = maxHashes;
    }
    public void search() {
        System.out.printf("%-11s (ID %3s)\n", // searching %,15d to %,15d\n",
            Thread.currentThread().getName(), Thread.currentThread().getId());
//                start, start + PACKET_SIZE);
        int bestHashCode = Integer.MAX_VALUE;
        while(true) {
            long start = nextPacket(); if(start < 0) break;
            int word0 = (int) (start % wordList.size());
            int word1 = (int) ((start / wordList.size()) % wordList.size());
        
            long number = PACKET_SIZE;
            while(number-- > 0) {
                WordWrapper candidate = new WordWrapper(word0, word1);
                if(candidate.hashCode() < bestHashCode) {
                    bestHashCode = setBestWord(candidate);
                }
                if(++word0 >= wordList.size()) {
                    word0 = 0;
                    ++word1;
                }
            }
        }
    }
    public synchronized long nextPacket() { // Returns the next range of candidates
        nextStart += PACKET_SIZE;
        return (nextStart < maxHashes) ? nextStart : -1;
    }
    public synchronized int setBestWord(WordWrapper candidate) {
        if(candidate.hashCode() < bestWord.hashCode()) bestWord = candidate;
        return bestWord.hashCode();
    }
    public static void main(String[] args) {
        if(1 > args.length || args.length > 3) {
            System.err.println("usage: java FindMinHash <#strings> [#threads] [filename]");
            System.exit(-1);
        }

        final long maxHashes = Long.parseLong(args[0]);
        
        final int numThreads = (args.length > 1) ? Integer.parseInt(args[1]) : 1;
        
        FindMinHash f = null;
        try {
            f = new FindMinHash((args.length > 2) ? args[2] : "all-words.txt", maxHashes);
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(-2);            
        }
        final FindMinHash findMinHash = f; // must be final to use in a lambda

        Thread[] threads = new Thread[numThreads];
        
       for(int t=0; t<numThreads; ++t) {
            threads[t] = new Thread(() -> findMinHash.search());
            threads[t].start();  // Threads now work packets from the pool
        }
        
        for(Thread t : threads) try {t.join();} catch (InterruptedException e) { }
        
        System.out.println("Best word \"" + findMinHash.bestWord.word + "\" has hashCode " 
                         + String.format("%,d", findMinHash.bestWord.hashCode()));
    }
    private long nextStart = -PACKET_SIZE;
    private final long maxHashes;
    private WordWrapper bestWord = new WordWrapper();
    private final ArrayList<String> wordList= new ArrayList<>();
}

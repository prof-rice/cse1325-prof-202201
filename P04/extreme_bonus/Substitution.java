import java.util.Arrays;

public class Substitution implements Cypher {
    public Substitution(String key) {
        // Data validation: 26 characters
        if(key.length() != 26) 
            throw new IllegalArgumentException("Key length error");

        // Data validation: Every character exactly once
        char[] sorted = key.toCharArray();
        Arrays.sort(sorted);
        if(!(new String(sorted)).equals("abcdefghijklmnopqrstuvwxyz"))
            throw new IllegalArgumentException("Key incomplete");

        // Save keys
        encryptKey = key.toCharArray();
        decryptKey = key.toCharArray();

        // Reverse decryptKey
        char k = 'a';
        for(char c : key.toCharArray()) decryptKey[c-'a'] = k++;
    }
    @Override
    public String encrypt(String unencrypted) {
        return substitute(unencrypted, encryptKey);
    }
    @Override
    public String decrypt(String encrypted)  {
        return substitute(encrypted, decryptKey);
    }
    protected String substitute(String original, char[] key) {
        StringBuilder encrypted = new StringBuilder();
        for(char c : original.toCharArray()) {
            if(!Character.isLetter(c)) encrypted.append(c);
            else {
                char newC = key[Character.toLowerCase(c)-'a'];
                if(Character.isUpperCase(c)) newC = Character.toUpperCase(newC);
                encrypted.append(newC);
            }
        }
        return encrypted.toString();
    }
    protected char[] encryptKey;
    protected char[] decryptKey;
}

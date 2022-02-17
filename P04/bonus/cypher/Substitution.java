package cypher;

import java.util.Arrays;

/**
 * Implements a simple substitution cypher for a specified key.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Substitution implements Cypher {
    public Substitution(String key) {
        // Data validation: 26 characters
        if(key.length() != 26) 
            throw new IllegalArgumentException("Key length error");

        // Data validation: Every character exactly once
        char[] sorted = key.toLowerCase().toCharArray();
        Arrays.sort(sorted);
        if(!(new String(sorted)).equals("abcdefghijklmnopqrstuvwxyz"))
            throw new IllegalArgumentException("Key incomplete");

        // Save keys
        encryptKey = key.toLowerCase().toCharArray();
        decryptKey = key.toLowerCase().toCharArray();

        // Reverse decryptKey
        char k = 'a';
        for(char c : key.toCharArray()) decryptKey[c-'a'] = k++;
        
        // System.out.println(new String(encryptKey));
        // System.out.println(new String(decryptKey));
    }
    /**
     * Encrypt a string using the specified substition key.
     *
     * @param unencrypted    The text to be encrypted
     * @returns              The encrypted text
     * @since                1.0
     */
    @Override
    public String encrypt(String unencrypted) {
        return substitute(unencrypted, encryptKey);
    }
    /**
     * Decrypt a string using the specified substition key.
     *
     * @param encrypted    The text to be decrypted
     * @returns            The decrypted text
     * @since              1.0
     */
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

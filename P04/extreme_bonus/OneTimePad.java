import java.io.IOException;
import java.util.Base64;

public class OneTimePad implements Cypher {
    private final byte[] pad;
    private int encryptionIndex = 0;
    private int decryptionIndex = 0;
    public OneTimePad(String filename) throws IOException {
        pad = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filename));
    }
    private byte[] xOr(byte[] array, boolean isEncrypting) {
         for(int i=0; i<array.length; ++i) {
            byte key = isEncrypting ? pad[encryptionIndex++] : pad[decryptionIndex++];
            array[i] ^= key;
        }
        return array;
    }
    @Override
    public String encrypt(String unencrypted) {
        byte[] encrypted = xOr(unencrypted.getBytes(), true);   // convert to bytes and encrypt
        byte[] encoded = Base64.getEncoder().encode(encrypted);     // encode to Base64 ASCII
        return new String(encoded);                                 // return encrypted encoded string
    }
    @Override
    public String decrypt(String encrypted)  {
        byte[] decoded = Base64.getDecoder().decode(encrypted.getBytes());  // decode Base64
        byte[] decrypted = xOr(decoded, false);                 // decrypt
        String unencrypted = new String(decrypted);                 // convert back to string
        return unencrypted;
    }
}

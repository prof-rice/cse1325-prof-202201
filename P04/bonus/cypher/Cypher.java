package cypher;

/**
 * Specifies the interface to a cypher, which encrypts and decrypts strings.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public interface Cypher {
    /**
     * Encrypt a string using the implemented cypher and key.
     *
     * @param unencrypted    The text to be encrypted
     * @returns              The encrypted text
     * @since                1.0
     */
    public String encrypt(String unencrypted);
    /**
     * Decrypt a string using the implemented cypher and key.
     *
     * @param encrypted    The text to be decrypted
     * @returns            The decrypted text
     * @since              1.0
     */
    public String decrypt(String encrypted);
}

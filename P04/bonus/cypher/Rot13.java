package cypher;

/**
 * Implements the Rot13 cypher, rotating each letter 13 alphabetic positions.
 *
 * @author             Professor George F. Rice
 * @version            1.0
 * @since              1.0
 * @license.agreement  Gnu General Public License 3.0
 */
public class Rot13 extends Substitution {
    /**
     * Constructs a Rot13 cypher object
     *
     * @since              1.0
     */
    public Rot13() {
        super("nopqrstuvwxyzabcdefghijklm");
    }
}

public class Word {
    char[] letters = new char[5];
    public Word(String word) {
        letters = word.toUpperCase().toCharArray();
    }
    public char charAt(int position) {
        return letters[position];
    }
    public void setCharAt(char c, int position) {
        letters[position] = c;
    }
    @Override
    public String toString() {
        return new String(letters);
    }
}


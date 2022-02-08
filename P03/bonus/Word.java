public class Word {
    private char[] letters = new char[5];

    private void validateMarker(char c) {
        if (c == '.') return;
        if (c == ' ') return;
        validateChar(c);
    }    
    private void validateChar(char c) {
        char u = Character.toUpperCase(c);
        if (('A' <= u) && (u <= 'Z')) return;
        throw new IllegalArgumentException("Not a letter: " + c); 
    }
    
    private void validatePosition(int position) {
        if(position < 0 || position > 4) 
            throw new IllegalArgumentException("Invalid charAt: " + position);
    }
    
    public Word(String word) {
        if(word.length() != 5) 
            throw new IllegalArgumentException("Not 5 letters: " + word);
        if(!word.equals("....."))
            for(char c : word.toCharArray()) validateChar(c);
        letters = word.toUpperCase().toCharArray();
    }
    public char charAt(int position) {
        validatePosition(position);
        return letters[position];
    }
    public void setCharAt(char c, int position) {
        validateMarker(c);
        validatePosition(position);
        letters[position] = c;
    }
    @Override
    public String toString() {
        return new String(letters);
    }
}


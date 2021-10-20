public class OffByOne implements CharacterComparator{

    /* Return true when two characters differ to by one */
    @Override
    public boolean equalChars(char x, char y) {
        // From the internet Char values on Java are really just integers
        int diff = x - y;
        if (diff == -1 || diff == 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        OffByOne test = new OffByOne();
        System.out.println(test.equalChars('a', 'c'));
    }
}

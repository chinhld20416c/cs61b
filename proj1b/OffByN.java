public class OffByN implements CharacterComparator{

    public int diffCharaters;
    // Constructor
    public OffByN(int n) {
        diffCharaters = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return equalChars(x, y, diffCharaters);
    }

    public boolean equalChars(char x, char y, int diffC) {
        int diff = x - y;
        if (diff == diffC || diff == - diffC) {
            return true;
        }
        return false;
    }

}

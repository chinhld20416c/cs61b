import jdk.swing.interop.SwingInterOpUtils;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> adeque = new ArrayDeque<>();
        int wsize = word.length();
        for (int i = 0; i < wsize; i += 1) {
            adeque.addLast(word.charAt(i));
        }
        return adeque;
    }
    /* Return true if word writes by ways both front to back and back to front are the same */
    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        int midWord = word.length() / 2;
        for (int i = 0; i < midWord; i++) {
            if (!d.removeFirst().equals(d.removeLast())) {
                return false;
            }
        }
        return true;
    }
    /* Return true if word is palindrome according to the requirement of method equalChar in CC class */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }
        Deque<Character> d = wordToDeque(word);
        int midWord = word.length() / 2;
        for (int i = 0; i < midWord; i++) {
            if (!cc.equalChars(d.removeFirst(), d.removeLast())) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("a".compareTo("b"));
        System.out.println("A".compareTo("a"));
        System.out.println('a'-'f');
        System.out.println('A' - 'a');
    }
}

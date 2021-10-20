import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean res = palindrome.isPalindrome("noonfnoon");
        assertTrue(palindrome.isPalindrome("noonfnoon"));
        assertTrue(palindrome.isPalindrome("noonnoon"));
        assertFalse(palindrome.isPalindrome("abbbas"));
    }

    static CharacterComparator cc;
    @Test
    public void testIsPalindromeCC() {
        cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("abcba", cc));
        cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("flaqa", cc));
        assertFalse(palindrome.isPalindrome("flake", cc));
    }

}     //Uncomment this class once you've created your Palindrome class.



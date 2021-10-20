import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        System.out.println(offByOne.equalChars('a', 'b'));
        System.out.println(offByOne.equalChars('r', 'q'));
        System.out.println(offByOne.equalChars('a', 'e'));
        System.out.println(offByOne.equalChars('z', 'a'));
        System.out.println(offByOne.equalChars('&', '%'));

        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'a'));
    }
} //Uncomment this class once you've created your CharacterComparator interface and OffByOne class.*/
package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;


/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(33);
        int actual = arb.dequeue();
        int expect = 33;
        assertEquals(expect, actual);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(32);
        arb.enqueue(40);
        actual = arb.dequeue();
        assertEquals(32, actual);
        actual = arb.peek();
        assertEquals(40, actual);

    }

}

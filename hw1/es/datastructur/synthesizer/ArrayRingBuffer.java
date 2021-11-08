package es.datastructur.synthesizer;
import java.util.Iterator;



public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {

        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /* The size of the buffer */
    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {

        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (last == rb.length) {
            last = 0;
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first == rb.length) {
            first = 0;
        }
        T delReturn = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        return delReturn;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {

        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        if (first == rb.length) {
            first = 0;
        }
        return rb[first];
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    /* Return an iterator object*/
    public Iterator<T> iterator() {
        return new RingBufferIterator<T> ();
    }
    private class RingBufferIterator<T> implements Iterator<T> {
        private int wid = first;
        // Return true if RingBuffer has more elements
        public boolean hasNext() {
            if (isEmpty()) {
                return false;
            }
            return wid < last;
        }
        public T next() {
            T ele = (T) rb[wid];
            wid += 1;
            if (wid == rb.length) {
                wid = 0;
            }
            return ele;
        }
    }
    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer other = (ArrayRingBuffer) o;
        int capOther = other.fillCount();
        if ( capOther != this.fillCount()) {
            return false;
        }
        for (int i = 0; i < capOther; i += 1) {
            if (!other.dequeue().equals(this.dequeue())) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(Math.round(9.499));
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(5);
        arb.enqueue(5);
        arb.enqueue(4);
        arb.enqueue(7);
        arb.enqueue(45);
//        Iterator seer = arb.iterator();
//        while (seer.hasNext()) {
//            System.out.println(seer.next());
//        }
//        for (int i : arb) {
//            System.out.println(i);
//        }
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(5);
        arb2.enqueue(5);
        arb2.enqueue(4);
        arb2.enqueue(7);
        arb2.enqueue(45);
        System.out.println(arb2.equals(arb));
    }
}
    // TODO: Remove all comments that say TODO when you're done.

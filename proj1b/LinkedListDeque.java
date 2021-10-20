public class LinkedListDeque<T> implements Deque<T>{
    private class IntNode {
        T item;
        IntNode pre;
        IntNode next;
        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            pre = p;
            next = n;
        }
    }
    /* Invariant:
     * The lasItem.next always pointing at sentinel?? why, to do what
     * The sentinel.next is the first item of deque
     * The sentinel.pre is the last item of deque */
    private IntNode sentinel;
    private int size;
    /* List constructors */
    public LinkedListDeque() {
        sentinel = new IntNode( null, null, null);
        sentinel.next = sentinel;  // why
        sentinel.pre = sentinel.next;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(item, sentinel, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }
    /* Constructor which creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new IntNode( null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel.next;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }

    }
    /* Add an item to the front of the deque
    *  Add to the front not make lastItem change,
    * but the first time to addFirst to empty deque that the first is the last */
    @Override
    public void addFirst(T item) {
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        size++;
        sentinel.next.next.pre = sentinel.next;  // After addFirst, oldFirst.pre = newFirst

    }
    /* Add an item to the end of the deque */
    @Override
    public void addLast(T item) {
        sentinel.pre = new IntNode(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre; // After addLast x, then oldLast.next = newLast
        size++;
    }

    /* Return the number of items in the deque */
    @Override
    public int size() {
        return size;
    }

    /* Print all items in deque in order, separated by a space, and a new line at the end*/
    @Override
    public void printDeque() {
        for (IntNode p = sentinel.next; p != sentinel; p = p.next) {
            System.out.println(p.item);
        }
        System.out.println();
    }
    /* Remove and return the item at the front of deque */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T frontRemove = sentinel.next.item;
        IntNode temp = sentinel.next.next;
        temp.pre = sentinel;
        sentinel.next.next = null;
        sentinel.next.pre = null;
        sentinel.next = temp;
        size--;
        return frontRemove;
    }
    /* Remove and return the item at the back of deque */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T backRemove = sentinel.pre.item;
        IntNode temp = sentinel.pre.pre;
        temp.next = sentinel;
        sentinel.pre.next = null;
        sentinel.pre.pre = null;
        sentinel.pre = temp;
        size--;
        return backRemove;
    }
    /* HELPER METHOD OF GET */

    /* Starting from the front of deque to the end */
    public T getFromFirst(int index) {
        int j = 0;  // The 1st index starting from the front of deque to back is 0
        IntNode startFirst = sentinel.next;
        while (j != index) {
            startFirst = startFirst.next;
            j++;
        }
        return startFirst.item;
    }
    /* Starting from the end of deque to front */
    public T getFromLast(int index) {
        int j = -1;  // The 1st index starting from the end of deque to the front is -1
        IntNode startLast = sentinel.pre;
        while (j != (index - size)) {
            // The index of desire item computing from the last (index: -1) to the front always equals index (from the first) minus size
            startLast = startLast.pre;
            j--;
        }
        return startLast.item;
    }
    /* Get desire index item */
    @Override
    public T get(int index) {
        // The position at the middle of deque
        int midPos = (size - 1) / 2;

        if (index <= midPos) {
           return getFromFirst(index);
        }
        return getFromLast(index);
    }
    /* Helper method getRecursive(int Index) */
    private T getRecursive(IntNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        p = p.next;
        index = index - 1;
        return getRecursive(p, index);
    }
    /* Get desire index item using recursion */
    public T getRecursive(int index) {
        IntNode temp = sentinel.next;
        return getRecursive(temp, index);

    }

}

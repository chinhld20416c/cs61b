public class LinkedListDeque<T> {

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

    public IntNode sentinel;
    public int size;

    /* Create empty deque */
    public LinkedListDeque() {
        sentinel = new IntNode( null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }
    /* Invariant: The first item of deque starting at sentinel.next
    * The lasItem.next always pointing at sentinel
    * And the sentinel.pre is the last item of deque */
    public LinkedListDeque(T item) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(item, sentinel, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }
    /* Create a deep copy of other */
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new IntNode( null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i++) {
            addLast(other.get(i));
        }

    }
    /* Add an item to the front of the deque
    *  Add to the front not make lastItem change, but the first time add first to empty deque*/
    public void addFirst(T item) {
        sentinel.next = new IntNode(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;  // The IntNode following of the first IntNode (or sentinel.next) has pre field pointing at the first IntNode
        size++;
        /* Very magic, we don't need block code below anymore */
//        if (size == 1) {
//            sentinel.pre = sentinel.next;  // In this case when size == 1, sentinel.next.next point at sentinel
//        }
    }
    /* Add an item to the end of the deque */
    public void addLast(T item) {
        sentinel.pre = new IntNode(item, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre; // The IntNode front of the last IntNode (or sentinel.pre) has next field pointing at the last IntNode
        size++;
    }
    /* Return true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }
    /* Return the number of items in the deque */
    public int size() {
        return size;
    }
    /* Print all items in deque in order, separated by a space, and a new line at the end*/
    public void printDeque() {
        IntNode p = sentinel.next;
        int i = size;
        while (i != 0) {
            System.out.print(p.item + " ");
            p = p.next;
            i--;  // Non-destructure size
        }
        System.out.println();
    }
    /* Remove and return the item at the front of deque */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T frontRemove = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return frontRemove;
    }
    /* Remove and return the item at the back of deque */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T backRemove = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
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
    public static void main(String[] args) {
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addFirst(2);
        lld.addLast(77);
        lld.addFirst(3);
        lld.addFirst(99);
        lld.addLast(1);  // 99 3 2 77 1
//        lld.addFirst("a");
//        lld.addFirst("b");
//        lld.addFirst("c");
//        lld.addFirst("d");
//        lld.addFirst("e");

//        for (int i = 0; i < lld.size(); i++) {
//            System.out.println(lld.get(i));
//        }
//        System.out.println(lld.removeFirst());
//        System.out.println(lld.removeLast());
//        System.out.println(lld.removeLast());
//        System.out.println(lld.removeFirst());
//        System.out.println(lld.removeFirst());
//        System.out.println(lld.removeFirst());
//
//        System.out.println(lld.size());
        LinkedListDeque<Integer> copyLLD = new LinkedListDeque<>(lld);
        for (int i = 0; i < copyLLD.size(); i++) {
            System.out.println(copyLLD.get(i));
        }
        copyLLD.addFirst(75);
        System.out.println(copyLLD.get(0));
        System.out.println(copyLLD.size());
        System.out.println(lld.get(0));
        System.out.println(lld.size());
    }
}

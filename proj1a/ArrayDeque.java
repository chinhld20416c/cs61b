public class ArrayDeque<T> {
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;
    /* array items = [5 4 7 0 0 0 0 6 9]
                      0 1 2 3 4 5 6 7 8
    nextFirst = 8 --> nextFirst = -1
    size = 5
    addLast()
    items[nextFirst + 1] 6 9 5 4 7

    Invariant:
    The first item of deque starts at nextFirst + 1.
    The last item of deque is nextLast - 1
    but special case when
    Last = items.length - 1 then the nextLast must be 0. And
    First = 0 then the nextFirst must be items.length - 1.
    */
    /* Create an empty array deque */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    /* Create a deep copy from other */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
        int sizeTemp = size;

    }
    /* Return true if the last item at the end of array */
    public boolean checkLast() {
        return (nextLast - 1 == items.length - 1);
    }
    /* Return true if the first item at 0 index of array */
    public boolean checkFirst() {
        return (nextFirst + 1 == 0);
    }
    /* Resize ArrayDeque smaller when special amount of times remove*/
    public void resizeSmaller() {
        int r = size / items.length;  // The required lowest rate size of deque and length of array is 0.25
        if (items.length >= 16 && r < 0.25) {
            T[] newItems = (T[]) new Object[items.length / 2];  // length of array has half left
            T[] temp = this.arrangeDeque();
            System.arraycopy(temp,0, newItems, 0, size);
            items = newItems;
            // After resize, we have to modify nextFirst and nextLast from scratch
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }
    /* Resize ArrayDeque bigger */
    public void resizeADeque() {
        if (size == items.length) {
            T[] newItems = (T[]) new Object[size * 2];
            T[] temp = this.arrangeDeque();
            System.arraycopy(temp, 0, newItems, 0, size);
            items = newItems;
            // After resize, we have to modify nextFirst and nextLast from scratch
            nextFirst = items.length - 1;
            nextLast = size;
        }
    }
    /* Add an item to the front of the deque */
    public void addFirst(T item) {
        resizeADeque();
        if (checkFirst()) {
            nextFirst = items.length - 1;
        }

        items[nextFirst] = item;
        nextFirst--;
        size++;
    }
    /* Add an item to the end of the deque */
    public void addLast(T item) {
        resizeADeque();
        if (checkLast()) {
            nextLast = 0;
        }
        items[nextLast] = item;
        nextLast++;
        size++;
    }
    /* Return true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return size == 0;
    }
    /* Return a size of the deque */
    public int size() {
        return size;
    }

    /* Print all item from head to end deque */
    public void printDeque() {
        int p = nextFirst;
        for ( int i = 0; i < size; i++) {
            if (p == items.length - 1) {
                p = -1;
            }
            System.out.print(items[p + 1] + " ");
            p++;
        }
        System.out.println();
    }
    /* Remove the first item of deque and return it */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int p = nextFirst;
        T firstRemove;
        if (p == items.length - 1) {
            firstRemove = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            firstRemove = items[p + 1];
            items[p + 1] = null;
            nextFirst = p + 1;
        }
        size--;
        resizeSmaller();
        return firstRemove;
    }
    /* Remove the last item of the deque and return it */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastRemove;
        int p = nextLast;
        if (p == 0) {
            lastRemove = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        } else {
            lastRemove = items[p - 1];
            items[p - 1] = null;
            nextLast = p - 1;
        }
        size--;
        resizeSmaller();
        return lastRemove;
    }
    /* HELPER METHOD TO ARRANGE THE DEQUE IN ORDER */
    public T[] arrangeDeque() {
        T[] temp = (T[]) new Object[items.length];
        int p = nextFirst;
        for (int i = 0; i < size(); i++) {
            if (p == items.length - 1) {
                p = -1;
            }
            temp[i] = items[p + 1];
            p++;
        }
        return temp;
    }
    /* Return the desire item */
    public T get(int index) {
        if (index >= this.size()) {
            return null;
        }
        int p = nextFirst;
        for ( int i = 0; i <= index; i++) {
            if (p == items.length - 1) {
                p = -1;
            }
            p++;
        }
        return items[p];
    }

    public static void main(String[] args) {
        ArrayDeque<int[]> arrd = new ArrayDeque<>();
//        arrd.addFirst("c");
//        arrd.addLast("d");
//        arrd.addLast("e");
//        arrd.addLast("f");
//        arrd.addFirst("b");
//        arrd.addLast("g");
//        arrd.addFirst("a");  // 3 4 5 49 99 77 6
//        System.out.println(arrd.removeFirst()); // a
//        System.out.println(arrd.removeLast());  // g
//        System.out.println(arrd.removeFirst());  // b
//        System.out.println(arrd.removeLast());  // f
        int[] a = new int[] {1, 2};
        int[] b = new int[] {3, 4};
        int[] c = new int[] {5, 6};

        arrd.addLast(a);

        ArrayDeque temp = new ArrayDeque(arrd);
        System.out.println(temp.removeFirst());
        temp.printDeque();  // c d e
        arrd.printDeque();
        System.out.println(arrd.size());  // 3
        System.out.println(temp.size());  // 4

    }


}

public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    /* Invariant:
    The first item of deque starts at nextFirst + 1.
    The last item of deque is nextLast - 1
    but special case when
    Last = items.length - 1 then the nextLast must be 0. And
    First = 0 then the nextFirst must be items.length - 1 */


    /* Create an empty array deque */
    public ArrayDeque() {
        items = (T[]) new Object[3];
        nextFirst = 0;  // If addFist the first index is 0, so nextLast is 1 and vice versa
        nextLast = 1;
        size = 0;
    }
    /* Contructor which creates a deep copy from other */
    public ArrayDeque(ArrayDeque<T> other) {
        int n = other.items.length;
        items = (T[]) new Object[n];
        System.arraycopy(other.items,0, items,0, n);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size();
    }

    /* Return true if the last item at the end of array */
    public boolean checkLast() {
        return nextLast == items.length;
    }

    /* Return true if the first item at 0 index of array */
    public boolean checkFirst() {
        return nextFirst == -1;
    }
    /** HELPER METHOD FOR RESIZE */
    private void newArray(T[] newItems) {
        int firstIndex = nextFirst + 1;
        if (nextFirst == items.length - 1) {
            firstIndex = 0;
        }
        int fToEnd = items.length - firstIndex;
        if (fToEnd <= this.size()) {
            System.arraycopy(items, firstIndex, newItems, 0, fToEnd);
            System.arraycopy(items, 0, newItems, fToEnd, this.size() - fToEnd);
        } else {
            System.arraycopy(items, firstIndex, newItems, 0, this.size());
        }
        items = newItems;
        // After resize, we have to modify nextFirst and nextLast from scratch
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /* Resize ArrayDeque smaller when special amount of times remove*/
    public void resizeSmaller() {
        int r = size / items.length;  // The required lowest rate size of deque and length of array is 0.25
        if (items.length >= 16 && r < 0.25) {
            T[] newItems = (T[]) new Object[items.length / 2];  // length of array has half left
            newArray(newItems);
        }
    }

    /* Resize ArrayDeque bigger */
    public void resizeADeque() {
        if (size == items.length) {
            T[] newItems = (T[]) new Object[size * 2];
            newArray(newItems);
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

    /* Return a size of the deque */
    public int size() {
        return size;
    }

    /* Return true if deque is empty, false otherwise */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /* Print all item from head to end deque */
    public void printDeque() {
        int p = nextFirst;
        for (int i = 0; i < size; i++) {
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
        int firstIndex = nextFirst + 1;
        if (nextFirst == items.length - 1) {
            firstIndex = 0;
        }
        int i = firstIndex + index;
        if (i >= items.length) {
            return items[i - items.length];
        }
        return items[firstIndex + index];
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> test = new ArrayDeque<>();
        test.addLast(7);
        test.addFirst(6);
        test.addFirst(5);
        test.addLast(8);
        test.addFirst(11);
        test.removeLast();
        ArrayDeque<Integer> copyT = new ArrayDeque<>(test);
        copyT.addFirst(100);
        copyT.printDeque();
        System.out.println("------------");
        test.printDeque();

    }
}


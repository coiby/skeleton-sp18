public class LinkedListDequeTwoSentinels<T> {

    private class ListNode {
        T item;
        ListNode next;
        ListNode prev;
        ListNode(T e, ListNode n, ListNode p) {
            item = e;
            next = n;
            prev = p;
        }
    }

    private ListNode frtSentinel;
    private ListNode backSentinel;
    private int size;

    /**
     * TODO initial value for sentinel should be provided, this seems to be ugly
     *
     * @param e
     */
    public LinkedListDequeTwoSentinels() {
        frtSentinel = new ListNode(null, null, null);
        backSentinel = new ListNode(null, null, frtSentinel);
        frtSentinel.next = backSentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode ln = frtSentinel.next;
        while (ln.item != null) {
            System.out.print(ln.item);
            System.out.print(" ");
            ln = ln.next;

        }
    }


    private T getFromFront(int index) {
        ListNode ln = frtSentinel;
        int i = 0;
        while (ln.next != null) {
            ln = ln.next;
            if (i == index) {
                break;
            }
        }

        return ln.item;
    }

    private T getFromBack(int index) {
        ListNode ln = backSentinel;
        int i = 0;
        while (ln.prev != null) {
            ln = ln.prev;
            if (i == index) {
                break;
            }
        }

        return ln.item;
    }


    /**
     * if index < size/2, search from the front of list
     * else search from the back of list
     * TODO error for index out of size
     * @param index
     * @return
     */
    public T get(int index) {

        if (index < size / 2) {
            return getFromFront(index);
        }

        return getFromBack(size - 1 - index);

    }

    public void addFirst(T item) {
        ListNode newItem = new ListNode(item, frtSentinel.next, frtSentinel);
        frtSentinel.next.prev = newItem;
        frtSentinel.next = newItem;
        size = size + 1;
    }

    public T removeFirst() {
        if (frtSentinel.next == null) {
            return null;
        }
        size = size - 1;
        ListNode nextNext = frtSentinel.next.next;
        nextNext.prev = frtSentinel;
        frtSentinel.next = nextNext;
        return nextNext.item;

    }

    public void addLast(T item) {
        ListNode bp = backSentinel.prev;
        ListNode newItem = new ListNode(item, backSentinel, bp);
        bp.next = newItem;
        backSentinel.prev =  newItem;
        size = size + 1;
    }

    public T removeLast() {
        if (backSentinel.prev == null) {
            return null;
        }
        size = size - 1;
        ListNode prevPrev = backSentinel.prev.prev;
        prevPrev.next = backSentinel;
        backSentinel.prev = prevPrev;
        return prevPrev.item;
    }

    public boolean isEmpty() {
        return (frtSentinel.next.item == null); // TODO and backSentinel == null?
    }
}

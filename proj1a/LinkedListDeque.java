public class LinkedListDeque<T> {

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

    private ListNode sentinel;

    private int size;

    /**
     *  null value for item of sentinel
     *
     * @param e
     */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode ln = sentinel.next;
        while (ln.item != null) {
            System.out.print(ln.item);
            System.out.print(" ");
            ln = ln.next;

        }
    }


    private T getFromFront(int index) {
        ListNode ln = sentinel;
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
        ListNode ln = sentinel;
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
     * TODO handle error for index out of size
     * @param index
     * @return
     */
    public T get(int index) {

        if (index < size / 2) {
            return getFromFront(index);
        }

        return getFromBack(size - 1 - index);

    }

    private T getRecursiveHelper(int index, ListNode node) {
        if (index == 0 && node.next != null) {
            return node.next.item;
        } else if (index > 0 && node.next == null) {
            return null;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel);
    }

    public void addFirst(T item) {
        ListNode newItem = new ListNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newItem;

        sentinel.next = newItem;
        size = size + 1;
    }

    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        size = size - 1;
        ListNode first = sentinel.next;
        ListNode nextNext = sentinel.next.next;
        nextNext.prev = sentinel;
        sentinel.next = nextNext;
        return first.item;

    }

    public void addLast(T item) {
        ListNode bp = sentinel.prev;
        ListNode newItem = new ListNode(item, sentinel, bp);
        bp.next = newItem;
        sentinel.prev =  newItem;
        size = size + 1;
    }

    public T removeLast() {
        if (sentinel.next.item == null) {
            return null;
        }
        size = size - 1;
        ListNode prevPrev = sentinel.prev.prev;
        ListNode last = sentinel.prev;
        prevPrev.next = sentinel;
        sentinel.prev = prevPrev;
        return last.item;
    }

    public boolean isEmpty() {
        return (sentinel.next.item == null);
    }
}

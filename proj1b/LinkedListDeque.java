import java.util.Iterator;

public class LinkedListDeque<Item> implements Deque<Item>, Iterable<Item>  {

    private final ListNode sentinel;

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

    @Override
    public Iterator<Item> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Item> {

        private ListNode curr = sentinel.next;
        @Override
        public boolean hasNext() {
            return curr.item != null;
        }

        @Override
        public Item next() {
            if (curr.item != null) {
                Item item = curr.item;
                curr = curr.next;
                return item;
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private class ListNode {
        Item item;
        ListNode next;
        ListNode prev;
        ListNode(Item e, ListNode n, ListNode p) {
            item = e;
            next = n;
            prev = p;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ListNode ln = sentinel.next;
        while (ln.item != null) {
            System.out.print(ln.item);
            System.out.print(" ");
            ln = ln.next;

        }
    }


    private Item getFromFront(int index) {
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

    private Item getFromBack(int index) {
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
    @Override
    public Item get(int index) {

        if (index < size / 2) {
            return getFromFront(index);
        }

        return getFromBack(size - 1 - index);

    }

    @Override
    public void addFirst(Item item) {
        if (item == null) {
            throw  new java.lang.IllegalArgumentException();
        }
        ListNode newItem = new ListNode(item, sentinel.next, sentinel);
        sentinel.next.prev = newItem;
        sentinel.next = newItem;
        size = size + 1;
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
            //return null;
        }
        /*if (sentinel.next.item == null) {
            return null;
        }*/
        size = size - 1;
        Item item = sentinel.next.item;
        ListNode nextNext = sentinel.next.next;
        nextNext.prev = sentinel;
        sentinel.next = nextNext;
        return item;

    }

    @Override
    public void addLast(Item item) {
        if (item == null) {
            throw  new java.lang.IllegalArgumentException();
        }
        ListNode bp = sentinel.prev;
        ListNode newItem = new ListNode(item, sentinel, bp);
        bp.next = newItem;
        sentinel.prev =  newItem;
        size = size + 1;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
            //return null;
        }
        /*if (sentinel.next.item == null) {
            return null;
        }*/
        size = size - 1;
        Item item = sentinel.prev.item;
        ListNode prevPrev = sentinel.prev.prev;
        prevPrev.next = sentinel;
        sentinel.prev = prevPrev;
        return item;
    }

    public boolean isEmpty() {
        return (sentinel.next.item == null);
    }
}

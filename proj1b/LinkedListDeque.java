public class LinkedListDeque<T> implements Deque<T> {

    private class ListNode {
        T item;
        ListNode next;
        ListNode prev;
        public ListNode(T e, ListNode n, ListNode p) {
            item = e;
            next = n;
            prev = p;
        }
    }

    private ListNode  sentinel;

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


    private T getFromFront(int index) {
        ListNode ln = sentinel;
        int i = 0;
        while (ln.next != null) {
            ln = ln.next;
            if (i == index){
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
            if (i == index){
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
    public T get(int index) {

        if (index < size/2){
            return getFromFront(index);
        }

        return getFromBack(size - 1 - index);

    }

    @Override
    public void addFirst(T item) {
        ListNode new_item = new ListNode(item, sentinel.next, sentinel);
        sentinel.next.prev = new_item;
        sentinel.next = new_item;
        size = size + 1;
    }

    @Override
    public T removeFirst(){
        if (sentinel.next.item == null) {
            return null;
        }
        size = size - 1;
        ListNode next_next = sentinel.next.next;
        next_next.prev = sentinel;
        sentinel.next = next_next;
        return next_next.item;

    }

    @Override
    public void addLast(T item) {
        ListNode bp = sentinel.prev;
        ListNode new_item = new ListNode(item, sentinel, bp);
        bp.next = new_item;
        sentinel.prev =  new_item;
        size = size + 1;
    }

    @Override
    public T removeLast() {
        if (sentinel.next.item == null) {
            return null;
        }
        size = size - 1;
        ListNode prev_prev = sentinel.prev.prev;
        prev_prev.next = sentinel;
        sentinel.prev = prev_prev;
        return prev_prev.item;
    }

    public boolean isEmpty() {
        return (sentinel.next.item == null);
    }
}

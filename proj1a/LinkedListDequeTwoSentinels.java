public class LinkedListDequeTwoSentinels<T> {

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

    private ListNode frt_sentinel;
    private ListNode back_sentinel;
    private int size;

    /**
     * TODO initial value for sentinel should be provided, this seems to be ugly because user should there is a setinel
     *
     * @param e
     */
    public LinkedListDequeTwoSentinels() {
        frt_sentinel = new ListNode(null, null, null);
        back_sentinel = new ListNode(null, null, frt_sentinel);
        frt_sentinel.next = back_sentinel;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode ln = frt_sentinel.next;
        while (ln.item != null) {
            System.out.print(ln.item);
            System.out.print(" ");
            ln = ln.next;

        }
    }


    private T getFromFront(int index) {
        ListNode ln = frt_sentinel;
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
        ListNode ln = back_sentinel;
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
     * TODO error for index out of size
     * @param index
     * @return
     */
    public T get(int index) {

        if (index < size/2){
            return getFromFront(index);
        }

        return getFromBack(size - 1 - index);

    }

    public void addFirst(T item) {
        ListNode new_item = new ListNode(item, frt_sentinel.next, frt_sentinel);
        frt_sentinel.next.prev = new_item;
        frt_sentinel.next = new_item;
        size = size + 1;
    }

    public T removeFirst(){
        if (frt_sentinel.next == null) {
            return null;
        }
        size = size - 1;
        ListNode next_next = frt_sentinel.next.next;
        next_next.prev = frt_sentinel;
        frt_sentinel.next = next_next;
        return next_next.item;

    }

    public void addLast(T item) {
        ListNode bp = back_sentinel.prev;
        ListNode new_item = new ListNode(item, back_sentinel, bp);
        bp.next = new_item;
        back_sentinel.prev =  new_item;
        size = size + 1;
    }

    public T removeLast() {
        if (back_sentinel.prev == null) {
            return null;
        }
        size = size - 1;
        ListNode prev_prev = back_sentinel.prev.prev;
        prev_prev.next = back_sentinel;
        back_sentinel.prev = prev_prev;
        return prev_prev.item;
    }

    public boolean isEmpty() {
        return (frt_sentinel.next.item == null);//TODO and back_sentinel == null?
    }
}

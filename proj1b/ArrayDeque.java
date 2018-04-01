public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private T[] items;

    private static final int FACTOR = 2;
    private static double USAGE_FACTOR = 0.25;
    private int nextFirst; // circular array
    private int nextLast;
    private int initalFirst; //mark the initial position of nextFirst

    public ArrayDeque() {
        size = 0;
        int capacity = 8;
        items = (T[]) new Object[capacity];
        nextLast = capacity / 2;
        nextFirst = nextLast - 1;
        initalFirst = nextFirst;
    }

    private int mod(int i) {
        if (i < 0) {
            return i + items.length;
        } else {
            return i % items.length;
        }
    }

    /**
     * won't resize a deque whose size = 0
     * @param capacity
     */
    private void resize(int capacity) {
        int nowFirst;
        int nowLast;
        T[] new_items = (T[]) new Object[capacity];
        int new_first = capacity / 4;
        nowFirst = mod(nextFirst + 1);//the index of the first item
        nowLast = mod(nextLast - 1);//the index of the last item
        initalFirst = new_first + initalFirst - nowFirst;
        //nowLast doesn't go to the beginning of the deque and nowFirst doesn't go to the end of the deque
        if (nowLast > nowFirst) {
            System.arraycopy(items, nowFirst, new_items, new_first, size);
        } else {
            int t_size = size - nowFirst;
            System.arraycopy(items, nowFirst, new_items, new_first, t_size); //copy from nowFirst to the end
            System.arraycopy(items, 0, new_items, new_first + t_size, nowFirst);
            if (nextFirst > initalFirst) {
                initalFirst = initalFirst + size;
            }
        }

        items = new_items;
        nextFirst = new_first - 1;
        nextLast = new_first + size;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(FACTOR * size);
        }

        items[nextFirst] = x;
        size = size + 1;
        nextFirst = mod(nextFirst - 1);
    }

    private void check_usage () {
        if (items.length > 8 && 1.0 * size / items.length < USAGE_FACTOR) { //only shrink when items.length > 8
            resize(items.length / 2);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0){
            return null;
        }

        nextFirst = mod(nextFirst + 1);
        initalFirst = nextFirst;
        T r = items[nextFirst];
        size = size - 1;
        check_usage();
        return r;

    }

    @Override
    public void addLast(T x) {
        if (size == items.length){
            resize(FACTOR * size);
        }

        items[nextLast] = x;
        size = size + 1;
        nextLast = mod(nextLast + 1);

    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = mod(nextLast - 1);
        size = size - 1;
        check_usage();
        return items[nextLast];

    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        return items[mod(nextFirst + index + 1)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i));
            System.out.print(" ");
        }
    }
}

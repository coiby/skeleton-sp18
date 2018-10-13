import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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
        T[] newItems = (T[]) new Object[capacity];
        int newFirst = capacity / 4;
        nowFirst = mod(nextFirst + 1); //the index of the first item
        nowLast = mod(nextLast - 1); //the index of the last item
        initalFirst = newFirst + initalFirst - nowFirst;
        // nowLast doesn't go to the beginning of the deque and nowFirst
        // doesn't go to the end of the deque
        if (nowLast > nowFirst) {
            System.arraycopy(items, nowFirst, newItems, newFirst, size);
        } else {
            int tSize = size - nowFirst;
            // copy from nowFirst to the end
            System.arraycopy(items, nowFirst, newItems, newFirst, tSize);
            System.arraycopy(items, 0, newItems, newFirst + tSize, nowFirst);
            if (nextFirst > initalFirst) {
                initalFirst = initalFirst + size;
            }
        }

        items = newItems;
        nextFirst = newFirst - 1;
        nextLast = newFirst + size;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void addFirst(T x) {
        if (x == null) {
            throw  new java.lang.IllegalArgumentException();
        }
        if (size == items.length) {
            resize(FACTOR * size);
        }

        items[nextFirst] = x;
        size = size + 1;
        nextFirst = mod(nextFirst - 1);
    }

    private void checkUsage() {
        //only shrink when items.length > 8
        if (items.length > 8 && 1.0 * size / items.length < USAGE_FACTOR) {
            resize(items.length / 2);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        nextFirst = mod(nextFirst + 1);
        initalFirst = nextFirst;
        T r = items[nextFirst];
        size = size - 1;
        checkUsage();
        return r;

    }

    @Override
    public void addLast(T x) {
        if (x == null) {
            throw  new java.lang.IllegalArgumentException();
        }
        if (size == items.length) {
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
        T last = items[nextLast];
        checkUsage();
        return last;

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

    private class ArrayDequeIterator implements Iterator<T> {
        private int s = size;
        @Override
        public boolean hasNext() {
            return s > 0;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return get(size - (s--));
            } else {
                throw new java.util.NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}

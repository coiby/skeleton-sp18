import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void addRemoveTest() {
        ArrayDeque<Integer> aq = new ArrayDeque<Integer>();
        //aq.add(2);
        assertEquals(aq.size(), 0);
        aq.addFirst(2);
        assertEquals(aq.size(), 1);
        aq.removeLast();
        assertEquals(aq.size(), 0);
        assertEquals(aq.get(0), null);
        //aq.remove();
        //aq.remove();
    }


    @Test
    /**
     * test resize
     * capacity will be halved
     */
    public void testUsage() {
        ArrayDeque<String> aq = new ArrayDeque<String>();
        assertEquals(aq.get(0), null);
        aq.addFirst("a");
        aq.addFirst("b");
        aq.addFirst("c");
        aq.addFirst("d");
        aq.addFirst("e");
        aq.addFirst("f");
        aq.addFirst("g");
        aq.addFirst("h");
        aq.addFirst("i");
        aq.removeLast();
        aq.removeLast();
        aq.removeLast();
        aq.removeLast();
        aq.removeFirst();
        aq.removeLast();
        assertEquals(aq.size(), 3);
        assertEquals(aq.get(0), "h");
    }

    @Test
    /**
     * keep adding items to the first, until it goes to the end of the deque
     */
    public void testGet() {
        ArrayDeque<String> aq = new ArrayDeque<String>();
        assertEquals(aq.get(0), null);
        aq.addFirst("a");
        aq.addFirst("b");
        aq.addFirst("c");
        aq.addFirst("d");
        aq.addFirst("e");
        aq.addFirst("f");
        aq.addFirst("g");
        aq.addFirst("h");
        aq.addFirst("i");


        assertEquals(aq.get(2), "g");
        assertEquals(aq.removeFirst(), "i");
        assertEquals(aq.removeLast(), "a");
        assertEquals(aq.get(0), "h");
        assertEquals(aq.size(), 7);

    }

    @Test
    /**
     * keep adding items to the last, until it goes to the beginning of the deque
     */
    public void addFirstTest() {
        ArrayDeque<String> aq = new ArrayDeque<String>();
        aq.addLast("a");
        aq.addLast("b");
        aq.addFirst("c");
        aq.addLast("d");
        aq.addLast("e");
        aq.addFirst("f");
        aq.addLast("g");
        aq.addLast("h");
        aq.addLast("i");
        assertEquals(aq.get(0), "f");
        //System.out.println(aq.size());
        assertEquals(aq.size(), 9);
        aq.addLast("aa");
        aq.addLast("bb");
        aq.addFirst("cc");
        aq.addLast("dd");
        aq.addLast("ee");
        aq.addFirst("ff");
        aq.addLast("gg");
        aq.addLast("hh");
        aq.addLast("ii");
        aq.addLast("j");
    }


    public void testPrint() {
        ArrayDeque<String> aq = new ArrayDeque<String>();
        aq.addLast("a");
        aq.addFirst("b");
        aq.addFirst("c");
        aq.addFirst("d");
        aq.addFirst("e");
        aq.addFirst("f");
        aq.addFirst("g");
        aq.addFirst("h");
        aq.addFirst("i");
        aq.printDeque();
    }

   /* public static void main(String[] args) {
        System.out.println("Running tests.\n");

        //addRemoveTest();
        addFirstTest2();
        //resizeTest();
        //testGet();
        //testPrint();

    }*/
}

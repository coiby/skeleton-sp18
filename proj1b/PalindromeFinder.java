/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        int N = 0;
        if (args.length != 0) {
            N = Integer.parseInt(args[0]);
        }
        CharacterComparator cc = new OffByN(N);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, cc)) {
                System.out.println(word);
            }
        }
    } /* Uncomment this class once you've written isPalindrome. */
}

public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> deque = new ArrayDeque<Character>();

        for (int i = 0; i < word.length(); i += 1){
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < deque.size() / 2; i +=1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    // TODO this seems to be redundant toe the previous methods
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() < 2) {
            return true;
        }

        Deque<Character> deque = wordToDeque(word);
        for (int i = 0; i < deque.size() / 2; i +=1) {
            if (! cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }

}

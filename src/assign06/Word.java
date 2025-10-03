package assign06;

/**
 * Represents a word composed of letters, providing various operations using recursion.
 * 
 * @author Vesper
 * @version 2025-10-02
 */
public class Word {
    private char[] letters;

    /**
     * Constructs a Word from the given String.
     * 
     * @param word the input string
     * @throws IllegalArgumentException if any character is not a letter
     */
    public Word(String word) {
        if (word == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        for (int i = 0; i < word.length(); i++) {
            if (!isLetter(word.charAt(i))) {
                throw new IllegalArgumentException("Invalid character at index " + i);
            }
        }
        letters = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            letters[i] = word.charAt(i);
        }
    }

    /**
     * Constructs a Word from the given char array.
     * 
     * @param word the input char array
     * @throws IllegalArgumentException if any character is not a letter
     */
    public Word(char[] word) {
        if (word == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }
        for (int i = 0; i < word.length; i++) {
            if (!isLetter(word[i])) {
                throw new IllegalArgumentException("Invalid character at index " + i);
            }
        }
        letters = new char[word.length];
        for (int i = 0; i < word.length; i++) {
            letters[i] = word[i];
        }
    }

    /**
     * Generates and returns a String object to represent this Word object.
     * 
     * @return a String representation of this Word
     */
    public String toString() {
        return toString(0);
    }

    /**
     * Recursive helper method to generate a String from a given index.
     * 
     * @param startIndex the index to start from
     * @return the String representation from startIndex
     */
    private String toString(int startIndex) {
    	
        if (startIndex == letters.length)
            return "";
        
        return letters[startIndex] + toString(startIndex + 1);
    }
    
    /**
     * Use this method to test oriented variable is letter or not
     * 
     * @param the char to test out
     * @return true if the char is a letter
     * */
    private boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    /**
     * Counts the occurrences of a specified character in this Word.
     * 
     * @param letter the character to count
     * @return the number of occurrences
     * @throws IllegalArgumentException if the character is not a letter
     */
    public int countOccurrences(char letter) {
        if (!isLetter(letter)) {
            throw new IllegalArgumentException("Character must be a letter.");
        }
        return countOccurrences(letter, 0);
    }

    /**
     * Recursive helper method to count occurrences starting from a given index.
     * 
     * @param letter   the character to count
     * @param index    the index to start counting from
     * @return the number of occurrences from the index
     */
    private int countOccurrences(char letter, int index) {
        if (index >= letters.length) {
            return 0;
        }
        int current = (letters[index] == letter) ? 1 : 0;
        return current + countOccurrences(letter, index + 1);
    }

    /**
     * Replaces the last occurrence of the specified character with the replacement.
     * 
     * @param letter       the character to replace
     * @param replacement  the replacement character
     * @throws IllegalArgumentException if either character is not a letter
     */
    public void replaceLastOccurrence(char letter, char replacement) {
        if (!isLetter(letter) || !isLetter(replacement)) {
            throw new IllegalArgumentException("Both characters must be letters.");
        }
        replaceLastOccurrence(letter, replacement, 0);
    }

    /**
     * Recursive helper method to replace the last occurrence starting from a given index.
     * 
     * @param letter       the character to replace
     * @param replacement  the replacement character
     * @param index        the index to start from
     */
    
    /*
    private void replaceLastOccurrence(char letter, char replacement, int index) {
        if (index >= letters.length) {
            return;
        }
        replaceLastOccurrence(letter, replacement, index + 1);
        
        if (letters[index] == letter) {
            letters[index] = replacement;
        }
    }
    */
    private boolean replaceLastOccurrence(char letter, char replacement, int index) {
        // Base case: 超出数组边界，说明没找到（或还没找到）
        if (index >= letters.length) {
            return false;
        }

        // 先递归处理后面的字符（向右走到底）
        boolean alreadyReplaced = replaceLastOccurrence(letter, replacement, index + 1);

        // 如果后面的递归已经完成了替换，就不要再动当前字符
        if (alreadyReplaced) {
            return true;
        }

        // 如果还没替换，且当前字符匹配目标字母，则替换
        if (letters[index] == letter) {
            letters[index] = replacement;
            return true; // 标记：已替换
        }

        // 当前不匹配，也没在后面替换，继续向上返回 false
        return false;
    }

    /**
     * Creates a new Word object with characters in reverse order.
     * 
     * @return the reversed Word
     */
    public Word reverse() {
        char[] reversed = new char[letters.length];
        reverse(reversed, 0);
        return new Word(reversed);
    }

    /**
     * Recursive helper method to fill the reversed array starting from a given index.
     * 
     * @param reversedWord the array to store the reversed characters
     * @param index        the index to start from
     */
    private void reverse(char[] reversedWord, int index) {
        if (index >= letters.length) {
            return;
        }
        reversedWord[reversedWord.length - 1 - index] = letters[index];
        reverse(reversedWord, index + 1);
    }
}

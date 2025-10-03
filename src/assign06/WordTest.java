package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Word class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class WordTest {
	// Provided
	@Test
	public void testFirstConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> { new Word("hel!o"); });
	}
	
	// Provided
	@Test
	public void testSecondConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> 
			{ new Word(new char[] { 'W', 'h', 'o', '?' }); });
	}
	
	// Provided
	@Test
	public void testToStringNormal() {
		Word normal = new Word("Normal");
		assertEquals("Normal", normal.toString());
	}
	
	//Neew
	@Test
	public void testToStringEmpty() {
		Word empty = new Word("");
		assertEquals("", empty.toString());
	}
	
	
	// Provided
	@Test
	public void testCountOccurrencesOneLetter() {		
		Word oneLetter = new Word("a");
		assertEquals(1, oneLetter.countOccurrences('a'));
	}
	
	// Provided
	@Test
	public void testIsCountOccurrencesMultiple() {
		Word multiplePs = new Word("saippuakivikauppias");
		assertEquals(4, multiplePs.countOccurrences('p'));
	}
	
	
	
	//New
	@Test
	public void testNoOccurenceA() {
		Word empty = new Word("abcd");
		assertEquals(0, empty.countOccurrences('e'));
	}
	//New
	@Test
	public void testNoOccurenceB() {
		Word empty = new Word("");
		assertEquals(0, empty.countOccurrences('a'));
	}
	//New
	@Test
	public void testCountOccurrencesDoesNotChangeObject() {
	    Word word = new Word("Hello");
	    String original = word.toString(); // "Hello"
	    
	    // Assert the object is unchanged
	    assertEquals("Hello", word.toString());
	    assertEquals(2, word.countOccurrences('l')); // also verify correct result
	}
	
	
	// Provided
	@Test
	public void testReplaceLastOccurrenceExceptionFirstArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence(' ', 'l'); });
	}
	
	// Provided
	@Test
	public void testReplaceLastOccurrenceHello() {
		Word hello = new Word("hello");
		hello.replaceLastOccurrence('l', 's');
		assertEquals("helso", hello.toString());
	}
	
	
	// New
	@Test
	public void testReplaceLastOccurrenceLetterNotPresent() {
	    Word word = new Word("apple");
	    word.replaceLastOccurrence('z', 'x'); 
	    assertEquals("apple", word.toString()); // should remain unchanged
	}

	// New
	@Test
	public void testReplaceLastOccurrenceReplacementNotALetter() {
	    Word word = new Word("test");
	    assertThrows(IllegalArgumentException.class, () -> {
	        word.replaceLastOccurrence('t', '1'); // '1' is not a letter
	    });
	}

	// New
	@Test
	public void testReplaceLastOccurrenceOnlyOneOccurrence() {
	    Word word = new Word("banana");
	    word.replaceLastOccurrence('b', 'B'); // only one 'b' at start
	    assertEquals("Banana", word.toString());
	}

	// Provided
	@Test
	public void testReverseHello() {
		Word hello = new Word("hello");
		assertEquals("olleh", hello.reverse().toString());
	}
	
	// Provided
	@Test
	public void testReverseEmpty() {
		Word empty = new Word("");
		assertEquals("", empty.reverse().toString());
	}
	// New
	@Test
	public void testReverseSingleCharacter() {
	    Word word = new Word("A");
	    assertEquals("A", word.reverse().toString());
	}

	// New
	@Test
	public void testReversePalindrome() {
	    Word word = new Word("level");
	    assertEquals("level", word.reverse().toString()); // palindrome stays same
	}

	// New
	@Test
	public void testReverseMixedCase() {
	    Word word = new Word("HeLLo");
	    assertEquals("oLLeH", word.reverse().toString());
	}
}

package assign09;

/**
 *
 * @author CS 1420 course staff and Vesper
 * @version 2025-11-06
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class SongTester {

	private Song song;

	@BeforeEach
	public void setup() {
		song = new Song(120, 1000); 
	}

	@Test
	public void testAddNoteEvent() {
		song.addNoteEvent(100, 0, 200, 60);
		ArrayList<AudioEvent> track0 = song.getTrack(0);
		assertEquals(1, track0.size(), "Track should have 1 event");
		assertTrue(track0.get(0) instanceof NoteEvent, "Event should be NoteEvent");
		NoteEvent ne = (NoteEvent) track0.get(0);
		assertEquals(100, ne.getTime());
		assertEquals(0, ne.getTrackNumber());
		assertEquals(200, ne.getDuration());
		assertEquals(60, ne.getPitch());
	}

	@Test
	public void testAddVolumeEvent() {
		song.addVolumeEvent(50, 1, 80);
		ArrayList<AudioEvent> track1 = song.getTrack(1);
		assertEquals(1, track1.size(), "Track should have 1 event");
		assertTrue(track1.get(0) instanceof VolumeEvent, "Event should be VolumeEvent");
		VolumeEvent ve = (VolumeEvent) track1.get(0);
		assertEquals(50, ve.getTime());
		assertEquals(1, ve.getTrackNumber());
		assertEquals(80, ve.getValue());
	}

	@Test
	public void testSorting() {
		song.addNoteEvent(200, 0, 100, 60);
		song.addNoteEvent(100, 0, 100, 62);
		ArrayList<AudioEvent> track0 = song.getTrack(0);
		// Should be sorted by time: 100 then 200
		assertEquals(100, track0.get(0).getTime());
		assertEquals(200, track0.get(1).getTime());
	}

	@Test
	public void testClearTrack() {
		song.addNoteEvent(100, 2, 100, 60);
		song.clearTrack(2);
		assertEquals(0, song.getTrack(2).size(), "Track should be empty after clear");
	}

	@Test
	public void testClearAll() {
		song.addNoteEvent(100, 0, 100, 60);
		song.addVolumeEvent(200, 1, 80);
		song.clearAll();
		for (int i = 0; i < 10; i++) {
			assertEquals(0, song.getTrack(i).size(), "All tracks should be empty");
		}
	}

	@Test
	public void testGetTrackBounds() {
		assertThrows(IllegalArgumentException.class, () -> song.getTrack(-1));
		assertThrows(IllegalArgumentException.class, () -> song.getTrack(10));
	}

	@Test
	public void testAddEventBounds() {
		assertThrows(IllegalArgumentException.class, () -> song.addNoteEvent(0, 10, 100, 60));
		assertThrows(IllegalArgumentException.class, () -> song.addVolumeEvent(0, -1, 100));
	}

	@Test
	public void testClearTrackBounds() {
		assertThrows(IllegalArgumentException.class, () -> song.clearTrack(10));
	}
}
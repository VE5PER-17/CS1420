package assign08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AudioEventTester {

    @Test
    void testNoteEventConstructorValid() {
        NoteEvent ne = new NoteEvent(100, 3, 500, 60);
        System.out.println(ne.getTrackNumber());
        System.out.println(ne.getDuration()); 
        System.out.println(ne.getPitch());
        assertEquals(100, ne.getTime());
        assertEquals(3, ne.getTrackNumber());
        assertEquals(500, ne.getDuration());
        assertEquals(60, ne.getPitch());
        
    }

    @Test
    void testNoteEventConstructorInvalidDuration() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 3, -10, 60);
        });
    }

    @Test
    void testNoteEventConstructorInvalidPitch() {
        assertThrows(IllegalArgumentException.class, () -> {
            new NoteEvent(100, 3, 100, 128);
        });
    }

    @Test
    void testVolumeEventConstructorValid() {
        VolumeEvent ve = new VolumeEvent(200, 5, 80);
        assertEquals(200, ve.getTime());
        assertEquals(5, ve.getTrackNumber());
        assertEquals(80, ve.getValue());
    }

    @Test
    void testVolumeEventConstructorInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new VolumeEvent(200, 5, 128);
        });
    }

    @Test
    void testNoteEventToString() {
        NoteEvent ne = new NoteEvent(7142, 3, 1000, 60);
        assertEquals("Note[7142, 3, 1000, 60]", ne.toString());
    }

    @Test
    void testVolumeEventToString() {
        VolumeEvent ve = new VolumeEvent(7142, 5, 72);
        System.out.println(ve.toString());
        assertEquals("Volume[7142, 5, 72]", ve.toString());
    }

    @Test
    void testCompareTo_NoteVsVolume() {
        NoteEvent ne = new NoteEvent(100, 0, 100, 60);
        VolumeEvent ve = new VolumeEvent(100, 0, 80);
        assertTrue(ne.compareTo(ve) < 0); // Note < Volume
        assertTrue(ve.compareTo(ne) > 0); // Volume > Note
    }

    @Test
    void testCompareTo_NoteVsNote_HigherPitchFirst() {
        NoteEvent high = new NoteEvent(100, 0, 100, 70);
        NoteEvent low = new NoteEvent(100, 0, 100, 60);
        assertTrue(high.compareTo(low) < 0); // 70 comes before 60
        assertTrue(low.compareTo(high) > 0);
    }

    @Test
    void testCompareTo_VolumeVsVolume_ByTime() {
        VolumeEvent early = new VolumeEvent(100, 0, 80);
        VolumeEvent late = new VolumeEvent(200, 0, 80);
        assertTrue(early.compareTo(late) < 0); // earlier time first
        assertTrue(late.compareTo(early) > 0);
    }

    @Test
    void testCompareTo_SameNote() {
        NoteEvent n1 = new NoteEvent(100, 0, 100, 60);
        NoteEvent n2 = new NoteEvent(100, 0, 100, 60);
        assertEquals(0, n1.compareTo(n2));
    }

    @Test
    void testCompareTo_SameVolume() {
        VolumeEvent v1 = new VolumeEvent(100, 0, 80);
        VolumeEvent v2 = new VolumeEvent(100, 0, 80);
        assertEquals(0, v1.compareTo(v2));
    }

    // Optional: test execute prints (not required for JUnit, but good for debugging)
    @Test
    void testExecutePrints() {
        SimpleSynthesizer synth = new SimpleSynthesizer();
        NoteEvent ne = new NoteEvent(0, 1, 100, 60);
        VolumeEvent ve = new VolumeEvent(0, 1, 90);
        ne.execute(synth);   // Should print "Note on: track 1, pitch 60"
        ve.execute(synth);   // Should print "Set volume: track 1, volume 90"
        // We can't assert printed output easily in JUnit without capturing System.out
    }
}
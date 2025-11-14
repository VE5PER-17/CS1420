package assign08;
/**
 *CS1420
 *
 * @author Vesper
 * @version 2025-10-30
 */

public abstract class AudioEvent implements Comparable<AudioEvent>{
	private int time;
	private int trackNumber;
	
	/**
	 * Construct an AudioEvent with given time and track number
	 * @param time > 0
	 * @param trackNumber -> [0,9]
	 * @throws erro if the given variables were invalid
	 */
	public AudioEvent(int time, int trackNumber) {
		if(time < 0 || trackNumber < 0 || trackNumber > 9)
			throw new IllegalArgumentException("Invalid time or trackNumber.");
		this.time = time;
		this.trackNumber = trackNumber;
	}
	
	/**
	 * Return the time of this event
	 */
	public int getTime() {
		return this.time;
	}
	
	/**
	 * Return the track number of this event
	 * Must be implemented in subclass
	 */
	public int getTrackNumber() {
		return this.trackNumber;
	}
	
	/**
	 * Execute the event using the given synthesizer
	 * @param synth the synthesizer to use
	 */
	public abstract void execute(SimpleSynthesizer syth);
	
	/**
	 * Compare this event with another
	 * Must be implemented in subclass
	 */
	@Override
	public abstract int compareTo(AudioEvent other);
}

package assign08;

public class VolumeEvent extends AudioEvent{
	
	private int value;
	
	/**
	 * Constructs a VolumeEvent
	 * @param time >= 0
	 * @param trackNumber 0-9
	 * @param value 0-127 just like volume
	 * @throws IllegalArgumentException if invalid
	 */
	public VolumeEvent(int time, int trackNumber, int value) {
		super(time, trackNumber);
		if(value < 0 || value >127)
			throw new IllegalArgumentException("Invalid volume value!");
		this.value = value;
	}
	
	/**
	 * Get the value of this event
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}
	
	@Override
	public void execute(SimpleSynthesizer synth) {
		synth.setVolume(getTrackNumber(), value);
	}
	
	/**
	 * Returns a string in a format like: Note[123, 1123, 1]
	 */
	@Override
	public String toString() {
		return String.format("Volume[%d, %d, %d]", getTime(), getTrackNumber(), value);
	}
	
	@Override
	public int compareTo(AudioEvent other) {
		if(other instanceof NoteEvent) {
			return 1;
		}else if(other instanceof VolumeEvent) {
			VolumeEvent otherVol = (VolumeEvent) other;
			return Integer.compare(this.getTime(), otherVol.getTime());
		}else {
			return 0;
		}
	}
	
	

}

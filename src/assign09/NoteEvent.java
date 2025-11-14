package assign09;

public class NoteEvent extends AudioEvent{
	
	//with AudioEvent's time and trackNumber
	private int duration;
	private int pitch;
	
	/**
	 * 
	 * COnstruct a new NoteEvent
	 * @param time >= 0
	 * @param trackNumber -> [0,9]
	 * @param durtation >= 0
	 * @param pitch 0-127
	 * @throws 
	 */
	public NoteEvent(int time, int trackNumber, int duration, int pitch) {
		super(time, trackNumber);
		if (duration < 0 || pitch < 0 || pitch > 127)
			throw new IllegalArgumentException("Invalid duration or pitch");
		this.duration = duration;
		this.pitch = pitch;
	}
	
	/**
	 * Get the duration of this event
	 * @return duration
	 */
	public int getDuration() {
		return this.duration;
	}
	
	/**
	 * Get the pitch of this event
	 * @return pitch
	 */
	public int getPitch() {
		return this.pitch;
	}
	
	@Override
	public void execute(SimpleSynthesizer synth) {
		synth.noteOn(getTrackNumber(), pitch);
	}
	
	
	public void complete(SimpleSynthesizer synth) {
		synth.noteOff(getTrackNumber(), pitch);
	}
	/**
	 * Returns a string in a format like: Note[123, 1123, 1, 1332]
	 */
	@Override
	public String toString() {
		return String.format("Note[%d, %d, %d, %d]", getTime(), getTrackNumber(), getDuration(), getPitch());
	}
	
	
	
	@Override
	public int compareTo(AudioEvent other) {
		// NoteEvent comes before the VolumEvent
		if(other instanceof VolumeEvent) 
			return -1;	//this (note) < other (volume)
		
		// Both are NoteEvent, than compare by pitch
		if(other instanceof NoteEvent) {
			NoteEvent otherNote = (NoteEvent) other;
			return Integer.compare(otherNote.getPitch(), this.getPitch());
		}
		
		//should not happen, but still return something
		return 0;
		
	}
}

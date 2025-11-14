package assign10;
/**
 * CS1420
 * setup the GUI of the sound sketcher
 * @author Vesper
 * @version 2025-11-13
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class SoundSketcherFrame extends JFrame implements ActionListener, ChangeListener {
	private static final int DEFALUT_TEMPO = 300;
	private static final int DEFAULT_SONG_LENGTH = 16;
	
	private Song song;
	private TrackPanel[] trackPanels = new TrackPanel[10];
	
	private JToggleButton playButton;
	private JToggleButton loopButton;	
	private JSlider tempoSlider;
	private JLabel tempoLabel;
	private JSpinner lengthSpinner;
	private JLabel songLength;
	
	/**
	 * the constructor that generate the basic UI interface
	 */
	public SoundSketcherFrame() {
		song = new Song(DEFALUT_TEMPO, DEFAULT_SONG_LENGTH);
		setPreferredSize(new Dimension(800, 800));
		setTitle("Sound Sketcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		
		//setup
		setLayout(new BorderLayout());
		JPanel mainCanva = new JPanel();
		
		playButton = new JToggleButton("Play/Pause");
		loopButton = new JToggleButton("Loop");
		
		
		
		JPanel tempoUI = new JPanel();
		setLayout(new BorderLayout());
		tempoSlider = new JSlider(20, 600, DEFALUT_TEMPO);
		tempoLabel = new JLabel("Tempo");
		tempoUI.add(tempoSlider, BorderLayout.NORTH);	//Why this didn't work????
		tempoUI.add(tempoLabel, BorderLayout.SOUTH);
		
		JPanel lengthUI = new JPanel();
		lengthSpinner = new JSpinner(new SpinnerNumberModel(DEFAULT_SONG_LENGTH, 4, 1024, 4));
		songLength = new JLabel("Length");
		lengthUI.add(lengthSpinner);
		lengthUI.add(songLength);
		
		//adding items to the panel
		mainCanva.add(playButton);
		mainCanva.add(loopButton);
		mainCanva.add(lengthUI);
		mainCanva.add(tempoUI);
		
		/*
		mainCanva.add(tempoSlider);
		mainCanva.add(tempoLabel);
		mainCanva.add(lengthSpinner);
		mainCanva.add(songLength);
		*/
		
		//adding listener
		playButton.addActionListener(this);
		loopButton.addActionListener(this);
		tempoSlider.addChangeListener(this);
		lengthSpinner.addChangeListener(this);
		
		
		//put them at the bottom
		JTabbedPane tabbedPane = new JTabbedPane();
		
		for(int i = 0;i < 10; i++) {
			trackPanels[i] = new TrackPanel(i, song.getSongLength(), song.getTrack(i), song.getSynthesizer());
			tabbedPane.addTab("Track " + i, trackPanels[i]);
		}
		
		add(mainCanva, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Object source = e.getSource();

	    // make Play/Stop work and change the text
	    if (source == playButton) {
	        if (playButton.isSelected()) {
	            song.play();
	            playButton.setText("Stop"); 
	        } else {
	            song.stop();
	            playButton.setText("Play");
	        }
	    }

	    //loop
	    else if (source == loopButton) {
	        boolean isLooping = loopButton.isSelected();
	        song.enableLoop(isLooping);
	    }
	}
		
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

	    // tempoSlider
	    if (source == tempoSlider) {
	        int tempo = tempoSlider.getValue();
	        song.setTempo(tempo);
	    }

	    //songLength
	    else if (source == lengthSpinner) {
	    
	        int newLength = (Integer) lengthSpinner.getValue(); // need convert
	        song.setSongLength(newLength);

	        //update the length
	        for (TrackPanel panel : trackPanels) {
	            if (panel != null) {
	                panel.setSongLength(newLength);
	            }
	        }
	    }
	}
}

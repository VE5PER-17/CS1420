package assign10;
/**
 * CS1420
 * Create a basic track panel
 * @author vesper
 * @version 2025-11-13
 */
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackPanel extends JPanel implements ActionListener{
	
	private int trackNumber;
	private SimpleSynthesizer synth;
	
	private JToggleButton muteButton;
	private ButtonGroup modeButtons;
	private JToggleButton noteMode;
	private JToggleButton volMode;
	private JToggleButton copyNpasteMode;
	private JToggleButton clearButton;
	
	private JComboBox<String> instrComboBox;
	private TrackEditor trackEditor;
	
	
	/**
	 * The constructor that setup the basic layout of the TrackPanel
	 * @param trackNumber
	 * @param songLength
	 * @param events
	 * @param synth
	 */
	public TrackPanel(int trackNumber, int songLength, ArrayList<AudioEvent> events, SimpleSynthesizer synth) {
		
		this.trackNumber = trackNumber;
		this.synth = synth;
		
		setLayout(new BorderLayout());
		JPanel trackPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		this.trackEditor = new TrackEditor(trackNumber, songLength, events, synth);
		
		instrComboBox = new JComboBox<String>(new Vector<String>(synth.getInstrumentNames()));
		controlPanel.add(instrComboBox);
		
		muteButton = new JToggleButton("Mute");
		controlPanel.add(muteButton);
		
		modeButtons = new ButtonGroup();
		
		noteMode = new JToggleButton("Note");
		volMode = new JToggleButton("Volume");
		copyNpasteMode = new JToggleButton("Copy/Paste");
		
		modeButtons.add(noteMode);
		modeButtons.add(volMode);
		modeButtons.add(copyNpasteMode);
		
		controlPanel.add(noteMode);
		controlPanel.add(volMode);
		controlPanel.add(copyNpasteMode);
		
		clearButton = new JToggleButton("Clear");
		controlPanel.add(clearButton);
		
		instrComboBox.addActionListener(this);
	    muteButton.addActionListener(this);
	    noteMode.addActionListener(this);
	    volMode.addActionListener(this);
	    copyNpasteMode.addActionListener(this);
	    clearButton.addActionListener(this);
		
	    add(controlPanel, BorderLayout.NORTH);
	    add(trackEditor, BorderLayout.CENTER);
		
	}
	
	/**
	 * takes one int for the instrument and does not return anything
	 * @param int instrument
	 * @return no return
	 */
	public void setInstrument(int instr) {
		synth.setInstrument(trackNumber, instr);
		instrComboBox.setSelectedIndex(instr);
	}
	/**
	 * takes one int for the total song duration and does not return anything
	 * @param length
	 * @return no return
	 */
	public void setSongLength(int length) {
		this.setSongLength(length);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
	    if (source == muteButton) {
	        boolean isMuted = muteButton.isSelected();
	        synth.setMute(trackNumber, isMuted); // 假设 SimpleSynthesizer 有 setMute(boolean)
	    }

	    else if (source == instrComboBox) {
	        int selectedIndex = instrComboBox.getSelectedIndex();
	        synth.setInstrument(trackNumber,selectedIndex);
	    }

	    else if (source == noteMode) {
	        trackEditor.setMode(TrackEditor.Mode.NOTE);
	    }

	    else if (source == volMode) {
	        trackEditor.setMode(TrackEditor.Mode.VOLUME);
	    }
	    
	    else if (source == copyNpasteMode) {
	        trackEditor.setMode(TrackEditor.Mode.COPY);
	    }

	    // 6. Clear 按钮
	    else if (source == clearButton) {
	        trackEditor.clearTrack();
	    }
		
	}
	
}

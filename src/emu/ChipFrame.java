package emu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import chip.IChip;

public class ChipFrame extends JFrame implements KeyListener {
	
	private ChipPanel panel;
	private int[] keyBuffer;
	private int[] keyIdToKey;
	
	public ChipFrame(IChip chip) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Chip 8 Emulator");
		this.setPreferredSize(new Dimension(640, 320));
		this.pack();
		var insets = getInsets();
		
		var width = 640 + insets.left + insets.right;
		var height = 320 + insets.top + insets.bottom;
		
		var dimensions = new Dimension(width, height);
		
		this.setPreferredSize(dimensions);
		
		this.panel = new ChipPanel(chip);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
		
		this.addKeyListener(this);
		
		this.keyIdToKey = new int[256];
		this.keyBuffer = new int[16];
		this.fillKeyIds();
	}
	
	private void fillKeyIds() {
		for(int i = 0; i < keyIdToKey.length; i++) {
			this.keyIdToKey[i] = -1;
		}
		keyIdToKey['1'] = 1;
		keyIdToKey['2'] = 2;
		keyIdToKey['3'] = 3;		
		keyIdToKey['Q'] = 4;
		keyIdToKey['W'] = 5;
		keyIdToKey['E'] = 6;
		keyIdToKey['A'] = 7;
		keyIdToKey['S'] = 8;
		keyIdToKey['D'] = 9;
		keyIdToKey['Z'] = 0xA;
		keyIdToKey['X'] = 0;
		keyIdToKey['C'] = 0xB;
		keyIdToKey['4'] = 0xC;
		keyIdToKey['R'] = 0xD;
		keyIdToKey['F'] = 0xE;
		keyIdToKey['V'] = 0xF;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(this.keyIdToKey[keyCode] != -1) {
			keyBuffer[this.keyIdToKey[keyCode]] = 1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(this.keyIdToKey[keyCode] != -1) {
			keyBuffer[this.keyIdToKey[keyCode]] = 0;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	public int[] getKeyBuffer() {
		return this.keyBuffer;
	}
	
}

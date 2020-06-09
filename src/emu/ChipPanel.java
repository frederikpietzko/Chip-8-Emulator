package emu;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import chip.IChip;

public class ChipPanel extends JPanel {

	private IChip chip;
	
	public ChipPanel(IChip chip) {
		this.chip = chip;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		var dispaly = this.chip.getDisplay();
		for (int i = 0; i < dispaly.length; i++) {
			if(dispaly[i] == 0)
				g.setColor(Color.BLACK);
			else
				g.setColor(Color.WHITE);
			
			int x = (i % 64);
			int y = (int)Math.floor(i / 64);
			g.fillRect(x * 10, y * 10, 10, 10);
		}
	}
}

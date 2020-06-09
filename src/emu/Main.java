package emu;

import chip.Chip;
import chip.Chip2;
import chip.IChip;

public class Main extends Thread {

	private IChip chip;
	private ChipFrame frame;

	public Main() {
		this.chip = new Chip2();
		this.chip.init();
		this.chip.loadProgram("./pong2.c8");

		this.frame = new ChipFrame(this.chip);
	}

	@Override
	public void run() {
		// 60hz
		while (true) {
			this.chip.setKeyBuffer(this.frame.getKeyBuffer());
			this.chip.run();
			if (this.chip.needsRedraw()) {
				this.frame.repaint();
				chip.removeDrawFlag();
			}
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
			}
		}
	}

	public static void main(String[] args) {
		var main = new Main();
		main.start();
	}
}

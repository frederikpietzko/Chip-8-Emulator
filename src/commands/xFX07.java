package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX07 extends OpcodeCommand {

	public xFX07(Chip chip) {
		super(chip);
	}

	/**
	 * FX07: Sets VX to the value of the delay timer. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		var delayTimer = chip.getDelayTimer();
		chip.setElementOnV(x, (char) delayTimer); 
		chip.incrementPc();
	}

}

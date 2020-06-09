package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX15 extends OpcodeCommand {

	public xFX15(Chip chip) {
		super(chip);
	}

	/**
	 * FX15: Sets the delay timer to VX. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int VX = chip.getElementOnV(x);
		chip.setDelayTimer(VX);
		chip.incrementPc();
	}

}

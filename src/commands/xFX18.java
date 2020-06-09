package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class xFX18 extends OpcodeCommand {

	public xFX18(Chip2 chip) {
		super(chip);
	}

	/**
	 * FX18: Sets the sound timer to VX. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int VX = chip.getElementOnV(x);
		chip.setSoundTimer(VX);
		chip.incrementPc();
	}

}

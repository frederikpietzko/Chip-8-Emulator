package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX29 extends OpcodeCommand {

	public xFX29(Chip chip) {
		super(chip);
	}

	/**
	 * FX29: Sets I to the location of the sprite for the character in VX.
	 * Characters 0-F (in hexadecimal) are represented by a 4x5 font.
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int character = chip.getElementOnV(x);
		char I = (char) (0x050 + (character * 5));
		chip.setI(I);
		chip.incrementPc();
	}

}

package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class xFX33 extends OpcodeCommand {

	public xFX33(Chip2 chip) {
		super(chip);
	}

	/**
	 * FX33: Stores the binary-coded decimal representation of VX, with the most
	 * significant of three digits at the address in I, the middle digit at I plus
	 * 1, and the least significant digit at I plus 2. (In other words, take the
	 * decimal representation of VX, place the hundreds digit in memory at location
	 * in I, the tens digit at location I+1, and the ones digit at location I+2.)
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int value = chip.getElementOnV(x);
		int hundreds = (value - (value % 100)) / 100;
		value -= hundreds * 100;
		int tens = (value - (value % 10)) / 10;
		value -= tens * 10;
		int I = chip.getI();
		chip.setElementInMemory(I, (char) hundreds);
		chip.setElementInMemory(I + 1, (char) tens);
		chip.setElementInMemory(I + 2, (char) value);

		chip.incrementPc();
	}

}

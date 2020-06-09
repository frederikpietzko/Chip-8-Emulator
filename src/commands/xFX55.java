package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX55 extends OpcodeCommand {

	public xFX55(Chip chip) {
		super(chip);
	}

	/**
	 * FX55: Stores V0 to VX (including VX) in memory starting at address I. The
	 * offset from I is increased by 1 for each value written, but I itself is left
	 * unmodified.
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;

		int offset = 0;
		int I = chip.getI();
		for (int i = 0; i < x + 1; i++) {
			char VI = chip.getElementOnV(i);
			char index = (char) (I + offset);
			chip.setElementInMemory(index, VI);
			offset++;
		}

		chip.incrementPc();
	}

}

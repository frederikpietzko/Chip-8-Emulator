package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x8XY0 extends OpcodeCommand {

	public x8XY0(Chip chip) {
		super(chip);
	}

	/**
	 * 8XY0: Sets VX to the value of VY.
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		chip.setElementOnV(x, chip.getElementOnV(y));
		chip.incrementPc();
	}

}

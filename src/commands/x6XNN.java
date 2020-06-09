package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x6XNN extends OpcodeCommand {

	public x6XNN(Chip chip) {
		super(chip);
	}

	/**
	 * 6XNN: Sets VX to NN.
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int nn = opcode & 0x00FF;
		chip.setElementOnV(x, (char)nn);
		chip.incrementPc();
	}

}

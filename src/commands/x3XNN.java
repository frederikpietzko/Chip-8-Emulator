package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x3XNN extends OpcodeCommand {

	public x3XNN(Chip chip) {
		super(chip);
	}

	/**
	 * 3XNN Skips the next instruction if VX equals NN. (Usually the next instruction is a jump to skip a code block)
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int nn = opcode & 0x00FF;
		if(chip.getElementOnV(x) == nn)
			chip.incrementPc();
		chip.incrementPc();
	}

}

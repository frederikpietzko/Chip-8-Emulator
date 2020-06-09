package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x4XNN extends OpcodeCommand {

	public x4XNN(Chip2 chip) {
		super(chip);
	}

	/**
	 * 4XNN: Skips the next instruction if VX doesn't equal NN. (Usually the next instruction is a jump to skip a code block) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int nn = opcode & 0x00FF;
		if(chip.getElementOnV(x) != nn) {
			chip.incrementPc();
		}
		chip.incrementPc();
	}

}

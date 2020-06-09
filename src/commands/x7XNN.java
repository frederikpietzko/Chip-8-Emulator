package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x7XNN extends OpcodeCommand {

	public x7XNN(Chip chip) {
		super(chip);
	}
	
	/**
	 * 7XNN: Adds NN to VX. (Carry flag is not changed) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int nn = opcode & 0x00FF;
		char value = (char) ((chip.getElementOnV(x) + nn) & 0xFF);
		chip.setElementOnV(x, value);
		chip.incrementPc();
	}

}

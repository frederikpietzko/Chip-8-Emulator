package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xBNNN extends OpcodeCommand {

	public xBNNN(Chip chip) {
		super(chip);
	}

	/**
	 * BNNN: Jumps to the address NNN plus V0. 
	 */
	@Override
	public void execute(Character opcode) {
		int nnn = opcode & 0x0FFF;
		int V0 = chip.getElementOnV(0);
		char value = (char) ((V0 + nnn) & 0xFF);
		chip.setPc(value);
		System.out.println("Sets PC to: " + chip.getPc());

	}

}

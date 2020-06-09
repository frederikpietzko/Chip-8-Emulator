package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x1NNN extends OpcodeCommand {

	public x1NNN(Chip chip) {
		super(chip);
	}

	/**
	 * Jumps to address NNN.
	 */
	@Override
	public void execute(Character opcode) {
		int nnn = opcode & 0x0FFF;
		chip.setPc((char) nnn);
		System.out.println("Sets PC to: " + chip.getPc());

	}

}

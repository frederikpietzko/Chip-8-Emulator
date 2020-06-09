package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x00EE extends OpcodeCommand {

	public x00EE(Chip2 chip) {
		super(chip);
	}

	@Override
	/**
	 * 00EE: Returns from a subroutine.
	 */
	public void execute(Character opcode) {

		char[] stack = chip.getStack();
		chip.setStackPointer(chip.getStackPointer() - 1);
		chip.setPc((char) (stack[chip.getStackPointer()] + 2));
		
		System.out.println("Sets PC to: " + chip.getPc());

	}

}

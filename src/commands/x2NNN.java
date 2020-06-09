package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x2NNN extends OpcodeCommand {

	public x2NNN(Chip2 chip) {
		super(chip);
	}

	/**
	 * 2NNN: Calls subroutine at NNN. 
	 */
	@Override
	public void execute(Character opcode) {
		
		int nnn = opcode & 0x0FFF;
		chip.setElementOnStack(chip.getStackPointer(), (char) chip.getPc());
		chip.setStackPointer(chip.getStackPointer() + 1);
		chip.setPc((char)nnn);
		
		System.out.println("Sets PC to: " + chip.getPc());

	}

}

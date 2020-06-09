package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xANNN extends OpcodeCommand {

	public xANNN(Chip chip) {
		super(chip);
	}
	
	/**
	 * ANNN: Sets I to the address NNN. 
	 */
	@Override
	public void execute(Character opcode) {
		int nnn = opcode & 0x0FFF;
		chip.setI((char)nnn);
		chip.incrementPc();
	}

}

package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xEXA1 extends OpcodeCommand {

	public xEXA1(Chip chip) {
		super(chip);
	}

	/**
	 * EXA1: Skips the next instruction if the key stored in VX isn't pressed. (Usually the next instruction is a jump to skip a code block) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int VX = chip.getElementOnV(x);
		if(!chip.keyPressed(VX))
			chip.incrementPc();
		chip.incrementPc();
	}

}

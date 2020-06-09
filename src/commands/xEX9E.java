package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xEX9E extends OpcodeCommand {

	public xEX9E(Chip chip) {
		super(chip);
	}

	/**
	 * EX9E: Skips the next instruction if the key stored in VX is pressed. (Usually the next instruction is a jump to skip a code block) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int VX = chip.getElementOnV(x);
		if(chip.keyPressed(VX))
			chip.incrementPc();
		chip.incrementPc();
	}

}

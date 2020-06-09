package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x5XY0 extends OpcodeCommand {

	public x5XY0(Chip2 chip) {
		super(chip);
	}

	/**
	 * 5XY0: Skips the next instruction if VX equals VY. (Usually the next instruction is a jump to skip a code block) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		if(chip.getElementOnV(x) == chip.getElementOnV(y)) {
			chip.incrementPc();
		}
		
		chip.incrementPc();
	}

}

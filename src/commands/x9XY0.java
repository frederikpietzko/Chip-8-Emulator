package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x9XY0 extends OpcodeCommand {

	public x9XY0(Chip chip) {
		super(chip);
	}

	/**
	 * 9XY0: Skips the next instruction if VX doesn't equal VY. (Usually the next instruction is a jump to skip a code block) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		int Vx = chip.getElementOnV(x);
		int Vy = chip.getElementOnV(y);
		
		if(Vx != Vy)
			chip.incrementPc();
		chip.incrementPc();
	}

}

package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x8XY4 extends OpcodeCommand {

	public x8XY4(Chip chip) {
		super(chip);
	}

	/**
	 * 8XY4: Adds VY to VX. VF is set to 1 when there's a carry, and to 0 when there isn't. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		int Vx = chip.getElementOnV(x);
		int Vy = chip.getElementOnV(y);
		
		if (Vy > Vx - 0xFF )
			chip.setElementOnV(0xF, (char) 1);
		else
			chip.setElementOnV(0xF, (char) 0);
		
		char value = (char) ((Vx + Vy) & 0xFF);
		chip.setElementOnV(x, value);
		chip.incrementPc();
	}

}

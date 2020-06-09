package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x8XY5 extends OpcodeCommand {

	public x8XY5(Chip chip) {
		super(chip);
	}

	/**
	 * 8XY5: VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1 when there isn't. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		int Vx = chip.getElementOnV(x);
		int Vy = chip.getElementOnV(y);
		
		if (Vx > Vy)
			chip.setElementOnV(0xF, (char) 1);
		else
			chip.setElementOnV(0xF, (char) 0);
		
		char value = (char) ((Vx - Vy) & 0xFF);
		chip.setElementOnV(x, value);
		chip.incrementPc();
	}

}

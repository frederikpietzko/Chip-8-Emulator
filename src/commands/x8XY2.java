package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x8XY2 extends OpcodeCommand {

	public x8XY2(Chip2 chip) {
		super(chip);
	}

	/**
	 * 8XY2: Sets VX to VX and VY. (Bitwise AND operation) 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		int Vx = chip.getElementOnV(x);
		int Vy = chip.getElementOnV(y);
		
		char value = (char) (Vx & Vy);
		
		chip.setElementOnV(x, value);
		chip.incrementPc();
	}

}

package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x8XY3 extends OpcodeCommand {

	public x8XY3(Chip chip) {
		super(chip);
	}

	/**
	 * 8XY3: Sets VX to VX xor VY. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		
		int Vx = chip.getElementOnV(x);
		int Vy = chip.getElementOnV(y);
		
		char value = (char) (Vx ^ Vy);
		
		chip.setElementOnV(x, value);
		chip.incrementPc();
	}

}

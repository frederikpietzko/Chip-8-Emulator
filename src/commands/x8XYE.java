package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x8XYE extends OpcodeCommand {

	public x8XYE(Chip2 chip) {
		super(chip);
	}

	/**
	 * 9XYE: Stores the most significant bit of VX in VF and then shifts VX to the left by 1.[b] 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		
		int Vx = chip.getElementOnV(x);
		
		int msb = (Vx & 0xFF) >> 7;
		
		chip.setElementOnV(0xF, (char) msb);
		
		chip.setElementOnV(x, (char) (Vx << 1));
		
		chip.incrementPc();
	}

}

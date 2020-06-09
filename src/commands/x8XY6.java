package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class x8XY6 extends OpcodeCommand {

	public x8XY6(Chip2 chip) {
		super(chip);
	}
	
	/**
	 * 8XY6: Stores the least significant bit of VX in VF and then shifts VX to the right by 1.[b] 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		
		int Vx = chip.getElementOnV(x);
		
		int lsb = Vx & 1;
		
		chip.setElementOnV(x, (char) (Vx >> 1));
		chip.setElementOnV(0xF, (char) lsb);
		chip.incrementPc();
	}

}

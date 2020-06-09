package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class xFX1E extends OpcodeCommand {

	public xFX1E(Chip2 chip) {
		super(chip);
	}

	/**
	 * FX1E: Adds VX to I. VF is set to 1 when there is a range overflow (I+VX>0xFFF), and to 0 when there isn't.[c] 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int VX = chip.getElementOnV(x);
		int I = chip.getI();
		
		if(VX + I > 0xFFF)
			chip.setElementOnV(0xF, (char) 1);
		else
			chip.setElementOnV(0xF,  (char) 0);
		
		char value = (char) ((VX + I) & 0xFFF);
		
		chip.setI(value);
		chip.incrementPc();
	}

}

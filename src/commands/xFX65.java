package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX65 extends OpcodeCommand {

	public xFX65(Chip chip) {
		super(chip);
	}

	/**
	 * FX65: Fills V0 to VX (including VX) with values from memory starting at address I. The offset from I is increased by 1 for each value written, but I itself is left unmodified.
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int I = chip.getI();
		int offset = 0;
		var memory  = chip.getMemory();
		for(int i = 0; i < x + 1; i++) {
			char value = memory[I + offset];
			chip.setElementOnV(i, value);
			offset++;
		}
		
		chip.incrementPc();
	}

}

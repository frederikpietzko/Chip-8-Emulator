package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class x00E0 extends OpcodeCommand {

	public x00E0(Chip chip) {
		super(chip);
	}

	@Override
	/**
	 * 00E0: Clears the screen.
	 */
	public void execute(Character opcode) {
		this.chip.setDisplay(new byte[64*32]);
	}

}

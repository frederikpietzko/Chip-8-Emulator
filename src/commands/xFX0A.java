package commands;

import chip.Chip;
import chip.OpcodeCommand;

public class xFX0A extends OpcodeCommand {

	public xFX0A(Chip chip) {
		super(chip);
	}

	/**
	 * FX0A: A key press is awaited, and then stored in VX. (Blocking Operation. All
	 * instruction halted until next key event)
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int keyPressed = -1;
		while (keyPressed == -1) {
			keyPressed = chip.getPressedKey();
		}

		chip.setElementOnV(x, (char) keyPressed);

		chip.incrementPc();
	}

}

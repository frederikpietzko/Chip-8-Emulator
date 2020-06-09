package commands;

import java.util.Random;

import chip.Chip2;
import chip.OpcodeCommand;

public class xCXNN extends OpcodeCommand {

	public xCXNN(Chip2 chip) {
		super(chip);
	}

	/**
	 * CXNN: Sets VX to the result of a bitwise and operation on a random number (Typically: 0 to 255) and NN. 
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int nn = opcode & 0x00FF;
		int randomNumber = new Random().nextInt(255) & nn;
		chip.setElementOnV(x, (char) randomNumber);
		chip.incrementPc();
	}

}

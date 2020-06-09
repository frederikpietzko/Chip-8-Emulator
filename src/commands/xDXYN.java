package commands;

import chip.Chip2;
import chip.OpcodeCommand;

public class xDXYN extends OpcodeCommand {

	public xDXYN(Chip2 chip) {
		super(chip);
	}

	/**
	 * DXYN: Draws a sprite at coordinate (VX, VY) that has a width of 8 pixels and
	 * a height of N pixels. Each row of 8 pixels is read as bit-coded starting from
	 * memory location I; I value doesn’t change after the execution of this
	 * instruction. As described above, VF is set to 1 if any screen pixels are
	 * flipped from set to unset when the sprite is drawn, and to 0 if that doesn’t
	 * happen
	 */
	@Override
	public void execute(Character opcode) {
		int x = (opcode & 0x0F00) >> 8;
		int y = (opcode & 0x00F0) >> 4;
		int VX = chip.getElementOnV(x);
		int VY = chip.getElementOnV(y);
		int height = opcode & 0x000F;

		chip.setElementOnV(0xF, (char) 0);

		int I = chip.getI();
		byte[] display = chip.getDisplay();
		char[] memory = chip.getMemory();

		for (int _y = 0; _y < height; _y++) {
			int line = memory[I + _y];
			for (int _x = 0; _x < 8; _x++) {
				int pixel = line & (0x80 >> _x);
				if (pixel != 0) {
					int totalX = VX + _x;
					int totalY = VY + _y;

					totalX = totalX % 64;
					totalY = totalY % 32;

					int index = (totalY * 64) + totalX;

					if (display[index] == 1)
						chip.setElementOnV(0xF, (char) 1);

					chip.XORPixel(index);
				}
			}
		}

		chip.setNeedsRedraw(true);
		chip.incrementPc();
	}

}

package chip;

import java.io.DataInputStream;
import commands.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Chip2 implements IChip{
	/**
	 * 4kB of 8-bit memory<br/>
	 * At position 0x50: The "bios" fontset At position 0x200: The start of every
	 * program
	 */
	private char[] memory;
	/**
	 * 16 8-bit registers.<br/>
	 * They will be used to store data which is used in several operation<br/>
	 * Register 0xF is used for Carry, Borrow and collision detection
	 */
	private char[] V;
	/**
	 * 16-bit (only 12 are used) to point to a specific point in the memory
	 */
	private char I;
	/**
	 * The 16-bit (only 12 are used) to point to the current operation
	 */
	private char pc;

	/**
	 * Subroutine callstack<br/>
	 * Allows up to 16 levels of nesting
	 */
	private char stack[];
	/**
	 * Points to the next free slot int the stack
	 */
	private int stackPointer;

	/**
	 * This timer is used to delay events in programs/games
	 */
	private int delay_timer;
	/**
	 * This timer is used to make a beeping sound
	 */
	private int sound_timer;

	/**
	 * This array will be our keyboard state
	 */
	private byte[] keys;
	/**
	 * The 64x32 pixel monochrome (black/white) display
	 */
	private byte[] display;

	private boolean needRedraw;

	private final Opcode[] opcodes;

	public Chip2() {
		this.opcodes = new Opcode[] { new Opcode(0xF0FF, 0x00E0, new x00E0(this)),
				new Opcode(0xF0FF, 0x00EE, new x00EE(this)), new Opcode(0xF000, 0x1000, new x1NNN(this)),
				new Opcode(0xF000, 0x2000, new x2NNN(this)), new Opcode(0xF000, 0x3000, new x3XNN(this)),
				new Opcode(0xF000, 0x4000, new x4XNN(this)), new Opcode(0xF000, 0x5000, new x5XY0(this)),
				new Opcode(0xF000, 0x6000, new x6XNN(this)), new Opcode(0xF000, 0x7000, new x7XNN(this)),
				new Opcode(0xF00F, 0x8000, new x8XY0(this)), new Opcode(0xF00F, 0x8001, new x8XY1(this)),
				new Opcode(0xF00F, 0x8002, new x8XY2(this)), new Opcode(0xF00F, 0x8003, new x8XY3(this)),
				new Opcode(0xF00F, 0x8004, new x8XY4(this)), new Opcode(0xF00F, 0x8005, new x8XY5(this)),
				new Opcode(0xF00F, 0x8006, new x8XY6(this)), new Opcode(0xF00F, 0x8007, new x8XY7(this)),
				new Opcode(0xF00F, 0x800E, new x8XYE(this)), new Opcode(0xF000, 0x9000, new x9XY0(this)),
				new Opcode(0xF000, 0xA000, new xANNN(this)), new Opcode(0xF000, 0xB000, new xBNNN(this)),
				new Opcode(0xF000, 0xC000, new xCXNN(this)), new Opcode(0xF000, 0xD000, new xDXYN(this)),
				new Opcode(0xF0FF, 0xE09E, new xEX9E(this)), new Opcode(0xF0FF, 0xE0A1, new xEXA1(this)),
				new Opcode(0xF0FF, 0xF007, new xFX07(this)), new Opcode(0xF0FF, 0xF00A, new xFX0A(this)),
				new Opcode(0xF0FF, 0xF015, new xFX15(this)), new Opcode(0xF0FF, 0xF018, new xFX18(this)),
				new Opcode(0xF0FF, 0xF01E, new xFX1E(this)), new Opcode(0xF0FF, 0xF029, new xFX29(this)),
				new Opcode(0xF0FF, 0xF033, new xFX33(this)), new Opcode(0xF0FF, 0xF055, new xFX55(this)),
				new Opcode(0xF0FF, 0xF065, new xFX65(this)), };
	}

	/**
	 * Reset the Chip 8 memory and pointers
	 */
	public void init() {
		memory = new char[4096];
		V = new char[16];
		I = 0x0;
		pc = 0x200;

		stack = new char[16];
		stackPointer = 0;

		delay_timer = 0;
		sound_timer = 0;

		keys = new byte[16];

		display = new byte[64 * 32];

		needRedraw = false;
		loadFontset();
	}

	public void run() {
		// fetch Opcode
		char opcode = (char) ((memory[pc] << 8) | memory[pc + 1]);
		System.out.println(Integer.toHexString(opcode).toUpperCase() + ": ");

		for (var c : opcodes) {
			c.execute(opcode);
		}
		
		if(sound_timer > 0)
			sound_timer--;
		if(delay_timer > 0)
			delay_timer--;
		
	}

	/**
	 * Returns the display data
	 * 
	 * @return Current state of the 64x32 display
	 */
	public byte[] getDisplay() {
		return display;
	}

	public void setDisplay(byte[] dispay) {
		this.display = dispay;
	}

	public char[] getStack() {
		return this.stack;
	}

	public void setElementOnStack(int index, char element) {
		this.stack[index] = element;
	}

	public int getPc() {
		return this.pc;
	}

	public void setPc(char pc) {
		this.pc = pc;
	}

	public int getStackPointer() {
		return this.stackPointer;
	}

	public void setStackPointer(int stackPointer) {
		System.out.println("Setting StackPointer to: " + stackPointer);
		this.stackPointer = stackPointer;
	}

	public void incrementStackPointer() {
		this.stackPointer++;
	}

	public char getElementOnV(int index) {
		return this.V[index];
	}

	public void setElementOnV(int index, char element) {
		this.V[index] = element;
	}

	public char getI() {
		return this.I;
	}

	public void setI(char I) {
		this.I = I;
	}

	/**
	 * Checks if there is a redraw needed
	 * 
	 * @return If a redraw is needed
	 */
	public boolean needsRedraw() {
		return needRedraw;
	}

	public void setNeedsRedraw(boolean redraw) {
		this.needRedraw = redraw;
	}

	public char[] getMemory() {
		return this.memory;
	}

	public void XORPixel(int index) {
		display[index] ^= 1;
	}

	public boolean keyPressed(int key) {
		return this.keys[key] == 1;
	}

	public int getPressedKey() {
		for (int i = 0; i < keys.length; i++) {
			if (keyPressed(i))
				return i;
		}
		return -1;
	}

	public int getDelayTimer() {
		return this.delay_timer;
	}
	
	public void setDelayTimer(int delayTimer) {
		this.delay_timer = delayTimer;
	}
	
	public void setSoundTimer(int soundTimer) {
		this.sound_timer = soundTimer;
	}

	public void setElementInMemory(int index, char value) {
		this.memory[index] = value;
	}
	
	/**
	 * Notify the chip that is has been redrawn
	 */
	public void removeDrawFlag() {
		needRedraw = false;
	}

	/**
	 * Loads the program into the memory
	 * 
	 * @param file The location of the program
	 */
	public void loadProgram(String file) {
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream(new File(file)));

			int offset = 0;
			while (input.available() > 0) {
				memory[0x200 + offset] = (char) (input.readByte() & 0xFF);
				offset++;
			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
				}
			}
		}
	}

	public void incrementPc() {
		this.pc += 2;
	}

	/**
	 * Loads the fontset into the memory
	 */
	public void loadFontset() {
		for (int i = 0; i < ChipData.fontset.length; i++) {
			memory[0x50 + i] = (char) (ChipData.fontset[i] & 0xFF);
		}
	}

	public void setKeyBuffer(int[] keyBuffer) {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = (byte) keyBuffer[i];
		}
	}
}

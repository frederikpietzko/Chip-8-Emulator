package chip;

public class Opcode {
	private final char mask;
	private final char match;
	private final OpcodeCommand command;

	public Opcode(int mask, int match, OpcodeCommand command) {
		this.command = command;
		this.mask = (char) mask;
		this.match = (char) match;
	}

	private boolean doesMatch(char opcode) {
		return (opcode & mask) == match;
	}

	/**
	 * Executes the Function fn if the opcode matches. Returns if it got executed.
	 * @param opcode The Operation Code.
	 * @return If the Function fn got executed.
	 */
	public boolean execute(char opcode) {
		if (!doesMatch(opcode))
			return false;
		
		command.execute(opcode);
		
		return true;
		
	}

}

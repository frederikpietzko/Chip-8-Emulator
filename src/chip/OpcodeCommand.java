package chip;

public abstract class OpcodeCommand implements Command<Character> {
	protected final Chip chip;
	
	public OpcodeCommand(Chip chip) {
		this.chip = chip;
	}
	}

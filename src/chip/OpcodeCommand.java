package chip;

public abstract class OpcodeCommand implements Command<Character> {
	protected final Chip2 chip;
	
	public OpcodeCommand(Chip2 chip) {
		this.chip = chip;
	}
	}

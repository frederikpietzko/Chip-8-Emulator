package chip;

public interface IChip {
	void init();
	void run();
	void setKeyBuffer(int[] keyBuffer);
	boolean needsRedraw();
	void removeDrawFlag();
	void loadProgram(String path);
	byte[] getDisplay();
}

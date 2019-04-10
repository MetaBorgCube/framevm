package framevm.strategies.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The environment that is passed around.
 * Contains a heap of all frames, a list of blocks, 
 * a pointer to the currently active frame and the console output.
 */
public class MachineState {
	public HashMap<String, Block> blocks;
	public MachineThread thread;
	public StringBuilder stdout;
	public String debug;	
	
	private int frameCount;		// Used for generating unique frame ids
	private int linkSize;
	public MachineThread currentThread;
	private int controlCount;
	
	/**
	 * Constructor for a new environment.
	 * @param link_size 
	 * 		The size of the link registers
	 */
	public MachineState(int link_size) {
		this.blocks = new HashMap<>();
		this.stdout = new StringBuilder();
		this.debug = "";
		
		this.linkSize = link_size;

		this.frameCount = 0;
		this.controlCount = 0;
	}
	
	/**
	 * Get the {@link Block} with the given name.
	 * 
	 * @param name
	 * 		The name of the block
	 * @return
	 * 		The block
	 */
	public Block getBlock(String name) {
		return blocks.get(name);
	}
		
	/**
	 * Create a new frame.
	 * 
	 * @param size
	 * 		The amount of slots for the new frame
	 * @return
	 * 		The new frame
	 */
	public Frame newFrame(int size) {
		String id = "frame_" + frameCount++;
		return new Frame(id, size, linkSize);
	}

	/**
	 * Create a new frame as a copy of the given frame and put it on the heap.
	 * 
	 * @param old
	 * 		The frame to copy
	 * @return
	 * 		The id of the new frame
	 */
	public Frame newFrameFrom(Frame old) {
		String id = "frame_" + frameCount++;
		return new Frame(id, old);
	}

	/**
	 * Set the debug string.
	 * 
	 * @param debug
	 * 		The debug string
	 */
	public void setDebug(String debug) {
		this.debug = debug;
	}

	public int getLinkSize() {
		return linkSize;
	}

	public MachineThread getNextThread() {
		//TODO: Scheduling here if multi-threading is added
		this.currentThread = thread;
		return thread;
	}

	public void addThread(MachineThread thread) {
		this.thread = thread;
		if (this.currentThread == null) {
			this.currentThread = thread;
		}
	}

	public List<MachineThread> getThreads() {
		ArrayList<MachineThread> res = new ArrayList<>();
		res.add(currentThread);
		return res;
	}

	public ControlFrame newControlFrame(int contSize, Block block) {
		String id = "controlFrame_" + controlCount++;
		return new ControlFrame(contSize, block, id);
	}

	public ControlFrame newControlFrame(int contSize, Frame frame, Block block) {
		String id = "controlFrame_" + controlCount++;
		return new ControlFrame(contSize, frame, block, id);
	}
}

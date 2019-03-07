package framevm.strategies.util;

import java.util.HashMap;

/**
 * The environment that is passed around.
 * Contains a heap of all frames, a list of blocks, 
 * a pointer to the currently active frame and the console output.
 */
public class Environment {
	//TODO: This heap shouldn't be needed in a full Java implementation as all frames are linked
	// - `dup` instruction breaks stuff as you don't want to duplicate this, so the operandStacks stack might need a tiny local heap
	public HashMap<String, Frame> heap;
	public HashMap<String, Block> blocks;
	public Frame currentFrame;
	public StringBuilder stdout;	
	
	private int count;		// Used for generating unique frame ids
	
	/**
	 * Constructor for a new environment.
	 */
	public Environment() {
		this.heap = new HashMap<>();
		this.blocks = new HashMap<>();
		this.stdout = new StringBuilder();	
		
		this.count = 0;
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
	 * Get the {@link Frame} with the given id.
	 * 
	 * @param id
	 * 		The id of the frame
	 * @return
	 * 		The frame
	 */
	public Frame getFrame(String id) {
		return heap.get(id);
	}
	
	/**
	 * Create a new frame and put it on the heap.
	 * 
	 * @return
	 * 		The id of the new frame
	 */
	public String newFrame() {
		String id = "frame_" + count++;
		heap.put(id, new Frame(id));
		return id;
	}

	/**
	 * Clear all values stored in this environment.
	 */
	public void clear() {
		currentFrame = null;
		stdout = new StringBuilder();
		blocks = new HashMap<>();
		heap = new HashMap<>();
		
		count = 0;
	}
}

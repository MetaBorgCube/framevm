package framevm.strategies.util;

import java.util.HashMap;

import framevm.strategies.Frame;

public class Environment {
	public HashMap<String, Routine> routines = new HashMap<>();
	
	//TODO: This heap shouldn't be needed in a full Java implementation as all frames are linked
	// - `dup` instruction breaks stuff as you don't want to duplicate this, so the operandStacks stack might need a tiny local heap
	public HashMap<String, Frame> heap = new HashMap<>();
	
	public Frame currentFrame;
	public StringBuilder stdout = new StringBuilder();

	private int count = 0;
	
	public Routine getRoutine(String name) {
		return routines.get(name);
	}
	
	public Frame getFrame(String id) {
		return heap.get(id);
	}
	
	public String newFrame() {
		String id = "frame_" + count++;
		heap.put(id, new Frame(id));
		return id;
	}

	public void clear() {
		currentFrame = null;
		stdout = new StringBuilder();

		routines = new HashMap<>();
		heap = new HashMap<>();
	}
}

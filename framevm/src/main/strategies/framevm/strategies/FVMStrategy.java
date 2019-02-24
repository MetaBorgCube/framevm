package framevm.strategies;

import java.util.HashMap;

import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Routine;

public class FVMStrategy extends Strategy {
	protected static HashMap<String, Routine> routines = new HashMap<>();
	
	//TODO: This heap shouldn't be needed in a full Java implementation as all frames are linked
	// - `dup` instruction breaks stuff as you don't want to duplicate this, so the operandStacks stack might need a tiny local heap
	protected static HashMap<String, Frame> heap = new HashMap<>();
	
	protected static Frame currentFrame;
	protected static StringBuilder stdout = new StringBuilder();

	private static int count = 0;
	
	protected Routine getRoutine(String name) {
		return routines.get(name);
	}
	
	protected Frame getFrame(String id) {
		return heap.get(id);
	}
	
	protected String newFrameID() {
		String id = "frame_" + count++;
		heap.put(id, new Frame(id));
		return id;
	}
}

package org.metaborg.lang.framevm_core.util;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public class ControlFrame {
	private Continuation[] continuations;
	private Frame currentFrame;
	private String id;
	
	/**
	 * Create an operand stack.
	 */
	public ControlFrame(int contSize, String id) {
		this.continuations = new Continuation[contSize];
		
		this.currentFrame = null;
		this.id = id;
	}
	
	/**
	 * Get the continuation from the specified slot.
	 * 
	 * @param slotIdx
	 * 		The index of the continuation slot 
	 * @return
	 * 		The current continuation
	 */
	public Continuation getContinuation(int idx) {
		if (idx >= this.continuations.length) return null;
		return this.continuations[idx];
	}

	public void setContinuation(int idx, Continuation continuation) {
		this.continuations[idx] = continuation;
	}
	
	public Continuation[] getContinuations() {
		return this.continuations;
	}

	public Frame getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(Frame frame) {
		this.currentFrame = frame;
	}

	public String getId() {
		return this.id;
	}
	
	public String toString() {
		return "ControlFrame(" + this.getId() + ", " + this.getCurrentFrame().getId() + ")";
	}

}

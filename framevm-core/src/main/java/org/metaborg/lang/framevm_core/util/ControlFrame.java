package org.metaborg.lang.framevm_core.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public abstract class ControlFrame {
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
	
	public ControlFrame(int contSize, Frame frame, String id) {
		this.continuations = new Continuation[contSize];
		
		this.currentFrame = frame;
		this.id = id;
	}

	public ControlFrame(String id, ControlFrame old, CopyPolicy framePolicy, MachineState env) {
		this.id = id;
		
		this.currentFrame = env.newFrameFrom(old.getCurrentFrame(), framePolicy);
		
		Continuation[] oldContinuation = old.getContinuations();
		this.continuations = new Continuation[oldContinuation.length];
		for (int i = 0; i < continuations.length; i++) {
			this.continuations[i] = oldContinuation[i];
		}
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

	public abstract void pushReturn(IStrategoTerm result);
	public abstract IStrategoTerm popReturn();
	public abstract boolean hasReturn();

	public abstract void setSize(int size);
	public abstract ControlFrameMemory getMemory();
	public abstract void restoreMemory(ControlFrameMemory mem);
	
	public String toString() {
		return "Continuation(" + this.getId() + ")";
	}

}

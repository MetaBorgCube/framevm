package org.metaborg.lang.framevm_core.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public abstract class ControlFrame {
	private Block block;
	private int instr_count;
	private Continuation[] continuations;
	private Frame currentFrame;
	private String id;
	
	/**
	 * Create an operand stack.
	 */
	public ControlFrame(int contSize, Block block, String id) {
		this.block = block;
		this.continuations = new Continuation[contSize];
		
		this.instr_count = 0;
		this.currentFrame = null;
		this.id = id;
	}
	
	public ControlFrame(int contSize, Frame frame, Block block, String id) {
		this.block = block;
		this.continuations = new Continuation[contSize];
		
		this.instr_count = 0;
		this.currentFrame = frame;
		this.id = id;
	}

	public ControlFrame(String id, ControlFrame old, CopyPolicy framePolicy, MachineState env) {
		this.id = id;
		this.block = old.block;
		this.instr_count = old.instr_count;
		
		this.currentFrame = env.newFrameFrom(old.getCurrentFrame(), framePolicy);
		
		Continuation[] oldContinuation = old.getContinuations();
		this.continuations = new Continuation[oldContinuation.length];
		for (int i = 0; i < continuations.length; i++) {
			this.continuations[i] = oldContinuation[i];
		}
	}

	/**
	 * @return
	 * 		The next instruction
	 */
	public IStrategoTerm nextInstruction() {
		return block.getInstr(instr_count++);
	}
	
	/**
	 * @return
	 * 		True if there is a next instruction, false otherwise
	 */
	public boolean hasNextInstruction() {
		return block != null && null != block.getInstr(instr_count);
	}
	
	/**
	 * Jump execution to the given block.
	 * 
	 * @param block
	 * 		The block to jump to
	 */
	public void jump(Block block) {
		this.jump(block, 0);
	}
	public void jump(Block block, int count) {
		this.block = block;
		this.instr_count = count;
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
	
	public Block getBlock() {
		return block;
	}

	public int getInstr_count() {
		return instr_count;
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
}

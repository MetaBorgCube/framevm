package org.metaborg.lang.framevm_core.util;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public class ControlFrame {
	private Continuation[] continuations;
	private Frame currentFrame;
	private String id;
	private HashMap<Integer, IStrategoTerm> registers;

	private static final Pattern LOCAL = Pattern.compile("\\w([0-9]+)");
	
	/**
	 * Create an operand stack.
	 */
	public ControlFrame(int contSize, String id, Frame frame) {
		this.continuations = new Continuation[contSize];
		
		this.registers = new HashMap<>();
		this.currentFrame = null;
		this.id = id;
		this.currentFrame = frame;
	}

	private int slotId(String slot) {
		Matcher matcher = LOCAL.matcher(slot);
		matcher.matches();
		matcher.group();
		return Integer.valueOf(matcher.group(1));
	}

	public void restoreMemory(Memory mem) {
		this.registers = mem.getRegisters();
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

	public String getId() {
		return this.id;
	}
	
	public String toString() {
		return "ControlFrame(" + this.getId() + ", " + this.getCurrentFrame().getId() + ")";
	}

	public void setCurrentFrame(Frame frame) {
		this.currentFrame = frame;
	}

	public IStrategoTerm getRegister(String slot) {
		return this.registers.get(slotId(slot));
	}

	public void setRegister(String slot, IStrategoTerm value) {
		this.registers.put(slotId(slot), value);
	}

	@SuppressWarnings("unchecked")
	public HashMap<Integer, IStrategoTerm> getRegisterCopy() {
		return (HashMap<Integer, IStrategoTerm>) this.registers.clone();
	}

	public int getRegistersSize() {
		return this.registers.size();
	}

}

package org.metaborg.lang.framevm_core.util;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class RegisterControlFrame extends ControlFrame {
	private Stack<IStrategoTerm> returnStack;
	private IStrategoTerm[] locals;
	
	private static final Pattern LOCAL = Pattern.compile("\\w([0-9]+)");
	
	public RegisterControlFrame(int contSize, int locals, String id) {
		super(contSize, id);
		this.returnStack = new Stack<>();
		if (locals >= 0) {
			this.locals = new IStrategoTerm[locals];
		} else {
			this.locals = null;
		}
	}
	
	private int slotId(String slot) {
		Matcher matcher = LOCAL.matcher(slot);
		matcher.matches();
		matcher.group();
		return Integer.valueOf(matcher.group(1));
	}
	
	public void set(String slot, IStrategoTerm term) {
		if (locals == null) throw new IllegalStateException("Locals not set");
		locals[slotId(slot)] = term;
	}
	
	public IStrategoTerm get(String slot) {
		if (locals == null) throw new IllegalStateException("Locals not set");
		return locals[slotId(slot)];
	}
	
	public boolean hasReturn() {
		return !returnStack.isEmpty();
	}
	
	public Stack<IStrategoTerm> getReturns() {
		return returnStack;
	}

	public IStrategoTerm[] getLocals() {
		return this.locals;
	}

	@Override
	public void pushReturn(IStrategoTerm result) {
		returnStack.push(result);
	}

	@Override
	public IStrategoTerm popReturn() {
		return returnStack.pop();
	}

	@Override
	public void setSize(int size) {
		if (this.locals != null) throw new IllegalStateException("Local size already set!");
		
		this.locals = new IStrategoTerm[size];
	}

	@Override @SuppressWarnings("unchecked")
	public ControlFrameMemory getMemory() {
		return new RegisterControlFrameMemory((Stack<IStrategoTerm>) this.returnStack.clone(), (IStrategoTerm[]) this.locals.clone());
	}

	@Override
	public void restoreMemory(ControlFrameMemory mem, Frame frame) {
		if (mem == null) return; // Nothing to do for the empty restore
		if (! (mem instanceof RegisterControlFrameMemory)) throw new IllegalStateException("Cannot restore stack control frame memory in a register control frame");
		this.locals = ((RegisterControlFrameMemory) mem).getLocals();
		this.returnStack = mem.getStack();
		this.setCurrentFrame(frame);
	}
}

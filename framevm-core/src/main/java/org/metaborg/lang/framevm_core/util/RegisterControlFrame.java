package org.metaborg.lang.framevm_core.util;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class RegisterControlFrame extends ControlFrame {
	private Stack<IStrategoTerm> returnStack;
	private IStrategoTerm[] locals;
	
	private static final Pattern LOCAL = Pattern.compile("\\w([0-9]+)");
	
	public RegisterControlFrame(int contSize, int locals, Block block, String id) {
		super(contSize, block, id);
		this.returnStack = new Stack<>(); //TODO: pre-allocate block.max-stack
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
		locals[slotId(slot)] = term;
	}
	
	public IStrategoTerm get(String slot) {
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
}

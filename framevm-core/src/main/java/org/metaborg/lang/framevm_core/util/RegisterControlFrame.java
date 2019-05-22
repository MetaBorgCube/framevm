package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class RegisterControlFrame extends ControlFrame {
	private Stack<IStrategoTerm> returnStack;
	
	public RegisterControlFrame(int contSize, Block block, String id) {
		super(contSize, block, id);
		this.returnStack = new Stack<>(); //TODO: pre-allocate block.max-stack
	}
	
	public RegisterControlFrame(int contSize, Frame frame, Block block, String id) {
		super(contSize, frame, block, id);
		this.returnStack = new Stack<>(); //TODO: pre-allocate block.max-stack
	}

	public IStrategoTerm addReturn(IStrategoTerm term) {
		return returnStack.push(term);
	}
	
	public IStrategoTerm getReturn() {
		return returnStack.pop();
	}

	public boolean hasReturn() {
		return !returnStack.isEmpty();
	}
	
	public Stack<IStrategoTerm> getReturns() {
		return returnStack;
	}
}

package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class ControlFrameMemory { 
	private Stack<IStrategoTerm> stack;
	
	public ControlFrameMemory(Stack<IStrategoTerm> stack) {
		this.stack = stack;
	}
	
	public Stack<IStrategoTerm> getStack() {
		return stack;
	}
}

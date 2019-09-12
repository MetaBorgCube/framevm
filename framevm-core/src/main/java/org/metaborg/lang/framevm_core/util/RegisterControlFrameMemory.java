package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class RegisterControlFrameMemory extends ControlFrameMemory {
	private IStrategoTerm[] locals;
	
	public RegisterControlFrameMemory(Stack<IStrategoTerm> stack, IStrategoTerm[] locals) {
		super(stack);
		this.locals = locals;
	}
	
	public IStrategoTerm[] getLocals() {
		return this.locals;
	}
}

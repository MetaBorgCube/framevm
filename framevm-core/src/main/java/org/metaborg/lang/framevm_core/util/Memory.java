package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Memory { 
	private Stack<IStrategoTerm> stack;
	private IStrategoTerm[] registers;
	
	public Memory(Stack<IStrategoTerm> stack, IStrategoTerm[] locals) {
		this.stack = stack;
		this.registers = locals;
	}
	
	public Stack<IStrategoTerm> getStack() {
		return stack;
	}
	
	public IStrategoTerm[] getRegisters() {
		return this.registers;
	}
}

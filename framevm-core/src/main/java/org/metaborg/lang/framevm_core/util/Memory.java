package org.metaborg.lang.framevm_core.util;

import java.util.HashMap;
import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Memory { 
	private Stack<IStrategoTerm> stack;
	private HashMap<Integer,IStrategoTerm> registers;
	
	public Memory(Stack<IStrategoTerm> stack, HashMap<Integer,IStrategoTerm> registers) {
		this.stack = stack;
		this.registers = registers;
	}
	
	public Stack<IStrategoTerm> getStack() {
		return stack;
	}
	
	public HashMap<Integer,IStrategoTerm> getRegisters() {
		return this.registers;
	}
}

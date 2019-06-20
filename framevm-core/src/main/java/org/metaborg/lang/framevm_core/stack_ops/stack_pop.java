package org.metaborg.lang.framevm_core.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoAppl;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.StackControlFrame;
import mb.nabl2.terms.stratego.StrategoBlob;

public abstract class stack_pop extends FVMStrategy {
	@Override
	// env| -> (env', val)
	// Pop a value from the stack
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StackControlFrame opStack = env.currentThread.getStackControlFrame();
		if (opStack == null) {
			io.printError("Cannot pop from a register control frame");
			return null;
		}
		
		if (!opStack.hasNext()) {
			io.printError("SEGFAULT");
			io.printError("Cannot pop from empty stack");
			return null;
		}
		IStrategoTerm term = opStack.pop();
		try {
			String name;
			if (term instanceof StrategoAppl) {
				StrategoAppl appl = (StrategoAppl) term;
				name = appl.getName();
			} else {
				name = term.toString();
			}
			if(accepted(name)) {
				return factory.makeTuple(new StrategoBlob(env), term);			
			} else {
				io.printError(term + " is not a valid " + accepted());
				return null;
			}
		} catch (ClassCastException ex) {
			io.printError(term + " is not a valid " + accepted());
			return null;			
		}
	}

	protected abstract boolean accepted(String name);
	protected abstract String accepted();
}

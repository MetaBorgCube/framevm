package framevm.strategies.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoAppl;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.OperandStack;
import mb.nabl2.stratego.StrategoBlob;

public abstract class stack_pop extends FVMStrategy {
	@Override
	// env| -> (env', val)
	// Pop a value from the stack
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		OperandStack opStack = env.currentFrame.getOperandStack();
		if (!opStack.hasNext()) {
			io.printError("SEGFAULT");
			io.printError("Cannot pop from empty stack");
			return null;
		}
		StrategoAppl term = (StrategoAppl) opStack.pop();
		if(accepted(term.getName())) {
			return factory.makeTuple(new StrategoBlob(env), term);			
		} else {
			io.printError(term + " is not a valid " + accepted());
			return null;
		}
	}

	protected abstract boolean accepted(String name);
	protected abstract String accepted();
}

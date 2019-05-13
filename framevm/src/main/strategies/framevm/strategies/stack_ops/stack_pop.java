package framevm.strategies.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoAppl;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public abstract class stack_pop extends FVMStrategy {
	@Override
	// env| -> (env', val)
	// Pop a value from the stack
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame opStack = env.currentThread.getControlFrame();
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

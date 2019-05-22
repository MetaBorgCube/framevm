package org.metaborg.lang.framevm_core.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.StackControlFrame;
import mb.nabl2.stratego.StrategoBlob;

public class stack_push_0_1 extends FVMStrategy {
	public static stack_push_0_1 instance = new stack_push_0_1();

	@Override
	// env| val -> env'
	// Push the given value to the stack
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StackControlFrame cf = env.currentThread.getStackControlFrame();
		if(cf == null) {
			io.printError("Cannot push to a register control frame");
			return null;
		}
		
		cf.push(arg);
		return new StrategoBlob(env);
	}
}

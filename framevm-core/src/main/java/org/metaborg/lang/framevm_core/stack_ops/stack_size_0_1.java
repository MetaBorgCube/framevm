package org.metaborg.lang.framevm_core.stack_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.StackControlFrame;

public class stack_size_0_1 extends FVMStrategy {
	public static stack_size_0_1 instance = new stack_size_0_1();

	@Override
	// env| -> int
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StackControlFrame cf = env.currentThread.getStackControlFrame();
		if (cf == null) {
			LOGGER.error("Cannot get stack size for a register frame");
			return null;
		}
		return factory.makeInt(cf.getStack().size());
	}
}

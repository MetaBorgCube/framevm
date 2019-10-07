package org.metaborg.lang.framevm_core.register;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;

public class rgr_size_0_1 extends FVMStrategy {
	public static rgr_size_0_1 instance = new rgr_size_0_1();

	@Override
	// env| -> int
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		return factory.makeInt(env.currentThread.getRegisters().length);
	}
}

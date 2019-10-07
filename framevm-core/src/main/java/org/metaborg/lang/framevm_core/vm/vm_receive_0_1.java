package org.metaborg.lang.framevm_core.vm;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class vm_receive_0_1 extends FVMStrategy {
	public static vm_receive_0_1 instance = new vm_receive_0_1();

	@Override
	// env| -> (env', val)
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		IStrategoTerm term = env.currentThread.popReturn();
		return factory.makeTuple(new StrategoBlob(env), term);
	}
}

package org.metaborg.lang.framevm_core.vm;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.dot.DotMachineStateFactory;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class vm_debug_0_1 extends FVMStrategy {
	public static vm_debug_0_1 instance = new vm_debug_0_1();

	@Override
	// env| -> env'
	// Return a string representation of the environment
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String debug = DotMachineStateFactory.build(env);
		env.setDebug(debug);
		return new StrategoBlob(env);
	}
}

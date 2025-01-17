package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;

public class vm_has_lib_0_1 extends FVMStrategy {
	public static vm_has_lib_0_1 instance = new vm_has_lib_0_1();

	@Override
	// env| lib
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String libName = ((StrategoString) arg).stringValue();
		
		if (env.blocks.containsKey(libName)) {
			return arg;
		} else {
			return null;
		}
	}
}

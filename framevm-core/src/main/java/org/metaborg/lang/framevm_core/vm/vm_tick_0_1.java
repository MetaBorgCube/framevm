package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.util.MachineState;


public class vm_tick_0_1 extends FVMTimer {
	public static vm_tick_0_1 instance = new vm_tick_0_1();

	@Override
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		this.start();
		return arg;
	}
}

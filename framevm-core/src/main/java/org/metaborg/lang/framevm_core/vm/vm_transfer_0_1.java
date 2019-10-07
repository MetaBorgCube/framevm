package org.metaborg.lang.framevm_core.vm;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoList;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class vm_transfer_0_1 extends FVMStrategy {
	public static vm_transfer_0_1 instance = new vm_transfer_0_1();

	@Override
	// env| [val] -> env'
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {		
		StrategoList values = (StrategoList) arg;
				
		for (IStrategoTerm value : values) {
			env.currentThread.pushReturn(value);
		}
		return new StrategoBlob(env);
	}
}

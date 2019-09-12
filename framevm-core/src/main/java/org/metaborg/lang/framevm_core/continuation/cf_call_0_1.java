package org.metaborg.lang.framevm_core.continuation;


import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import mb.nabl2.terms.stratego.StrategoBlob;

public class cf_call_0_1 extends FVMStrategy {
	public static cf_call_0_1 instance = new cf_call_0_1();

	@Override
	// env| cf -> env'
	// Call a function with the given frame and block, 
	// set return address to the given continuation
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame cf = (ControlFrame)((StrategoBlob) arg).value();
		env.currentThread.callControlFrame(cf);

		return new StrategoBlob(env);
	}
}

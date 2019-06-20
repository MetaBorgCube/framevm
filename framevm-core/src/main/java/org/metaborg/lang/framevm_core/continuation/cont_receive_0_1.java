package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class cont_receive_0_1 extends FVMStrategy {
	public static cont_receive_0_1 instance = new cont_receive_0_1();

	@Override
	// env| cont -> (env', val)
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame cf = (ControlFrame) ((StrategoBlob) arg).value();
		
		IStrategoTerm term = cf.popReturn();
		return factory.makeTuple(new StrategoBlob(env), term);
	}
}

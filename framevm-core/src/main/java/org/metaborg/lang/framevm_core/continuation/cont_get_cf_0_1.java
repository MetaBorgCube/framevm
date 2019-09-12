package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class cont_get_cf_0_1 extends FVMStrategy {
	public static cont_get_cf_0_1 instance = new cont_get_cf_0_1();

	@Override
	// env| cont -> cf
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Continuation cont = (Continuation) ((StrategoBlob) arg).value();
		return new StrategoBlob(cont.getControlFrame());
	}
}

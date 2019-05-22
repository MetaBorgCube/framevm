package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.StackControlFrame;
import mb.nabl2.stratego.StrategoBlob;

public class cont_transfer_0_1 extends FVMStrategy {
	public static cont_transfer_0_1 instance = new cont_transfer_0_1();

	@Override
	// env| (cont, [val]) -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		ControlFrame cf = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		
		if (!(cf instanceof StackControlFrame)) return null;
		StackControlFrame frame = (StackControlFrame) cf;
		StrategoList values = (StrategoList) tuple.get(1);
				
		for (int i = values.size()-1; i >= 0; i--) {
			IStrategoTerm value = values.get(i);
			frame.push(value);
		}
		return new StrategoBlob(env);
	}
}

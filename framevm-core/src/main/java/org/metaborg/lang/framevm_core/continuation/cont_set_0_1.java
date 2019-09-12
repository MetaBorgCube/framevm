package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class cont_set_0_1 extends FVMStrategy {
	public static cont_set_0_1 instance = new cont_set_0_1();

	@Override
	// env| (cf, (contId, contIdx), cont) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();
		Continuation continuation = (Continuation) ((StrategoBlob) tuple.get(2)).value();
		
		Continuation cont = controlFrame.getContinuation(contIdx);
		if (cont == null) {
			controlFrame.setContinuation(contIdx, continuation);
		} else {
			cont.update(continuation);
		}
		return new StrategoBlob(env);
	}
}

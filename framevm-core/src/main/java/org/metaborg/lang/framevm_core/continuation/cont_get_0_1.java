package org.metaborg.lang.framevm_core.continuation;


import java.util.Arrays;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class cont_get_0_1 extends FVMStrategy {
	public static cont_get_0_1 instance = new cont_get_0_1();

	@Override
	// env| (cf, (slot, idx)) -> cont
	// Get the content of the given continuation register in the given frame
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		String contId = ((StrategoString) contTuple.get(0)).stringValue();
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();

		Continuation cont = controlFrame.getContinuation(contIdx);
		if (cont == null) {
			LOGGER.error(Arrays.toString(controlFrame.getContinuations()));
			LOGGER.error("Continuation does not exist: " + contId);
			return null;
//		TODO: Fix this
//		} else if (!cont.id.equals(contId) && cont.id.startsWith("_c")) {
//			cont.id = contId;	// Update to better name
		}
		return new StrategoBlob(cont);
	}
}

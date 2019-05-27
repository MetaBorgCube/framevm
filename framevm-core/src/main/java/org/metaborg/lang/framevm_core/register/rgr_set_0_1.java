package org.metaborg.lang.framevm_core.register;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
import mb.nabl2.stratego.StrategoBlob;

public class rgr_set_0_1 extends FVMStrategy {
	public static rgr_set_0_1 instance = new rgr_set_0_1();

	@Override
	// env| (slot, val) -> env'
	// Push the given value to the stack
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		RegisterControlFrame cf = env.currentThread.getRegisterControlFrame();
		if(cf == null) {
			io.printError("Cannot store in a stack control frame");
			return null;
		}
		
		StrategoTuple tuple = (StrategoTuple) arg;
		String slot = ((StrategoString) tuple.get(0)).stringValue();
		IStrategoTerm val = tuple.get(1);
		
		try {
			cf.set(slot, val);
			return new StrategoBlob(env);
		} catch (ArrayIndexOutOfBoundsException ex) {
			io.printError("Slot " + slot + " does not exist");
			return null;
		}
	}
}
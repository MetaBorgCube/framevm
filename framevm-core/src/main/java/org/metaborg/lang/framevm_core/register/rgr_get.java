package org.metaborg.lang.framevm_core.register;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoString;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;

public abstract class rgr_get extends FVMStrategy {
	@Override
	// env| slot -> val
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		RegisterControlFrame cf = env.currentThread.getRegisterControlFrame();
		if (cf == null) {
			io.printError("Cannot get from a stack control frame");
			return null;
		}
		String slot = ((StrategoString) arg).stringValue();
		try {
			IStrategoTerm term = cf.get(slot);
			String name;
			
			if (term instanceof StrategoAppl) {
				StrategoAppl appl = (StrategoAppl) term;
				name = appl.getName();
			} else {
				name = term.toString();
			}
			if(accepted(name)) {
				return term;			
			} else {
				io.printError(term + " is not a valid " + accepted());
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			io.printError("SEGFAULT");
			io.printError("Slot " + slot + " does not exist");
			return null;
		}
	}

	protected abstract boolean accepted(String name);
	protected abstract String accepted();
}

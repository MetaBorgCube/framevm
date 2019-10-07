package org.metaborg.lang.framevm_core.register;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoAppl;
import org.spoofax.terms.StrategoString;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.MachineThread;

public abstract class rgr_get extends FVMStrategy {
	@Override
	// env| slot -> val
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		MachineThread thread = env.currentThread;
		if (thread == null) {
			LOGGER.error("Cannot get from a stack control frame");
			return null;
		}
		String slot = ((StrategoString) arg).stringValue();
		try {
			IStrategoTerm term = thread.get(slot);
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
				LOGGER.error(term + " is not a valid " + accepted());
				return null;
			}
		} catch (ArrayIndexOutOfBoundsException ex) {
			LOGGER.error("SEGFAULT");
			LOGGER.error("Slot " + slot + " does not exist");
			return null;
		} catch (IllegalStateException ex) {
			LOGGER.error(ex.getMessage());
			return null;
		}
	}

	protected abstract boolean accepted(String name);
	protected abstract String accepted();
}

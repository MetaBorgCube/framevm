package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class cf_get_frame_0_1 extends FVMStrategy {
	public static cf_get_frame_0_1 instance = new cf_get_frame_0_1();

	@Override
	// env| cont -> frame
	// Get the data frame of the given continuation
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) arg).value();
		return new StrategoBlob(controlFrame.getCurrentFrame());
	}
}

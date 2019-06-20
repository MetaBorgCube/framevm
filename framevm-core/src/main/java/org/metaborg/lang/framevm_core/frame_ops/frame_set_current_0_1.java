package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_set_current_0_1 extends FVMStrategy {
	public static frame_set_current_0_1 instance = new frame_set_current_0_1();

	@Override
	// env| (cont, frame) -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		Frame frame = (Frame) ((StrategoBlob) tuple.get(1)).value();
		
		controlFrame.setCurrentFrame(frame);
		
		return new StrategoBlob(env);
	}
}

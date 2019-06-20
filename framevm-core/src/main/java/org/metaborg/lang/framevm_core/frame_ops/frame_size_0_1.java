package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_size_0_1 extends FVMStrategy {
	public static frame_size_0_1 instance = new frame_size_0_1();

	@Override
	// env| frame -> int
	// Get the number of slots in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Frame frame = (Frame) ((StrategoBlob) arg).value();
		
		return factory.makeInt(frame.getSlots().length);
	}
}

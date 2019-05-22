package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_new_0_1 extends FVMStrategy {
	public static frame_new_0_1 instance = new frame_new_0_1();

	@Override
	// env| int -> (env', frame)
	// create a new frame and return it
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		int size = ((StrategoInt) arg).intValue();
		Frame frame = env.newFrame(size);

		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(frame));
	}
}

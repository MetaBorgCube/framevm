package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_this_0_1 extends FVMStrategy {
	public static frame_this_0_1 instance = new frame_this_0_1();
	
	@Override
	// env -> frame
	// Get the frame id of the currently executing frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		return new StrategoBlob(env.currentThread.getControlFrame().getCurrentFrame());
	}
}

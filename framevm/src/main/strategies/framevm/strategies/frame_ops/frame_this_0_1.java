package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;

public class frame_this_0_1 extends FVMStrategy {
	public static frame_this_0_1 instance = new frame_this_0_1();
	
	@Override
	// env -> frame_id
	// Get the frame id of the currently executing frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		return factory.makeString(env.currentFrame.getId());
	}
}
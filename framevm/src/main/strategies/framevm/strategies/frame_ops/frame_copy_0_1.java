package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public class frame_copy_0_1 extends FVMStrategy {
	public static frame_copy_0_1 instance = new frame_copy_0_1();

	@Override
	// env| frame_id -> (env', frame_id)
	// Copy the given frame and return the id of the copied frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String frame_id = ((StrategoString) arg).stringValue();
		String frameId = env.newFrameFrom(env.getFrame(frame_id));
		IStrategoString val = factory.makeString(frameId);

		return factory.makeTuple(new StrategoBlob(env), val);
	}
}

package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public class frame_new_0_1 extends FVMStrategy {
	public static frame_new_0_1 instance = new frame_new_0_1();

	@Override
	// env| -> (env', frame_id)
	// create a new frame, return its id
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		int size = ((StrategoInt) arg).intValue();
		String frameId = env.newFrame(size);
		IStrategoString val = factory.makeString(frameId);

		return factory.makeTuple(new StrategoBlob(env), val);
	}
}

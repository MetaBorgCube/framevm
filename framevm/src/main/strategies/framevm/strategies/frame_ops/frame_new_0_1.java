package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_new_0_1 extends FVMStrategy {
	public static frame_new_0_1 instance = new frame_new_0_1();

	@Override
	// env| -> (env', frame)
	// create a new frame and return it
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		int size = ((StrategoInt) arg).intValue();
		Frame frame = env.newFrame(size);

		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(frame));
	}
}

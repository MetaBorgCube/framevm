package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;

import framevm.strategies.util.Environment;

public class ex_get_cx_0_1 extends FVMStrategy {
	public static ex_get_cx_0_1 instance = new ex_get_cx_0_1();

	@Override
	// env| frame_id -> continuation
	// Get the currently active exception
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm value) {
		String frame = ((StrategoString) value).stringValue();
		IStrategoTerm continuation = env.getFrame(frame).getOperandStack().getException();
		return continuation;
	}
}

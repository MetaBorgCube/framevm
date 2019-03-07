package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;

import framevm.strategies.util.Environment;

public class vm_get_cc_0_1 extends FVMStrategy {
	public static vm_get_cc_0_1 instance = new vm_get_cc_0_1();

	@Override
	// env| frame_id -> frame_id
	// Get the currently active continuation
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm value) {
		String frame = ((StrategoString) value).stringValue();
		IStrategoTerm continuation = env.getFrame(frame).getOperandStack().getContinuation();
		return continuation;
	}
}

package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import mb.nabl2.stratego.StrategoBlob;

public class ex_set_cx_0_1 extends FVMStrategy {
	public static ex_set_cx_0_1 instance = new ex_set_cx_0_1();

	@Override
	// env| (frame, continuation) -> env'
	// Set the currently active exception of the specified frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame frame = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		
		if (frame.getOperandStack() == null) frame.setExecutable();
		frame.getOperandStack().setException(tuple.get(1));
		return new StrategoBlob(env);
	}
}

package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public class next_instr_0_0 extends Strategy {
	public static next_instr_0_0 instance = new next_instr_0_0();

	@Override
	//env -> (instr, env')
	// Get the next instruction to execute or null if not found
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		Environment env = (Environment) ((StrategoBlob) current).value();
		IStrategoTerm next = env.currentFrame.getOperandStack().next();
		if(next == null) {
			return null;
		} else {
			return context.getFactory().makeTuple(next, new StrategoBlob(env));}
	}
}

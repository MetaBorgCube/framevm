package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.util.Environment;
import framevm.strategies.util.OperandStack;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class frame_scope_switch_0_1 extends FVMStrategy {
	public static frame_scope_switch_0_1 instance = new frame_scope_switch_0_1();

	@Override
	// env| (frame_id, frame_id) -> env'
	// Move the execution to a new frame and new block, but keep the return pointers
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame_from = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		Frame frame_to   = env.getFrame(((StrategoString) tuple.get(1)).stringValue());
		Block block  = env.getBlock(((StrategoString) tuple.get(2)).stringValue());

		OperandStack opstack = frame_from.getOperandStack();
		if (opstack == null) return null;
		
		frame_to.setExecutable(block, opstack.getReturnFrame());
		env.currentFrame = frame_to;
		
		return new StrategoBlob(env);
	}
}

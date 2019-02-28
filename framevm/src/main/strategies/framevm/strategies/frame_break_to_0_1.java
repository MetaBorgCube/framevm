package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.util.Environment;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class frame_break_to_0_1 extends FVMStrategy {
	public static frame_break_to_0_1 instance = new frame_break_to_0_1();

	@Override
	// env| (frame_id, lbl) -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame target = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		
		// TODO: old frame can be garbage collected
		//   -More specifically, all frames between current and target
		//env.currentFrame.markGarbage()
		
		env.currentFrame = target;
		Block block = env.getBlock(((StrategoString) tuple.get(1)).stringValue());
		env.currentFrame.getOperandStack().jump(block);
		return new StrategoBlob(env);
	}
}

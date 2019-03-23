package framevm.strategies.continuation;


import java.util.List;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;


import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import framevm.strategies.util.Continuation;
import mb.nabl2.stratego.StrategoBlob;

public class cont_call_0_1 extends FVMStrategy {
	public static cont_call_0_1 instance = new cont_call_0_1();

	@Override
	// env| (frame_id, lbl) -> env'
	// Call a function with the given frame and block, 
	// set return address to the given continuation
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String frame_id = ((StrategoString) tuple.get(0)).stringValue();
		Frame target = env.getFrame(frame_id);
		Block target_block = env.getBlock(((StrategoString) tuple.get(1)).stringValue());
		
		if (target.getOperandStack() == null) {
			io.printError("Frame " + frame_id + " not executable");
			return null;
		}
		if (!target.getId().equals("_exit") && !target.getId().equals("_catch")) {
			List<Continuation> continuations = env.currentFrame.getOperandStack().getContinuations();
			for (int i = 0; i < continuations.size(); i++) {
				if (target.getOperandStack().getContinuation(i) == null && continuations.get(i) != null) {
					io.printError("Warning: Continuation '" + continuations.get(i).value + "' not set for " + target.getId());
				}
			}
		}
		
		target.getOperandStack().jump(target_block);
		env.currentFrame = target;

		return new StrategoBlob(env);
	}
}

package framevm.strategies.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class cont_call_0_1 extends FVMStrategy {
	public static cont_call_0_1 instance = new cont_call_0_1();

	@Override
	// env| (frame_id, lbl) -> env'
	// Call a function with the given frame and block, 
	// set return address to the given continuation
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame target = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		Block target_block = env.getBlock(((StrategoString) tuple.get(1)).stringValue());
		
		if (!target.getId().equals("_exit") && !target.getId().equals("_catch")) {
			if (target.getOperandStack().getContinuation() == null) {
				io.printError("Continuation not set for " + target.getId());
				return null;
			}
			io.printError(target.getOperandStack().getException().toString());
			if (target.getOperandStack().getException() == null) {
				io.printError("Exception handler not set for " + target.getId());
				return null;
			}
		}
		
		target.getOperandStack().jump(target_block);
		env.currentFrame = target;

		return new StrategoBlob(env);
	}
}

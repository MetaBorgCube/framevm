package framevm.strategies.frame_ops;


import java.util.Arrays;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Continuation;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;

public class frame_get_cont_0_1 extends FVMStrategy {
	public static frame_get_cont_0_1 instance = new frame_get_cont_0_1();

	@Override
	// env| (frame_id, (slot, idx)) -> continuation
	// Get the content of the given continuation register in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		StrategoString frame_id = (StrategoString) tuple.get(0);
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		
		String contId = ((StrategoString) contTuple.get(0)).stringValue();
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();

		Frame frame = env.getFrame(frame_id.stringValue());
		if (frame.getOperandStack() == null) {
			io.printError("Frame " + frame_id + " is not executable");
			return null;
		}
		Continuation cont = frame.getOperandStack().getContinuation(contIdx);
		if (cont == null) {
			io.printError(Arrays.toString(frame.getOperandStack().getContinuations()));
			io.printError("Continuation does not exist: " + contId);
			return null;
		} else if (!cont.id.equals(contId)) {
			io.printError("Continuation label mismatch, found " + cont.id + " required " + contId);
			return null;
		} else {
			return cont.value;
		}
	}
}

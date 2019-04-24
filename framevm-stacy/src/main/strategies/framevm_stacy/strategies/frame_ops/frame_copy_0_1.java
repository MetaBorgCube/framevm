package framevm_stacy.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.CopyPolicy;
import framevm_stacy.strategies.util.Frame;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_copy_0_1 extends FVMStrategy {
	public static frame_copy_0_1 instance = new frame_copy_0_1();

	@Override
	// env| (frame, policy) -> (env', frame)
	// Copy the given frame and return the id of the copied frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame oldFrame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		CopyPolicy policy = CopyPolicy.valueOf(((StrategoString) tuple.get(1)).stringValue().toUpperCase());

		Frame newFrame = env.newFrameFrom(oldFrame, policy);

		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(newFrame));
	}
}

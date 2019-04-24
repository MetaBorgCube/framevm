package framevm_stacy.strategies.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.ControlFrame;
import framevm_stacy.strategies.util.CopyPolicy;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class cont_copy_0_1 extends FVMStrategy {
	public static cont_copy_0_1 instance = new cont_copy_0_1();

	@Override
	// env| (cont, policy, policy) -> (env', cont)
	// Copy the given frame and return the id of the copied frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		ControlFrame oldFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		CopyPolicy policy = CopyPolicy.valueOf(((StrategoString) tuple.get(1)).stringValue().toUpperCase());
		CopyPolicy framePolicy = CopyPolicy.valueOf(((StrategoString) tuple.get(2)).stringValue().toUpperCase());
		
		ControlFrame newFrame = env.newControlFrameFrom(oldFrame, policy, framePolicy);
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(newFrame));
	}
}

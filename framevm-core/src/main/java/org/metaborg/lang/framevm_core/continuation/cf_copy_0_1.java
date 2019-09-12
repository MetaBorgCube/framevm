package org.metaborg.lang.framevm_core.continuation;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;

public class cf_copy_0_1 extends FVMStrategy {
	public static cf_copy_0_1 instance = new cf_copy_0_1();

	@Override
	// env| (cont, policy, policy) -> (env', cont)
	// Copy the given frame and return the id of the copied frame
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		LOGGER.error("Not supported");
		return null;
//		StrategoTuple tuple = (StrategoTuple) arg;
//		ControlFrame oldFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
//		CopyPolicy policy = CopyPolicy.valueOf(((StrategoString) tuple.get(1)).stringValue().toUpperCase());
//		CopyPolicy framePolicy = CopyPolicy.valueOf(((StrategoString) tuple.get(2)).stringValue().toUpperCase());
//		
//		ControlFrame newFrame = env.newControlFrameFrom(oldFrame, policy, framePolicy);
//		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(newFrame));
	}
}

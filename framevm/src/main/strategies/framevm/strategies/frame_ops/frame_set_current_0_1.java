package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_current_0_1 extends FVMStrategy {
	public static frame_set_current_0_1 instance = new frame_set_current_0_1();

	@Override
	// env| (cont, frame) -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		Frame frame = (Frame) ((StrategoBlob) tuple.get(1)).value();
		
		controlFrame.setCurrentFrame(frame);
		
		return new StrategoBlob(env);
	}
}

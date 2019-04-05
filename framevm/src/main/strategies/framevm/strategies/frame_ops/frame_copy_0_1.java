package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_copy_0_1 extends FVMStrategy {
	public static frame_copy_0_1 instance = new frame_copy_0_1();

	@Override
	// env| frame -> (env', frame)
	// Copy the given frame and return the id of the copied frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Frame oldFrame = (Frame) ((StrategoBlob) arg).value();
		Frame newFrame = env.newFrameFrom(oldFrame);

		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(newFrame));
	}
}

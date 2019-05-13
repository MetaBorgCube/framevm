package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_size_0_1 extends FVMStrategy {
	public static frame_size_0_1 instance = new frame_size_0_1();

	@Override
	// env| frame -> int
	// Get the number of slots in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Frame frame = (Frame) ((StrategoBlob) arg).value();
		
		return factory.makeInt(frame.getSlots().length);
	}
}

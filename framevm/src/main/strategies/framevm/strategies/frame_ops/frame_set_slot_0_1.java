package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_slot_0_1 extends FVMStrategy {
	public static frame_set_slot_0_1 instance = new frame_set_slot_0_1();

	@Override
	// env| (frame, slot, val) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		IStrategoTerm value = tuple.get(2);
		int slotIdx = Integer.valueOf(((StrategoString) tuple.get(1)).stringValue());
		try {
			frame.set(slotIdx, value);
		} catch (ArrayIndexOutOfBoundsException e) {
			io.printError(frame.getId() + ": " + e.getMessage());
			return null;
		}
		return new StrategoBlob(env);
	}
}

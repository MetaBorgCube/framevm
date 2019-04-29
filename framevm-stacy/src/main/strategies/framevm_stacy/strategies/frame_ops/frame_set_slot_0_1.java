package framevm_stacy.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.Frame;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_slot_0_1 extends FVMStrategy {
	public static frame_set_slot_0_1 instance = new frame_set_slot_0_1();

	@Override
	// env| (frame, slot, val) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		int slotIdx = ((StrategoInt) tuple.get(1)).intValue();
		IStrategoTerm value = tuple.get(2);
		try {
			frame.set(slotIdx, value);
		} catch (ArrayIndexOutOfBoundsException e) {
			io.printError(frame.getId() + ": " + e.getMessage());
			return null;
		}
		return new StrategoBlob(env);
	}
}

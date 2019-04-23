package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import framevm.strategies.util.Slot;
import mb.nabl2.stratego.StrategoBlob;

public class frame_get_slot_0_1 extends FVMStrategy {
	public static frame_get_slot_0_1 instance = new frame_get_slot_0_1();

	@Override
	// env| (frame, slot) -> val
	// get the value from a slot with given id from the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		String slotId = ((StrategoString) tuple.get(1)).stringValue();

		try {
			int idx = Integer.valueOf(slotId);
			Slot slot = frame.getSlot(idx);
			if (slot.value == null) {
				io.printError("Slot " + idx + " is still empty!");
				return null;
			} else {
				return slot.value;
			}
		} catch (NumberFormatException ex) {
			io.printError("Invalid slot " + slotId + ". It probably is a continuation you are looking for");
			return null;
		} catch (IndexOutOfBoundsException e){
			io.printError(frame.getId() + ": " + e.getMessage());
			return null;
		}
	}
}

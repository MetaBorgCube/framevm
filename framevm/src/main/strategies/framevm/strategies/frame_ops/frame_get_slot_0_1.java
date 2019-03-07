package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;

public class frame_get_slot_0_1 extends FVMStrategy {
	public static frame_get_slot_0_1 instance = new frame_get_slot_0_1();

	@Override
	// env| (frame_id, slot) -> val
	// get the value from a slot with given id from the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		StrategoString frame_id = (StrategoString) tuple.get(0);
		String slotId = ((StrategoString) tuple.get(1)).stringValue();

		Frame frame = env.getFrame(frame_id.stringValue());
		try {
			int idx = Integer.valueOf(slotId);
			return frame.getSlot(idx, false).value;
		} catch (NumberFormatException ex) {
			if ("r".equals(slotId)) {
				return frame.getOperandStack().getReturnValue().value;
			} else {
				return null;
			}
		}
	}
}

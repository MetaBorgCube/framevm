package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.util.Slot;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_0_1 extends FVMStrategy {
	public static frame_set_0_1 instance = new frame_set_0_1();

	@Override
	// env| (frame_id, slot, val) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		IStrategoTerm value = tuple.get(2);
		String slotId = ((StrategoString) tuple.get(1)).stringValue();
		try {
			int slotIdx = Integer.valueOf(slotId);

			Slot slot;
			slot = frame.getSlot(slotIdx, true);
			slot.update(value);
		} catch (NumberFormatException ex) {
			if (frame.getOperandStack() == null) frame.setExecutable();
			if ("r".equals(slotId)) {
				frame.getOperandStack().setReturnValue(value);
			} else if ("c".equals(slotId)) {
				frame.getOperandStack().setContinuation(value);
			} else if ("x".equals(slotId)) {
				frame.getOperandStack().setException(value);
			} else {
				throw new IllegalArgumentException();
			}
		}
		return new StrategoBlob(env);
	}
}

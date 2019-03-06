package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Slot;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_0_1 extends FVMStrategy {
	public static frame_set_0_1 instance = new frame_set_0_1();

	@Override
	// env| (frame_id, slot, val) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		String frame_id = ((StrategoString) tuple.get(0)).stringValue();
		IStrategoTerm value = tuple.get(2);
		try {
			int slotId = Integer.valueOf(((StrategoString) tuple.get(1)).stringValue());

			Slot slot;
			slot = env.getFrame(frame_id).getSlot(slotId, true);
			slot.update(value);
		} catch (NumberFormatException ex) {
			env.getFrame(frame_id).getOperandStack().on_return(value);
		}
		return new StrategoBlob(env);
	}
}

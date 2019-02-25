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
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		StrategoString frame_id = (StrategoString) tuple.get(0);
		int slotId = Integer.valueOf(((StrategoString) tuple.get(1)).stringValue());
		IStrategoTerm value = tuple.get(2);

		Slot slot = env.getFrame(frame_id.stringValue()).getSlot(slotId, true);
		slot.update(value);
		return new StrategoBlob(env);
	}
}

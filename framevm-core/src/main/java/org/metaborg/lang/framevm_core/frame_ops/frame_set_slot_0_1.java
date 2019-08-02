package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_set_slot_0_1 extends FVMStrategy {
	public static frame_set_slot_0_1 instance = new frame_set_slot_0_1();

	@Override
	// env| (frame, slot, val) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		int slotIdx = ((StrategoInt) tuple.get(1)).intValue();
		IStrategoTerm value = tuple.get(2);
		try {
			frame.set(slotIdx, value);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error(frame.getId() + ": " + e.getMessage());
			return null;
		}
		return new StrategoBlob(env);
	}
}

package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.Slot;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_is_empty_slot_0_1 extends FVMStrategy {
	public static frame_is_empty_slot_0_1 instance = new frame_is_empty_slot_0_1();

	@Override
	// env| (frame, slot) -> val
	// Check if the slot with given id from the given frame is empty
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		int slotIdx = ((StrategoInt) tuple.get(1)).intValue();

		try {
			Slot slot = frame.getSlot(slotIdx);
			if (slot.value == null) {
				return factory.makeInt(1);
			} else {
				return factory.makeInt(0);
			}
		} catch (IndexOutOfBoundsException e){
			LOGGER.error(frame.getId() + ": " + e.getMessage());
			return null;
		}
	}
}

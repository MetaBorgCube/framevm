package framevm.strategies.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Slot;

public class frame_get_0_0 extends FVMStrategy {
	public static frame_get_0_0 instance = new frame_get_0_0();
	
	 @Override
	 // (env, frame_id, slot) -> val
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoString frame_id = (StrategoString) tuple.get(1);
		 int slotId = Integer.valueOf(((StrategoString) tuple.get(2)).stringValue());
		 
		 Slot slot = getFrame(frame_id.stringValue()).getSlot(slotId, false);
		 return slot.value;
	 }
}

package framevm.strategies.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;
import framevm.strategies.Frame;

public class frame_get_slot_0_0 extends FVMStrategy {
	public static frame_get_slot_0_0 instance = new frame_get_slot_0_0();
	
	 @Override
	 // (env, frame_id, slot) -> val
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoString frame_id = (StrategoString) tuple.get(1);
		 String slotId = ((StrategoString) tuple.get(2)).stringValue();
		 
		 Frame frame = getFrame(frame_id.stringValue());
		 try {
			int idx = Integer.valueOf(slotId);
			return frame.getSlot(idx, false).value;
		} catch (NumberFormatException ex) {
			return frame.getSlot(slotId).value;
		}
	 }
}

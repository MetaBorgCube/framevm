package framevm.strategies.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Slot;

public class frame_set_0_0 extends FVMStrategy {
	public static frame_set_0_0 instance = new frame_set_0_0();
	
	 @Override
	 // (env, (frame_id, slot, val)) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoTuple optuple = (StrategoTuple) tuple.get(1);
		 StrategoString frame_id = (StrategoString) optuple.get(0);
		 int slotId = Integer.valueOf(((StrategoString) optuple.get(1)).stringValue());
		 IStrategoTerm value = optuple.get(2);
		 
		 Slot slot = getFrame(frame_id.stringValue()).getSlot(slotId, true);
		 slot.update(value);
		 return tuple.get(0);
	 }
}

package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.util.Routine;

public class vm_call_0_0 extends FVMStrategy {
	public static vm_call_0_0 instance = new vm_call_0_0();
	
	 @Override
	 // (env, (frame_id, lbl, lbl)) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoTuple optuple = (StrategoTuple) tuple.get(1);
		 
		 Frame target = getFrame(((StrategoString) optuple.get(0)).stringValue());
		 Routine target_lbl = getRoutine(((StrategoString) optuple.get(1)).stringValue());
		 Routine return_lbl = getRoutine(((StrategoString) optuple.get(2)).stringValue());
		 
		 if (target.getOperandStack() != null) return null; 
		 target.setExecutable(target_lbl, return_lbl, currentFrame);
		 currentFrame = target;
		 
		 return tuple.get(0);
	 }
}

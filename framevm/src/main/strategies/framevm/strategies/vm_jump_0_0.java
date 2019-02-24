package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.util.Routine;

public class vm_jump_0_0 extends FVMStrategy {
	public static vm_jump_0_0 instance = new vm_jump_0_0();
	
	 @Override
	 // (env, lbl) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 String routineName = ((StrategoString) tuple.get(1)).stringValue();
		 Routine routine = getRoutine(routineName);
		 if (routine == null) {
			 context.getIOAgent().printError("Routine " + routineName + " not found!");
		 }
		 currentFrame.getOperandStack().jump(routine);
		 return tuple.get(0);
	 }
}

package framevm.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;

import framevm.strategies.util.Routine;


public class start_vm_0_0 extends FVMStrategy {
	public static start_vm_0_0 instance = new start_vm_0_0();
	
	 @Override
	 // env -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 Routine routine = routines.get("MAIN");

		 if (routine == null) {
			 context.getIOAgent().printError("No MAIN routine found!");
			 return null;
		 }

		 currentFrame.setExecutable(routine, null, null);
		 
		 context.getIOAgent().printError("FrameVM started: " + routine.getName());
		 return current;
	 }
}

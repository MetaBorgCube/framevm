package framevm.strategies;

import java.util.HashMap;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;


public class stop_vm_0_0 extends FVMStrategy {
	public static stop_vm_0_0 instance = new stop_vm_0_0();
	
	 @Override
	 // env -> string
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 String out = stdout.toString();
		 
		 currentFrame = null;
		 stdout = new StringBuilder();

		 routines = new HashMap<>();
		 heap = new HashMap<>();
		 
		 ITermFactory factory = context.getFactory();
		 return factory.makeString(out);
	 }
}
package framevm.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;


public class vm_print_0_0 extends FVMStrategy {
	public static vm_print_0_0 instance = new vm_print_0_0();
	
	 @Override
	 // (env, val) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 String out = ((StrategoString) tuple.get(1)).stringValue();
		 stdout.append(out).append('\n');
		 return tuple.get(0);
	 }
}

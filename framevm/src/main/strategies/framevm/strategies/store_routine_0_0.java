package framevm.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.util.Routine;

public class store_routine_0_0 extends FVMStrategy {
	public static store_routine_0_0 instance = new store_routine_0_0();
	
	 @Override
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoTuple routine = (StrategoTuple) tuple.get(1);
		
		 StrategoString lbl = (StrategoString) routine.get(0);
		 StrategoList instrs = (StrategoList) routine.get(1);
		
		 IStrategoTerm[] data = new IStrategoTerm[instrs.size()];
		 int idx = 0;
		 for (IStrategoTerm instr : instrs) {
			 data[idx++] = instr;
		 }
		 String name = lbl.stringValue();
		 context.getIOAgent().printError("Added routine " + name + " with " + instrs.size() + " instructions");
		 routines.put(name, new Routine(name, data));
		 return tuple.get(0);
	 }
}

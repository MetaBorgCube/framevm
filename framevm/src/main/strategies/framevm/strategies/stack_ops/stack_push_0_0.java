package framevm.strategies.stack_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;

public class stack_push_0_0 extends FVMStrategy {
	public static stack_push_0_0 instance = new stack_push_0_0();
	
	 @Override
	 // (env, val) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 currentFrame.getOperandStack().push(tuple.get(1));
		 return tuple.get(0);
	 }
}

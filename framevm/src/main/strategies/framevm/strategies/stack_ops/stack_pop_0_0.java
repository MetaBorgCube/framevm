package framevm.strategies.stack_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.OperandStack;

public class stack_pop_0_0 extends FVMStrategy {
	public static stack_pop_0_0 instance = new stack_pop_0_0();
	
	 @Override
	 // (env) -> (env', val)
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 OperandStack opStack = currentFrame.getOperandStack();
		 if (!opStack.hasNext()) {
			 context.getIOAgent().printError("SEGFAULT");
			 context.getIOAgent().printError("Cannot pop from empty stack");
			 throw new IllegalStateException("Empty Stack");
		 }
		 return new StrategoTuple(new IStrategoTerm[]{current, opStack.pop()}, null, IStrategoTerm.IMMUTABLE);
	 }
}

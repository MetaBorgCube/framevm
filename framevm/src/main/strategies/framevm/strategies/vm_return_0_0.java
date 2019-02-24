package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

public class vm_return_0_0 extends FVMStrategy {
	public static vm_return_0_0 instance = new vm_return_0_0();
	
	 @Override
	 // (env, value) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 IStrategoTerm value = tuple.get(1);
		 
		 currentFrame = currentFrame.getOperandStack().do_return(value);
		 return tuple.get(0);
	 }
}

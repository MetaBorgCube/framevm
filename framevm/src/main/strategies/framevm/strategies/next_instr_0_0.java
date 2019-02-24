package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

public class next_instr_0_0 extends FVMStrategy {
	public static next_instr_0_0 instance = new next_instr_0_0();
	
	 @Override
	  public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 IStrategoTerm env = current;		 
		 IStrategoTerm next = currentFrame.getOperandStack().next();
		 if (next == null) {
			 return null;
		 } else {
			 return new StrategoTuple(new IStrategoTerm[] {next, env}, null, IStrategoTerm.IMMUTABLE);
		 }
	  }
}

package framevm.strategies.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;

public class frame_new_0_0 extends FVMStrategy {
	public static frame_new_0_0 instance = new frame_new_0_0();
	
	 @Override
	 // env -> (env', frame_id)
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 String frameId = newFrame();
		 ITermFactory factory = context.getFactory();
		 return new StrategoTuple(new IStrategoTerm[] {current, factory.makeString(frameId)}, null, IStrategoTerm.IMMUTABLE);
	 }
}

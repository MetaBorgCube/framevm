package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;

public class frame_this_0_0 extends FVMStrategy {
	public static frame_this_0_0 instance = new frame_this_0_0();
	
	 @Override
	 // env -> frame_id
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 ITermFactory factory = context.getFactory();
		 return factory.makeString(currentFrame.id);
	 }
}

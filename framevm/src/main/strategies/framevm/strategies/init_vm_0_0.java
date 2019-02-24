package framevm.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;


public class init_vm_0_0 extends FVMStrategy {
	public static init_vm_0_0 instance = new init_vm_0_0();
	
	 @Override
	  public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 currentFrame = new Frame(newFrameID());
		 
		 context.getIOAgent().printError("FrameVM initialized");
		 return new StrategoTuple(new IStrategoTerm[]{}, null, IStrategoTerm.IMMUTABLE);
	  }
}

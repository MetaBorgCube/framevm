package framevm.strategies;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;


public class init_vm_0_0 extends Strategy {
	public static init_vm_0_0 instance = new init_vm_0_0();
	
	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		Environment env = new Environment();
		env.currentFrame = env.getFrame(env.newFrame());

		context.getIOAgent().printError("FrameVM initialized" + env.currentFrame.id + env.currentFrame.getOperandStack());
		return new StrategoBlob(env);
	}
}

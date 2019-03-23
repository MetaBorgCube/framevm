package framevm.strategies.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;


public class vm_init_0_0 extends Strategy {
	public static vm_init_0_0 instance = new vm_init_0_0();

	@Override
	// -> env
	// Set up the vm by making a new environment
	public IStrategoTerm invoke(Context context, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		int link_size = ((StrategoInt) tuple.get(0)).intValue();
		int cont_size = ((StrategoInt) tuple.get(1)).intValue();
		Environment env = new Environment(link_size, cont_size);
		env.currentFrame = env.getFrame(env.newFrame());

		context.getIOAgent().printError("FrameVM initialized " + env.currentFrame.getId());
		context.getIOAgent().printError("Register size = (" + link_size + ", " + cont_size + ")");
		return new StrategoBlob(env);
	}
}

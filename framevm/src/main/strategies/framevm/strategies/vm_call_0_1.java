package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class vm_call_0_1 extends FVMStrategy {
	public static vm_call_0_1 instance = new vm_call_0_1();

	@Override
	// env| (frame_id, lbl, lbl) -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame target = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		Block target_lbl = env.getBlock(((StrategoString) tuple.get(1)).stringValue());
		Block return_lbl = env.getBlock(((StrategoString) tuple.get(2)).stringValue());

		if (target.getOperandStack() != null) return null; 
		target.setExecutable(target_lbl, return_lbl, env.currentFrame);
		env.currentFrame = target;

		return new StrategoBlob(env);
	}
}

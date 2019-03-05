package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;


public class vm_stop_0_1 extends FVMStrategy {
	public static vm_stop_0_1 instance = new vm_stop_0_1();

	@Override
	// env -> string
	// Clear the environment for reuse (TODO: Is this still needed?)
	// Return the output that was written to 'console'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String out = env.stdout.toString();
		env.clear();

		return factory.makeString(out);
	}
}
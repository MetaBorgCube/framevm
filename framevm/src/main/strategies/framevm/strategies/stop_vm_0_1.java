package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;


public class stop_vm_0_1 extends FVMStrategy {
	public static stop_vm_0_1 instance = new stop_vm_0_1();

	@Override
	// env -> string
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String out = env.stdout.toString();
		env.clear();

		return factory.makeString(out);
	}
}
package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;

public class vm_debug_0_1 extends FVMStrategy {
	public static vm_debug_0_1 instance = new vm_debug_0_1();

	@Override
	// env| -> string
	// Return a string representation of the environment
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		return factory.makeString(env.toDotString());
	}
}

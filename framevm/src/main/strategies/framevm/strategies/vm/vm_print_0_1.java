package framevm.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;


public class vm_print_0_1 extends FVMStrategy {
	public static vm_print_0_1 instance = new vm_print_0_1();

	@Override
	// env| val -> env'
	// Print the given string to console
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String out = ((StrategoString) arg).stringValue();
		env.stdout.append(out).append('\n');
		return new StrategoBlob(env);
	}
}

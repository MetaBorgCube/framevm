package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public class vm_return_0_1 extends FVMStrategy {
	public static vm_return_0_1 instance = new vm_return_0_1();

	@Override
	// env| value -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		IStrategoTerm value = arg;

		env.currentFrame = env.currentFrame.getOperandStack().do_return(value);
		return new StrategoBlob(env);
	}
}

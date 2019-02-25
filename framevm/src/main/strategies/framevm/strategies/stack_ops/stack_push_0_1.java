package framevm.strategies.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public class stack_push_0_1 extends FVMStrategy {
	public static stack_push_0_1 instance = new stack_push_0_1();

	@Override
	// env| val -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		env.currentFrame.getOperandStack().push(arg);
		return new StrategoBlob(env);
	}
}

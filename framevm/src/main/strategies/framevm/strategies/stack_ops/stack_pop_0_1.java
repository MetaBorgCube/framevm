package framevm.strategies.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.OperandStack;
import mb.nabl2.stratego.StrategoBlob;

public class stack_pop_0_1 extends FVMStrategy {
	public static stack_pop_0_1 instance = new stack_pop_0_1();

	@Override
	// env| -> (env', val)
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		OperandStack opStack = env.currentFrame.getOperandStack();
		if (!opStack.hasNext()) {
			io.printError("SEGFAULT");
			io.printError("Cannot pop from empty stack");
			throw new IllegalStateException("Empty Stack");
		}
		return factory.makeTuple(new StrategoBlob(env),opStack.pop());
	}
}

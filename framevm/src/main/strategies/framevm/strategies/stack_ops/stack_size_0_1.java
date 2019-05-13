package framevm.strategies.stack_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.MachineState;

public class stack_size_0_1 extends FVMStrategy {
	public static stack_size_0_1 instance = new stack_size_0_1();

	@Override
	// env| -> int
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		return factory.makeInt(env.currentThread.getControlFrame().getStack().size());
	}
}

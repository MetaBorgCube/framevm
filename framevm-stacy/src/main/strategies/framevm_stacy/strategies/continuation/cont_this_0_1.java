package framevm_stacy.strategies.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class cont_this_0_1 extends FVMStrategy {
	public static cont_this_0_1 instance = new cont_this_0_1();

	@Override
	// env| (cont, (slot, idx)) -> continuation
	// Get the content of the given continuation register in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		return new StrategoBlob(env.currentThread.getControlFrame());
	}
}

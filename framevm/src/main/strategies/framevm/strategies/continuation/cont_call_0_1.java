package framevm.strategies.continuation;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.MachineState;
import framevm.strategies.FVMStrategy;
import mb.nabl2.stratego.StrategoBlob;

public class cont_call_0_1 extends FVMStrategy {
	public static cont_call_0_1 instance = new cont_call_0_1();

	@Override
	// env| cont -> env'
	// Call a function with the given frame and block, 
	// set return address to the given continuation
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame controlFrame = (ControlFrame)((StrategoBlob) arg).value();
		env.currentThread.setControlFrame(controlFrame);

		return new StrategoBlob(env);
	}
}

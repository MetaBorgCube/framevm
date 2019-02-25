package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Routine;
import mb.nabl2.stratego.StrategoBlob;


public class start_vm_0_1 extends FVMStrategy {
	public static start_vm_0_1 instance = new start_vm_0_1();

	@Override
	// env -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		Routine routine = env.getRoutine("MAIN");

		if (routine == null) {
			io.printError("No MAIN routine found!");
			return null;
		}

		env.currentFrame.setExecutable(routine, null, null);

		io.printError("FrameVM started: " + routine.getName());
		return new StrategoBlob(env);
	}
}

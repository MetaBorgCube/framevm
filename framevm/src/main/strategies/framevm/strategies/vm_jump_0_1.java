package framevm.strategies;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Routine;
import mb.nabl2.stratego.StrategoBlob;

public class vm_jump_0_1 extends FVMStrategy {
	public static vm_jump_0_1 instance = new vm_jump_0_1();

	@Override
	// env| lbl -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String routineName = ((StrategoString) arg).stringValue();
		Routine routine = env.getRoutine(routineName);
		if (routine == null) {
			io.printError("Routine " + routineName + " not found!");
		}
		env.currentFrame.getOperandStack().jump(routine);
		return new StrategoBlob(env);
	}
}

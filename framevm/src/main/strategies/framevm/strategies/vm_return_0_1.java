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
	// Return execution to the return address and -frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm value) {
		// If exit
		if (env.currentFrame.getOperandStack().getReturnFrame() == null) {
			try {
				int exitCode = Integer.valueOf(value.toString().replaceAll("IntV\\(\"(-?[0-9]+)\"\\)", "$1"));
				io.printError("Exection terminated with exit code " + exitCode);
				if (exitCode == 0) {
					io.printError("Terminated sucessfully");
				} else {
					io.printError("Terminated with non-zero exit code");
				}
			} catch (NumberFormatException ex) {
				io.printError("Exitcode must be an int, not " + value.toString());
				return null;
			}
		} else {
			env.currentFrame = env.currentFrame.getOperandStack().do_return(value);
		}

		return new StrategoBlob(env);
	}
}

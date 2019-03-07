package framevm.strategies;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;


public class vm_stop_0_1 extends FVMStrategy {
	public static vm_stop_0_1 instance = new vm_stop_0_1();
	private static final Pattern PATTERN = Pattern.compile("IntV\\(\"(\\d)\"\\)");

	@Override
	// env -> string
	// Return the output that was written to 'console'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		String exitObj = env.currentFrame.getOperandStack().getReturnValue().value.toString();
		Matcher match = PATTERN.matcher(exitObj);
		if (match.matches()) {
			int exitcode = Integer.valueOf(match.group(1));
			if (exitcode == 0) {
				io.printError("Execution terminated sucessfully");
			} else {
				io.printError("Execution terminated with errors: " + exitcode);
			}
		} else {
			return null;	// Exitcode is not an intV 
		}
		
		String out = env.stdout.toString();

		return factory.makeString(out);
	}
}
package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;


public class start_vm_0_1 extends FVMStrategy {
	public static start_vm_0_1 instance = new start_vm_0_1();

	@Override
	// env -> env'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		Block block = env.getBlock("MAIN");

		if (block == null) {
			io.printError("No MAIN block found!");
			return null;
		}

		env.currentFrame.setExecutable(block, null, null);

		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}

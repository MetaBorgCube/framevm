package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm continuation) {
		Block block = env.getBlock("MAIN");

		if (block == null) {
			io.printError("No MAIN block found!");
			return null;
		}

		env.currentFrame.setExecutable();
		env.currentFrame.getOperandStack().jump(block);
		env.currentFrame.getOperandStack().setContinuation(continuation);
		
		Frame exit = new Frame("exit");
		exit.setExecutable();
//		env.currentFrame.getOperandStack().jump(env.getBlock("_exit"));
		env.heap.put("exit", exit);

		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}

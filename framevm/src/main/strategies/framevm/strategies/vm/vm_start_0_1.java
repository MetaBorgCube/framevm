package framevm.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import framevm.strategies.util.Continuation;
import mb.nabl2.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env| (continuation, continuation) -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Block block = env.getBlock("MAIN");

		if (block == null) {
			io.printError("No MAIN block found!");
			return null;
		}

		env.currentFrame.setExecutable(2);
		env.currentFrame.getOperandStack().jump(block);
		env.currentFrame.getOperandStack().setContinuation(0, new Continuation("c", tuple.get(0)));
		env.currentFrame.getOperandStack().setContinuation(1, new Continuation("x", tuple.get(1)));
		
		Frame exit = new Frame("_exit", 0, 0);
		exit.setExecutable(0);
		env.heap.put("_exit", exit);
		
		Frame catsh = new Frame("_catch", 0, 0);
		catsh.setExecutable(0);
		env.heap.put("_catch", catsh);

		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}

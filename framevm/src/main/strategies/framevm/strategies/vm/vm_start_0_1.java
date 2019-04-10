package framevm.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import framevm.strategies.util.Continuation;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env| -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Block block = env.getBlock("MAIN");

		if (block == null) {
			io.printError("No MAIN block found!");
			return null;
		}
		
		ControlFrame controlFrame = env.currentThread.getControlFrame();
		controlFrame.jump(block);
		env.currentThread.initThread();
		
		controlFrame.setContinuation(0, new Continuation("c", new ControlFrame(0, new Frame("_exit", 0, 0), null)));
		controlFrame.setContinuation(1, new Continuation("x", new ControlFrame(0, new Frame("_catch", 0, 0), null)));
		
		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}

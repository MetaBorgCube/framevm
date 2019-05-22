package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Block;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
import org.metaborg.lang.framevm_core.util.StackControlFrame;
import mb.nabl2.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env| (lib, block) -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String blockName = ((StrategoString) tuple.get(1)).stringValue();
		String libName = ((StrategoString) tuple.get(0)).stringValue();
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			io.printError("No main block found!");
			return null;
		}
		
		ControlFrame controlFrame = env.currentThread.getControlFrame();
		controlFrame.jump(block);
		env.currentThread.initThread();
		
		switch (env.mode) {
			case REGISTER:
				controlFrame.setContinuation(0, new Continuation("c", new RegisterControlFrame(0, new Frame("_exit", 0, 0), null, "_exit")));
				controlFrame.setContinuation(1, new Continuation("x", new RegisterControlFrame(0, new Frame("_catch", 0, 0), null, "_catch")));
				break;
				
			case STACK:
			default:
				controlFrame.setContinuation(0, new Continuation("c", new StackControlFrame(0, new Frame("_exit", 0, 0), null, "_exit")));
				controlFrame.setContinuation(1, new Continuation("x", new StackControlFrame(0, new Frame("_catch", 0, 0), null, "_catch")));
		}
		
		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}
package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
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
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env| (lib, block, size) -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String libName = ((StrategoString) tuple.get(0)).stringValue();
		String blockName = ((StrategoString) tuple.get(1)).stringValue();
		int size = ((StrategoInt) tuple.get(2)).intValue();
		
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			io.printError("No main block found!");
			return null;
		}
		
		ControlFrame controlFrame = env.currentThread.getControlFrame();
		try {
			controlFrame.setSize(size);
		} catch (IllegalStateException ex) {
			io.printError(ex.getMessage());
		} catch (NegativeArraySizeException ex) {
			io.printError("No local size set for initial block " + blockName);
		}
		controlFrame.jump(block);
		env.currentThread.initThread();
		
		ControlFrame c;
		ControlFrame x;
		switch (env.mode) {
			case REGISTER:
				c = new RegisterControlFrame(0, 0, null, "_exit");
				x = new RegisterControlFrame(0, 0, null, "_catch");
				break;
				
			case STACK:
			default:
				c = new StackControlFrame(0, 1, null, "_exit");
				x = new StackControlFrame(0, 1, null, "_catch");
		}
		
		c.setCurrentFrame(new Frame("_exit", 0, 0));
		x.setCurrentFrame(new Frame("_catch", 0, 0));
		
		controlFrame.setContinuation(0, new Continuation("c", c));
		controlFrame.setContinuation(1, new Continuation("x", x));
		
		
		io.printError("FrameVM started: " + block.getName());
		return new StrategoBlob(env);
	}
}

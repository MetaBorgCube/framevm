package org.metaborg.lang.framevm_core.vm;

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
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_start_0_1 extends FVMStrategy {
	public static vm_start_0_1 instance = new vm_start_0_1();

	@Override
	// env| (lib, block, size) -> env'
	// Start the vm by setting the initial frame as executable and running the MAIN block
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String libName = ((StrategoString) tuple.get(0)).stringValue();
		String blockName = ((StrategoString) tuple.get(1)).stringValue();
		
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			LOGGER.error("No main block found!");
			return null;
		}
		
		ControlFrame controlFrame = env.currentThread.getControlFrame();
		env.currentThread.jump(block);
		env.currentThread.initThread();
		
		ControlFrame c = new ControlFrame(0, "_exit");
		ControlFrame x = new ControlFrame(0, "_catch");
				
		c.setCurrentFrame(new Frame("_exit", 0, 0));
		x.setCurrentFrame(new Frame("_catch", 0, 0));
		
		controlFrame.setContinuation(0, new Continuation(c, null, null));
		controlFrame.setContinuation(1, new Continuation(x, null, null));
		
		
		LOGGER.info("Started execution at " + block.getName());
		return new StrategoBlob(env);
	}
}

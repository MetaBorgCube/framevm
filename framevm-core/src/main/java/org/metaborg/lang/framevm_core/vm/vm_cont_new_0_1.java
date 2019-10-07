package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Block;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_cont_new_0_1 extends FVMStrategy {
	public static vm_cont_new_0_1 instance = new vm_cont_new_0_1();

	@Override
	// env| (cf, (lib, lbl)) -> (env', cont)
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		ControlFrame cf = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		
		StrategoTuple blockTuple = (StrategoTuple) tuple.get(1);
		String libName = ((StrategoString) blockTuple.get(0)).stringValue();
		String blockName = ((StrategoString) blockTuple.get(1)).stringValue();
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			LOGGER.error("Block " + blockName + " not found in library " + libName + "!");
			return null;
		}
		
		Continuation cont = new Continuation(cf, block, env.currentThread.getMemory());
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(cont));
	}
}
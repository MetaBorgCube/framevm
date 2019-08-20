package org.metaborg.lang.framevm_core.vm;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Block;
import org.metaborg.lang.framevm_core.util.MachineState;

public class vm_get_block_size_0_1 extends FVMStrategy {
	public static vm_get_block_size_0_1 instance = new vm_get_block_size_0_1();

	@Override
	// env| (lib, lbl) -> size
	// Get the size of the given block
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String libName = ((StrategoString) tuple.get(0)).stringValue();
		String blockName = ((StrategoString) tuple.get(1)).stringValue();
		Block block = env.getBlock(libName, blockName);
		if (block == null) {
			LOGGER.error("Block " + blockName + " not found in library " + libName + "!");
			return null;
		}
		int size = block.getSize();
		if (size > 0) {
			return factory.makeInt(size);
		} else {
			return null;
		}
	}
}

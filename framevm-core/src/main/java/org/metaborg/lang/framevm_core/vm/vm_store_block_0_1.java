package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class vm_store_block_0_1 extends FVMStrategy {
	public static vm_store_block_0_1 instance = new vm_store_block_0_1();

	@Override
	// env| ((lib, lbl), [instr]) -> env' 
	// Store a list of instructions as a block with given label
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		StrategoTuple blockTuple = (StrategoTuple) tuple.get(0);
		String libName = ((StrategoString) blockTuple.get(0)).stringValue();
		String blockName = ((StrategoString) blockTuple.get(1)).stringValue();
		
		StrategoList instrs = (StrategoList)   tuple.get(1);

		IStrategoTerm[] data = new IStrategoTerm[instrs.size()];
		int idx = 0;
		for (IStrategoTerm instr : instrs) {
			data[idx++] = instr;
		}
		env.putBlock(libName, blockName, data);
		LOGGER.info("Added block " + blockName + " with " + instrs.size() + " instructions for " + libName);
		return new StrategoBlob(env);
	}
}

package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class vm_store_block_0_1 extends FVMStrategy {
	public static vm_store_block_0_1 instance = new vm_store_block_0_1();

	@Override
	// env| ((lib, lbl), [instr]) -> env' 
	// Store a list of instructions as a block with given label
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		StrategoTuple blockTuple = (StrategoTuple) tuple.get(0);
		String blockName = ((StrategoString) blockTuple.get(1)).stringValue();
		String libName = ((StrategoString) blockTuple.get(0)).stringValue();
		
		StrategoList instrs = (StrategoList)   tuple.get(1);

		IStrategoTerm[] data = new IStrategoTerm[instrs.size()];
		int idx = 0;
		for (IStrategoTerm instr : instrs) {
			data[idx++] = instr;
		}
		env.putBlock(libName, blockName, data);
		io.printError("Added block " + blockName + " with " + instrs.size() + " instructions for " + libName);
		return new StrategoBlob(env);
	}
}

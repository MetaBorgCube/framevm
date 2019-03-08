package framevm.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.util.Environment;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class vm_store_block_0_1 extends FVMStrategy {
	public static vm_store_block_0_1 instance = new vm_store_block_0_1();

	@Override
	// (env, (lbl, [instr])) -> env' 
	// Store a list of instructions as a block with given label
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		StrategoString lbl  = (StrategoString) tuple.get(0);
		StrategoList instrs = (StrategoList)   tuple.get(1);

		IStrategoTerm[] data = new IStrategoTerm[instrs.size()];
		int idx = 0;
		for (IStrategoTerm instr : instrs) {
			data[idx++] = instr;
		}
		String name = lbl.stringValue();
		io.printError("Added block " + name + " with " + instrs.size() + " instructions");
		env.blocks.put(name, new Block(name, data));
		return new StrategoBlob(env);
	}
}
package framevm.strategies.vm;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import mb.nabl2.stratego.StrategoBlob;

public class vm_jump_0_1 extends FVMStrategy {
	public static vm_jump_0_1 instance = new vm_jump_0_1();

	@Override
	// env| lbl -> env'
	// Jump the execution to a different block
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String blockName = ((StrategoString) tuple.get(1)).stringValue();
		Block block = env.getBlock(blockName);
		if (block == null) {
			io.printError("Block " + blockName + " not found!");
			return null;
		}
		Frame frame = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		frame.getOperandStack().jump(block);
		return new StrategoBlob(env);
	}
}

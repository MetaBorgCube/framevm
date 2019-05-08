package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.Block;
import framevm_stacy.strategies.util.ControlFrame;
import framevm_stacy.strategies.util.Frame;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;


public class vm_cont_new_0_1 extends FVMStrategy {
	public static vm_cont_new_0_1 instance = new vm_cont_new_0_1();

	@Override
	// env| (frame, (lib, lbl), size) -> (env', cont)
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		
		StrategoTuple blockTuple = (StrategoTuple) tuple.get(1);
		String libName = ((StrategoString) blockTuple.get(0)).stringValue();
		String blockName = ((StrategoString) blockTuple.get(1)).stringValue();
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			io.printError("Block " + blockName + " not found in library " + libName + "!");
			return null;
		}
		
		int contSize = ((StrategoInt) tuple.get(2)).intValue();
		
		ControlFrame cont = env.newControlFrame(contSize, block);
		cont.setCurrentFrame(frame);
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(cont));
	}
}

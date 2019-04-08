package framevm.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Block;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.Frame;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;


public class vm_cont_new_0_1 extends FVMStrategy {
	public static vm_cont_new_0_1 instance = new vm_cont_new_0_1();

	@Override
	// env| (frame, lbl, size) -> (env', cont)
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		Block block = env.getBlock(((StrategoString) tuple.get(1)).stringValue());
		int contSize = ((StrategoInt) tuple.get(2)).intValue();
		
		ControlFrame cont = new ControlFrame(contSize, block);
		cont.setCurrentFrame(frame);
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(cont));
	}
}

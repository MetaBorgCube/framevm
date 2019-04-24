package framevm_stacy.strategies.continuation;


import java.util.Arrays;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.Continuation;
import framevm_stacy.strategies.util.ControlFrame;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class cont_get_0_1 extends FVMStrategy {
	public static cont_get_0_1 instance = new cont_get_0_1();

	@Override
	// env| (cont, (slot, idx)) -> cont
	// Get the content of the given continuation register in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		String contId = ((StrategoString) contTuple.get(0)).stringValue();
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();

		Continuation cont = controlFrame.getContinuation(contIdx);
		if (cont == null) {
			io.printError(Arrays.toString(controlFrame.getContinuations()));
			io.printError("Continuation does not exist: " + contId);
			return null;
		} else if (!cont.id.equals(contId)) {
			if (cont.id.startsWith("_c")) {
				cont.id = contId;	// Update to better name
			} else if (!contId.startsWith("_c")) {
				io.printError("Continuation label mismatch, found " + cont.id + " required " + contId);
				return null;
			}
		}
		return new StrategoBlob(cont.value());
	}
}

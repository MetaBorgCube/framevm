package framevm_stacy.strategies.continuation;


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

public class cont_set_0_1 extends FVMStrategy {
	public static cont_set_0_1 instance = new cont_set_0_1();

	@Override
	// env| (cont, (contId, contIdx), cont) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		ControlFrame controlFrame = (ControlFrame) ((StrategoBlob) tuple.get(0)).value();
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		String contId = ((StrategoString) contTuple.get(0)).stringValue();
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();
		ControlFrame continuationFrame = (ControlFrame) ((StrategoBlob) tuple.get(2)).value();
					
		if ("r".equals(contId)) {
			io.printError("Slot r can never be a continuation slot");
			return null;
		} else {
			
			Continuation cont = controlFrame.getContinuation(contIdx);
			if (cont == null) {
				controlFrame.setContinuation(contIdx, new Continuation(contId, continuationFrame));
			} else if (!cont.id.equals(contId)) {
				if (cont.id.startsWith("c")) {
					cont.id = contId;	// Update to better name
					cont.update(continuationFrame);
				} else {
					// Ignore less descriptive name
					cont.update(continuationFrame);
				}
			} else {
				cont.update(continuationFrame);
			}
		}
		return new StrategoBlob(env);
	}
}

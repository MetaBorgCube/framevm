package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Continuation;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import mb.nabl2.stratego.StrategoBlob;

public class frame_set_cont_0_1 extends FVMStrategy {
	public static frame_set_cont_0_1 instance = new frame_set_cont_0_1();

	@Override
	// env| (frame_id, (contId, contIdx), cont) -> env'
	// Set the value in the given slot of the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		
		Frame frame = env.getFrame(((StrategoString) tuple.get(0)).stringValue());
		IStrategoTerm value = tuple.get(2);
		
		StrategoTuple contTuple = (StrategoTuple) tuple.get(1);
		String contId = ((StrategoString) contTuple.get(0)).stringValue();
		int contIdx = ((StrategoInt) contTuple.get(1)).intValue();
		
		if (frame.getOperandStack() == null) frame.setExecutable(env.getContSize());
		if ("r".equals(contId)) {
			io.printError("Slot r can never be a continuation slot");
			return null;
		} else {
			Continuation cont = frame.getOperandStack().getContinuation(contIdx);
			if (cont == null) {
				frame.getOperandStack().setContinuation(contIdx, new Continuation(contId, value));
			} else if (!cont.id.equals(contId)) {
				if (cont.id.startsWith("c")) {
					cont.id = contId;	// Update to better name
					cont.update(value);
				} else if (contId.startsWith("c")) {
					// Ignore less descriptive name
					cont.update(value);
				} else {
					io.printError("ID mismatch, expected " + contId + " but found " + cont.id);
					return null;
				}
			} else {
				cont.update(value);
			}
		}
		return new StrategoBlob(env);
	}
}

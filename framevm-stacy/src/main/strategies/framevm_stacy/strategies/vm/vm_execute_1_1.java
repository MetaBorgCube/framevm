package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm_stacy.strategies.util.MachineState;
import framevm_stacy.strategies.util.MachineThread;
import mb.nabl2.stratego.StrategoBlob;


public class vm_execute_1_1 extends Strategy {
	public static vm_execute_1_1 instance = new vm_execute_1_1();

	@Override
	// eval|env| -> env'
	public IStrategoTerm invoke(Context context, IStrategoTerm current, Strategy eval, IStrategoTerm arg) {
		MachineState env = (MachineState) ((StrategoBlob) arg).value();
		
		IStrategoTerm envBlob = new StrategoBlob(env);
		for (MachineThread thread = env.getNextThread(); thread.isRunning(); thread=env.getNextThread()) {
			IStrategoTerm result = thread.evalNext(context, eval, (StrategoBlob) envBlob);
			if (result == null) break;
			envBlob = result;
		}
		return envBlob;
	}
}

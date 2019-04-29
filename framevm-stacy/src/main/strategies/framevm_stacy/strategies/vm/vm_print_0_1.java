package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;


public class vm_print_0_1 extends FVMStrategy {
	public static vm_print_0_1 instance = new vm_print_0_1();

	@Override
	// env| val -> env'
	// Print the given string to console
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String out;
		if (arg instanceof StrategoInt) {
			out = String.valueOf(((StrategoInt) arg).intValue());
		} else if (arg instanceof StrategoBlob) {
			out = ((StrategoBlob) arg).value().toString();
		} else if (arg instanceof StrategoTuple) {
			StrategoTuple tuple = (StrategoTuple) arg;
			String frame = ((StrategoBlob) tuple.get(0)).value().toString();
			String lbl = ((StrategoString) tuple.get(1)).stringValue();
			out = "Closure(" + frame + ", " + lbl + ")";
		} else {
			out = arg.toString();
		}
		env.stdout.append(out).append('\n');
		return factory.makeTuple(new StrategoBlob(env), factory.makeString(out));
	}
}

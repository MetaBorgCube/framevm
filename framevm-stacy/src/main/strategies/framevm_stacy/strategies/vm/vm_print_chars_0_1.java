package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoList;

import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;


public class vm_print_chars_0_1 extends FVMStrategy {
	public static vm_print_chars_0_1 instance = new vm_print_chars_0_1();

	@Override
	// env| [int] -> env'
	// Print the given list of characters to the console
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoList list = (StrategoList) arg;
		String out = "";
		for (IStrategoTerm term : list) {
			char c = (char) ((StrategoInt) term).intValue();
			env.stdout.append(c);
			out += c;
		}
		return factory.makeTuple(new StrategoBlob(env), factory.makeString(out));
	}
}

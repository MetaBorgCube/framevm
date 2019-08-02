package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoList;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_print_chars_0_1 extends FVMStrategy {
	public static vm_print_chars_0_1 instance = new vm_print_chars_0_1();

	@Override
	// env| [int] -> env'
	// Print the given list of characters to the console
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
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

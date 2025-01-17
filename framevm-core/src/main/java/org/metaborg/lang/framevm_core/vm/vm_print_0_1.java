package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_print_0_1 extends FVMStrategy {
	public static vm_print_0_1 instance = new vm_print_0_1();

	@Override
	// env| val -> env'
	// Print the given string to console
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String out;
		if (arg instanceof StrategoBlob) {
			out = ((StrategoBlob) arg).value().toString();
		} else if (arg instanceof StrategoTuple) {
			StrategoTuple tuple = (StrategoTuple) arg;
			String frame = ((StrategoBlob) tuple.get(0)).value().toString();
			String lbl = ((StrategoString) tuple.get(1)).stringValue();
			out = "Closure(" + frame + ", " + lbl + ")";
		} else if (arg instanceof StrategoString) {
			out = ((StrategoString) arg).stringValue();
		} else {
			out = arg.toString();
		}
		env.stdout.append(out).append('\n');
		return factory.makeTuple(new StrategoBlob(env), factory.makeString(out));
	}
}

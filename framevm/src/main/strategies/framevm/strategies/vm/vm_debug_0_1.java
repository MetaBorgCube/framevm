package framevm.strategies.vm;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import framevm.strategies.FVMStrategy;
import framevm.strategies.dot.DotMachineStateFactory;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

public class vm_debug_0_1 extends FVMStrategy {
	public static vm_debug_0_1 instance = new vm_debug_0_1();

	@Override
	// env| -> env'
	// Return a string representation of the environment
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String debug = DotMachineStateFactory.build(env);
		env.setDebug(debug);
		return new StrategoBlob(env);
	}
}

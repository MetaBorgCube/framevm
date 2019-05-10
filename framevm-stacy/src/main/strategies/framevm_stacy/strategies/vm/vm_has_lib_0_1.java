package framevm_stacy.strategies.vm;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import framevm_stacy.strategies.FVMStrategy;
import framevm_stacy.strategies.util.MachineState;

public class vm_has_lib_0_1 extends FVMStrategy {
	public static vm_has_lib_0_1 instance = new vm_has_lib_0_1();

	@Override
	// env| lib
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		String libName = ((StrategoString) arg).stringValue();
		
		if (env.blocks.containsKey(libName)) {
			return arg;
		} else {
			return null;
		}
	}
}

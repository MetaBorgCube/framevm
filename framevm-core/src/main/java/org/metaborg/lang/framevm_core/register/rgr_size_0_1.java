package org.metaborg.lang.framevm_core.register;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;

public class rgr_size_0_1 extends FVMStrategy {
	public static rgr_size_0_1 instance = new rgr_size_0_1();

	@Override
	// env| -> int
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		RegisterControlFrame cf = env.currentThread.getRegisterControlFrame();
		if (cf == null) {
			io.printError("Cannot get stack size for a register frame");
			return null;
		}

		return factory.makeInt(cf.getLocals().length);
	}
}

package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_cf_new_0_1 extends FVMStrategy {
	public static vm_cf_new_0_1 instance = new vm_cf_new_0_1();

	@Override
	// env| (frame, conts) -> (env', cf)
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();

		ControlFrame cf = env.newControlFrame(((StrategoInt) tuple.get(1)).intValue());
		cf.setCurrentFrame(frame);
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(cf));
	}
}
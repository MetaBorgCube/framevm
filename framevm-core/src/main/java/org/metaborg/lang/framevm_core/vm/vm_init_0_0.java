package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.MachineThread;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_init_0_0 extends Strategy {
	public static vm_init_0_0 instance = new vm_init_0_0();

	@Override
	// (links, conts, slots) -> env
	// Set up the vm by making a new environment
	public IStrategoTerm invoke(Context context, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		int link_size = ((StrategoInt) tuple.get(0)).intValue();
		int cont_size = ((StrategoInt) tuple.get(1)).intValue();
		int slot_size = ((StrategoInt) tuple.get(2)).intValue();
		int register_size = ((StrategoInt) tuple.get(3)).intValue();
		MachineState env = new MachineState(link_size, cont_size);
		ControlFrame controlFrame = env.newControlFrame(env.newFrame(slot_size));
		env.addThread(new MachineThread(controlFrame, env, register_size));
		

		FVMStrategy.LOGGER.info("Initialized " + controlFrame.getCurrentFrame().getId() + " (" + slot_size + ")");
		FVMStrategy.LOGGER.info("Link registers = " + link_size + " Registers = " + register_size);
		return new StrategoBlob(env);
	}
}

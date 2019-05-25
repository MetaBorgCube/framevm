package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.MachineThread;
import org.metaborg.lang.framevm_core.util.VMMode;
import mb.nabl2.stratego.StrategoBlob;


public class vm_init_stc_0_0 extends Strategy {
	public static vm_init_stc_0_0 instance = new vm_init_stc_0_0();

	@Override
	// (links, conts, (slots, stack)) -> env
	// Set up the vm by making a new environment
	public IStrategoTerm invoke(Context context, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		int link_size = ((StrategoInt) tuple.get(0)).intValue();
		int cont_size = ((StrategoInt) tuple.get(1)).intValue();
		
		StrategoTuple sizes = (StrategoTuple) tuple.get(2);
		int slot_size = ((StrategoInt) sizes.get(0)).intValue();
		int stack_size = ((StrategoInt) sizes.get(1)).intValue();
		
		MachineState env = new MachineState(link_size, VMMode.STACK);
		ControlFrame controlFrame = env.newStackControlFrame(cont_size, stack_size, null);
		controlFrame.setCurrentFrame(env.newFrame(slot_size));
		env.addThread(new MachineThread(controlFrame, env));
		

		context.getIOAgent().printError("FrameVM initialized " + controlFrame.getCurrentFrame().getId() + " (" + slot_size + ")");
		context.getIOAgent().printError("Link registers = " + link_size);
		return new StrategoBlob(env);
	}
}

package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Block;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;


public class vm_cont_new_0_1 extends FVMStrategy {
	public static vm_cont_new_0_1 instance = new vm_cont_new_0_1();

	@Override
	// env| (frame, (lib, lbl), size) -> (env', cont)
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;

		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		
		StrategoTuple blockTuple = (StrategoTuple) tuple.get(1);
		String libName = ((StrategoString) blockTuple.get(0)).stringValue();
		String blockName = ((StrategoString) blockTuple.get(1)).stringValue();
		Block block = env.getBlock(libName, blockName);
		
		if (block == null) {
			LOGGER.error("Block " + blockName + " not found in library " + libName + "!");
			return null;
		}
		
		ControlFrame cont;
		switch (env.mode) {
			case REGISTER: cont = makeRegister(env, tuple.get(2), block); break;
			case STACK:    
			default:       cont = makeStack(env, tuple.get(2), block); break;
		}
		cont.setCurrentFrame(frame);
		return factory.makeTuple(new StrategoBlob(env), new StrategoBlob(cont));
	}

	private ControlFrame makeRegister(MachineState env, IStrategoTerm size, Block block) {
		StrategoTuple tuple = (StrategoTuple) size;
		int contSize = ((StrategoInt) tuple.get(0)).intValue();
		int localSize = ((StrategoInt) tuple.get(1)).intValue();
		return env.newRegisterControlFrame(contSize, localSize, block);
	}

	public ControlFrame makeStack(MachineState env, IStrategoTerm size, Block block) {
		StrategoTuple tuple = (StrategoTuple) size;
		int contSize = ((StrategoInt) tuple.get(0)).intValue();
		int stackSize = ((StrategoInt) tuple.get(1)).intValue();
		return env.newStackControlFrame(contSize, stackSize, block);
	}
}

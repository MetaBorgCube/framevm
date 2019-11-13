package org.metaborg.lang.framevm_core.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import java.lang.ref.WeakReference;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.MachineState;


public class vm_force_gc_0_1 extends FVMStrategy {
	public static vm_force_gc_0_1 instance = new vm_force_gc_0_1();

	@Override
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		LOGGER.info("Starting garbage collection");
		
		Object obj = new Object();
		WeakReference<Object> ref = new WeakReference<>(obj);
		obj = null;
		while(ref.get() != null) {
			System.gc();
		}
	     
		LOGGER.info("Completed garbage collection");
		return arg;
	}
}

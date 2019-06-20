package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_link_0_1 extends FVMStrategy {
	public static frame_link_0_1 instance = new frame_link_0_1();
	
	@Override
	// (env, (frame, frame, (lbl, idx))) -> env'
	// Link the two given frames using a link with the given label name
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame frame_from = (Frame) ((StrategoBlob) tuple.get(0)).value();
		Frame frame_to   = (Frame) ((StrategoBlob) tuple.get(1)).value();
		StrategoTuple linkTuple = (StrategoTuple) tuple.get(2);
		String link_lbl = ((StrategoString) linkTuple.get(0)).stringValue();
		int link_idx = ((StrategoInt) linkTuple.get(1)).intValue();
		 
		if (frame_from.getLink(link_idx) != null) {
			io.printError("Link " + link_lbl + " already exists");
			return null;
		}
		frame_from.link(link_idx, link_lbl, frame_to);
		 
		return new StrategoBlob(env);
	}
}

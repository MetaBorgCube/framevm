package org.metaborg.lang.framevm_core.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.Link;
import org.metaborg.lang.framevm_core.util.MachineState;
import mb.nabl2.terms.stratego.StrategoBlob;

public class frame_get_link_0_1 extends FVMStrategy {
	public static frame_get_link_0_1 instance = new frame_get_link_0_1();

	@Override
	// env| (frame, (linkid, idx)) -> frame
	// Get the target of the given link in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		Frame frame = (Frame) ((StrategoBlob) tuple.get(0)).value();
		StrategoTuple linkTuple = (StrategoTuple) tuple.get(1);
		
		String linkId = ((StrategoString) linkTuple.get(0)).stringValue();
		int linkIdx = ((StrategoInt) linkTuple.get(1)).intValue();

		Link link = frame.getLink(linkIdx);
		if (link == null) {
			io.printError("A link with index " + linkIdx + " does not exist");
			return null;
		}
		if (!link.linkId.equals(linkId) && link.linkId.startsWith("_l")) {
			link.linkId = linkId;	// Update to better name
		}
		return new StrategoBlob(link.target);
	}
}

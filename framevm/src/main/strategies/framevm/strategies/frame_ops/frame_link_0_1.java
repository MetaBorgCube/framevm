package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;

import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import mb.nabl2.stratego.StrategoBlob;

public class frame_link_0_1 extends FVMStrategy {
	public static frame_link_0_1 instance = new frame_link_0_1();
	
	@Override
	// (env, (frame_id, frame_id, (lbl, idx))) -> env'
	// Link the two given frames using a link with the given label name
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		String frame_from = ((StrategoString) tuple.get(0)).stringValue();
		String frame_to   = ((StrategoString) tuple.get(1)).stringValue();
		StrategoTuple linkTuple = (StrategoTuple) tuple.get(2);
		String link_lbl = ((StrategoString) linkTuple.get(0)).stringValue();
		int link_idx = ((StrategoInt) linkTuple.get(1)).intValue();
		 
		Frame frame = env.getFrame(frame_from);
		if (frame.getLink(link_idx) != null) {
			io.printError("Link " + link_lbl + " already exists");
			return null;
		}
		frame.link(link_idx, link_lbl, env.getFrame(frame_to));
		 
		return new StrategoBlob(env);
	}
}

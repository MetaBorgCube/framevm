package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoInt;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Frame;
import framevm.strategies.util.Link;
import framevm.strategies.util.MachineState;
import mb.nabl2.stratego.StrategoBlob;

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
		if (!link.linkId.equals(linkId)) {
			if (link.linkId.startsWith("l")) {
				link.linkId = linkId;	// Update to better name
			} else if (!linkId.startsWith("l")) {
				io.printError("Link lable mismatch, requested " + linkId + ", but foud " + link.linkId);
				return null;
			}
		}
		return new StrategoBlob(link.target);
	}
}

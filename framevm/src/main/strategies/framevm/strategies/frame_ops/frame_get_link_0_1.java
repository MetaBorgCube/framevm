package framevm.strategies.frame_ops;


import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import framevm.strategies.FVMStrategy;
import framevm.strategies.util.Environment;
import framevm.strategies.util.Frame;
import framevm.strategies.util.Link;

public class frame_get_link_0_1 extends FVMStrategy {
	public static frame_get_link_0_1 instance = new frame_get_link_0_1();

	@Override
	// env| (frame_id, link) -> frame_id
	// Get the target of the given link in the given frame
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg) {
		StrategoTuple tuple = (StrategoTuple) arg;
		StrategoString frame_id = (StrategoString) tuple.get(0);
		String linkId = ((StrategoString) tuple.get(1)).stringValue();

		Frame frame = env.getFrame(frame_id.stringValue());
		Link link = frame.getLink(linkId);
		if (link == null) return null;
		Frame target = link.target;
		return factory.makeString(target.getId());
	}
}

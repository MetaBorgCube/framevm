package framevm.strategies.frame_ops;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

import framevm.strategies.FVMStrategy;
import framevm.strategies.Frame;
import framevm.strategies.util.Link;

public class frame_get_link_0_0 extends FVMStrategy {
	public static frame_get_link_0_0 instance = new frame_get_link_0_0();
	
	 @Override
	 // (env, frame_id, link) -> frame_id
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoString frame_id = (StrategoString) tuple.get(1);
		 String linkId = ((StrategoString) tuple.get(2)).stringValue();
		 
		 Frame frame = getFrame(frame_id.stringValue());
		 Link link = frame.getLink(linkId);
		 if (link == null) return null;
		 Frame target = link.target;
		 return context.getFactory().makeString(target.id);
	 }
}

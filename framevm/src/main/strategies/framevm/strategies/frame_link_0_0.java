package framevm.strategies;


import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.spoofax.terms.StrategoTuple;
import org.strategoxt.lang.Context;

public class frame_link_0_0 extends FVMStrategy {
	public static frame_link_0_0 instance = new frame_link_0_0();
	
	 @Override
	 // (env, (frame_id, frame_id, lbl)) -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current) {
		 StrategoTuple tuple = (StrategoTuple) current;
		 StrategoTuple optuple = (StrategoTuple) tuple.get(1);
		 String frame_from = ((StrategoString) optuple.get(0)).stringValue();
		 String frame_to   = ((StrategoString) optuple.get(1)).stringValue();
		 String link_lbl   = ((StrategoString) optuple.get(2)).stringValue();
		 
		 Frame frame = getFrame(frame_from);
		 frame.link(link_lbl, getFrame(frame_to));
		 return tuple.get(0);
	 }
}

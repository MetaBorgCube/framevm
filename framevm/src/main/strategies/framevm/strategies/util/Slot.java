package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Slot {
	public IStrategoTerm value;
	public boolean isFramePointer;
	
	public Slot(IStrategoTerm term) {
		this.value = term;
		this.isFramePointer = false;
	}

	public IStrategoTerm update(IStrategoTerm term) {
		// TODO filter out frame pointers
		this.value = term;
		return term;
	}
}

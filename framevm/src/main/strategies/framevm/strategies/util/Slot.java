package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Slot {
	public IStrategoTerm value;
	
	public Slot(IStrategoTerm term) {
		this.value = term;
	}

	public IStrategoTerm update(IStrategoTerm term) {
		// TODO filter out frame pointers
		this.value = term;
		return term;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}

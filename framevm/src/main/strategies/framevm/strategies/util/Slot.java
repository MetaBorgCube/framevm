package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.Frame;

/**
 * A Slot in a {@link Frame}
 */
public class Slot {
	public IStrategoTerm value;
	
	/**
	 * Constructor for a slot containing the given term.
	 * 
	 * @param term
	 * 		The term to store in this slot
	 */
	public Slot(IStrategoTerm term) {
		this.value = term;
	}

	/**
	 * Update the value stored in this slot.
	 * 
	 * @param term
	 * 		The term to store
	 */
	public void update(IStrategoTerm term) {
		this.value = term;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}

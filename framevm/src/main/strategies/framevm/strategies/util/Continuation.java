package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Continuation {

	public String id;
	public IStrategoTerm value;

	public Continuation(String id, IStrategoTerm value) {
		this.id = id;
		this.value = value;
	}
	
	public IStrategoTerm update(IStrategoTerm newVal) {
		IStrategoTerm oldVal = this.value;
		this.value = newVal;
		return oldVal;
	}
}

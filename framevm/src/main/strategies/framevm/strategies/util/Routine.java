package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class Routine {
	private String name;
	private IStrategoTerm[] body;
	
	public Routine(String name, IStrategoTerm[] body) {
		this.name = name;
		this.body = body;
	}

	public String getName() {
		return name;
	}
	
	public IStrategoTerm getInstr(int idx) {
		if (idx >= body.length) return null;
		return body[idx];
	}
}
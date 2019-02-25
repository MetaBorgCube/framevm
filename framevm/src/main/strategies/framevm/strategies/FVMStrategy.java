package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

public abstract class FVMStrategy extends Strategy {
	@Override
	 // env| args -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm env) {
		Environment environment = (Environment) ((StrategoBlob) env).value();
		
		return this.invoke(context.getIOAgent(), context.getFactory(), environment, current);
	}

	protected abstract IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg);
}

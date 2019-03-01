package framevm.strategies;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;

/**
 * Superclass of most external strategies.
 * Takes care of the environment passing
 */
public abstract class FVMStrategy extends Strategy {
	@Override
	 // env| args -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm env) {
		Environment environment = (Environment) ((StrategoBlob) env).value();
		
		//TODO: The environment is now passed around by reference, 
		// making that a pop in a failed branch in Stratego still propagates the pop
		// We might want to make this use a deep copy instead
		return this.invoke(context.getIOAgent(), context.getFactory(), environment, current);
	}

	/**
	 * Execute the strategy
	 * 
	 * @param io
	 * 		The {@link IOAgent} to use
	 * @param factory
	 * 		The {@link ITermFactory} to use
	 * @param env
	 * 		The {@link Environment} to use
	 * @param arg
	 * 		The arguments of the strategy
	 * @return
	 * 		The values returned by this strategy
	 */
	protected abstract IStrategoTerm invoke(IOAgent io, ITermFactory factory, Environment env, IStrategoTerm arg);
}

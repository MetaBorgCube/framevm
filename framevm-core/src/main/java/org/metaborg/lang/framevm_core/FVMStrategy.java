package org.metaborg.lang.framevm_core;

import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import mb.nabl2.terms.stratego.StrategoBlob;

/**
 * Superclass of most external strategies.
 * Takes care of the environment passing
 */
public abstract class FVMStrategy extends Strategy {
	public static final ILogger LOGGER = LoggerUtils.logger("FrameVM");
	
	@Override
	 // env| args -> env'
	 public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm env) {
		MachineState environment = ((MachineState) ((StrategoBlob) env).value());
		
		//TODO: The environment is now passed around by reference, 
		// making that a pop in a failed branch in Stratego still propagates the pop
		// We might want to make this use a deep copy instead
		try {
			return this.invoke(context.getFactory(), environment, current);
		} catch (Exception ex) {
			LOGGER.error("Uncaught " + ex.getClass().getName() + " '" + ex.getMessage() + "'");
			
			StackTraceElement[] trace = ex.getStackTrace();
			for (int i = 0; i < 7; i++) {
				LOGGER.error(trace[i].toString());
			}
			//TODO: Call exception continuation of current frame
		}
		return null;
	}

	/**
	 * Execute the strategy
	 * 
	 * @param factory
	 * 		The {@link ITermFactory} to use
	 * @param environment
	 * 		The {@link MachineState} to use
	 * @param arg
	 * 		The arguments of the strategy
	 * @return
	 * 		The values returned by this strategy
	 */
	protected abstract IStrategoTerm invoke(ITermFactory factory, MachineState environment, IStrategoTerm arg);
	
}

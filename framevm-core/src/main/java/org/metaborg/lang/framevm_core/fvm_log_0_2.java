package org.metaborg.lang.framevm_core;

import org.metaborg.util.log.ILogger;
import org.metaborg.util.log.LoggerUtils;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

public class fvm_log_0_2 extends Strategy {
	private static final ILogger LOGGER = LoggerUtils.logger("FrameVM");
	public static fvm_log_0_2 instance = new fvm_log_0_2();

	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, IStrategoTerm lvl, IStrategoTerm msg) {
		String level   = ((StrategoString) lvl).stringValue();
		String message = ((StrategoString) msg).stringValue();
		
		switch (level) {
		  case "DEBUG": LOGGER.debug(message); break;
		  case "INFO":  LOGGER.info(message);  break;
		  case "WARN":  LOGGER.warn(message);  break;
		  case "ERROR": LOGGER.error(message); break;
		  default: return null;
		}
		return current;
	}
}

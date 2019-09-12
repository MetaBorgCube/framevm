package org.metaborg.lang.framevm_core.vm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.MachineThread;


public class vm_stop_0_1 extends FVMStrategy {
	public static vm_stop_0_1 instance = new vm_stop_0_1();
	private static final Pattern PATTERN = Pattern.compile("IntV\\((\\d)\\)");

	@Override
	// env -> string
	// Return the output that was written to 'console'
	protected IStrategoTerm invoke(ITermFactory factory, MachineState env, IStrategoTerm arg) {
		ControlFrame controlFrame = env.currentThread.getControlFrame();
		if (controlFrame.getId().equals("_exit") || controlFrame.getId().equals("_catch")) {
	
			IStrategoTerm returnVal = controlFrame.popReturn();
			
			if (returnVal != null) {
				String exitObj = returnVal.toString();
				if (controlFrame.getId().equals("_exit")) {
					Matcher match = PATTERN.matcher(exitObj);
					if (match.matches()) {
						int exitcode = Integer.valueOf(match.group(1));
						if (exitcode == 0) {
							LOGGER.info("Execution terminated sucessfully");
						} else {
							LOGGER.error("Execution terminated with errors: " + exitcode);
						}
					} else {
						return null;	// Exitcode is not an intV 
					}
				} else if (controlFrame.getId().equals("_catch")) {
					LOGGER.error("Uncought exception: " + exitObj);
				}
			} else {
				LOGGER.error("Execution terminated in terminal state without exitcode");
				return factory.makeString("FAIL");
			}
			if (env.debug.length() > 0) {
				LOGGER.info("Printing debug info, all output is discarded");
				return factory.makeString(env.debug.trim());
			} else {
				String out = env.stdout.toString();
				if (out.endsWith("\n")) {
					out = out.substring(0, out.length() - 1);
				}
				return factory.makeString(out);
			}
		} else {
			MachineThread thread = env.currentThread;
			if (thread.getBlock() == null) {
				LOGGER.error("You somehow got a nullpointer in your program counter");
				return factory.makeString("FAIL");
			}
			String instr = thread.getBlock().getInstr(Math.max(0, thread.getInstr_count() - 1)).toString();
			if (instr.contains("_DebugKill")) {
				LOGGER.info("Printing debug info, all output is discarded");
				return factory.makeString(env.debug.trim());
			} else {
				LOGGER.error("Execution terminated in non-terminal state");
				return factory.makeString("FAIL");
			}
		}
	}
}
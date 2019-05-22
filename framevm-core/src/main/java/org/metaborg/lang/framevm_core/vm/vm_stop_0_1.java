package org.metaborg.lang.framevm_core.vm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.library.IOAgent;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.interpreter.terms.ITermFactory;

import org.metaborg.lang.framevm_core.FVMStrategy;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.MachineState;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
import org.metaborg.lang.framevm_core.util.StackControlFrame;


public class vm_stop_0_1 extends FVMStrategy {
	public static vm_stop_0_1 instance = new vm_stop_0_1();
	private static final Pattern PATTERN = Pattern.compile("IntV\\((\\d)\\)");

	@Override
	// env -> string
	// Return the output that was written to 'console'
	protected IStrategoTerm invoke(IOAgent io, ITermFactory factory, MachineState env, IStrategoTerm arg) {
		Frame currentFrame = env.currentThread.getControlFrame().getCurrentFrame();
		if (currentFrame.getId().equals("_exit") || currentFrame.getId().equals("_catch")) {
			
			ControlFrame controlFrame = env.currentThread.getControlFrame();
			IStrategoTerm returnVal;
			if (controlFrame instanceof StackControlFrame) {
				returnVal = ((StackControlFrame) controlFrame).pop();
			} else if (controlFrame instanceof RegisterControlFrame) {
				returnVal = ((RegisterControlFrame) controlFrame).getReturn();
			} else {
				io.printError("Unrecognized mode, this should never happen");
				returnVal = null;
			}
			
			if (returnVal != null) {
				String exitObj = returnVal.toString();
				if (currentFrame.getId().equals("_exit")) {
					Matcher match = PATTERN.matcher(exitObj);
					if (match.matches()) {
						int exitcode = Integer.valueOf(match.group(1));
						if (exitcode == 0) {
							io.printError("Execution terminated sucessfully");
						} else {
							io.printError("Execution terminated with errors: " + exitcode);
						}
					} else {
						return null;	// Exitcode is not an intV 
					}
				} else if (currentFrame.getId().equals("_catch")) {
					io.printError("Uncought exception: " + exitObj);
				}
			} else {
				io.printError("Execution terminated in terminal state without exitcode");
				return factory.makeString("FAIL");
			}
			if (env.debug.length() > 0) {
				io.printError("Printing debug info, all output is discarded");
				return factory.makeString(env.debug.trim());
			} else {
				String out = env.stdout.toString();
				if (out.endsWith("\n")) {
					out = out.substring(0, out.length() - 1);
				}
				return factory.makeString(out);
			}
		} else {
			ControlFrame frame = env.currentThread.getControlFrame();
			String instr = frame.getBlock().getInstr(Math.max(0, frame.getInstr_count() - 1)).toString();
			if (instr.startsWith("STC_DebugKill")) {
				io.printError("Printing debug info, all output is discarded");
				return factory.makeString(env.debug.trim());
			} else {
				io.printError("Execution terminated in non-terminal state");
				return factory.makeString("FAIL");
			}
		}
	}
}
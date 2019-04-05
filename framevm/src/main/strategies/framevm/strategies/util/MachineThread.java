package framevm.strategies.util;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import mb.nabl2.stratego.StrategoBlob;

public class MachineThread {
	private ControlFrame controlFrame;
	private boolean running;
	private MachineState env;
	
	public MachineThread(ControlFrame frame, MachineState env) {
		running = false;
		controlFrame = frame;
		this.env = env;
	}
	
	public void initThread() {
		running = controlFrame.hasNextInstruction();
	}
	
	public void stopThread() {
		
	}
	
	public IStrategoTerm evalNext(Context context, Strategy eval, StrategoBlob env) {
		if (!controlFrame.hasNextInstruction()) throw new IllegalStateException("Executing finished thread");
		
		IStrategoTerm instruction = controlFrame.nextInstruction();
		IStrategoTerm result = eval.invoke(context, context.getFactory().makeTuple(instruction, env));
		running = controlFrame.hasNextInstruction();
		return result;
	}
	
	public boolean isRunning() {
		return running;
	}

	public ControlFrame getControlFrame() {
		return controlFrame;
	}

	public void setControlFrame(ControlFrame controlFrame) {
		this.controlFrame = controlFrame;
	}

	/**
	 * @return the machine state
	 */
	public MachineState getEnv() {
		return env;
	}
}

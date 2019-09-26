package org.metaborg.lang.framevm_core.util;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import mb.nabl2.terms.stratego.StrategoBlob;

public class MachineThread {
	private ControlFrame controlFrame;
	private MachineState env;

	private Block block;
	private int instr_count;
	
	public MachineThread(ControlFrame frame, MachineState env) {
		controlFrame = frame;
		this.env = env;
	}
	
	public void initThread() {	}
	
	public void stopThread() {	}
	
	public IStrategoTerm evalNext(Context context, Strategy eval, StrategoBlob env) {
		if (!hasNextInstruction()) throw new IllegalStateException("Executing finished thread");
		
		IStrategoTerm instruction = nextInstruction();
		IStrategoTerm result = eval.invoke(context, context.getFactory().makeTuple(instruction, env));
		return result;
	}
	
	public boolean isRunning() {
		return hasNextInstruction();
	}

	public ControlFrame getControlFrame() {
		return controlFrame;
	}

	public StackControlFrame getStackControlFrame() {
		if (env.mode != VMMode.STACK || controlFrame instanceof StackControlFrame) {
			return (StackControlFrame) controlFrame;
		} else {
			return null;
		}
	}

	public void callContinuation(Continuation continuation) {
		this.controlFrame = continuation.getControlFrame();
		this.controlFrame.restoreMemory(continuation.getMemory(), continuation.getFrame());
		this.jump(continuation.getBlock());
	}

	public void callControlFrame(ControlFrame cf) {
		this.controlFrame = cf;
	}

	/**
	 * @return the machine state
	 */
	public MachineState getEnv() {
		return env;
	}

	public RegisterControlFrame getRegisterControlFrame() {
		if (env.mode != VMMode.REGISTER || controlFrame instanceof RegisterControlFrame) {
			return (RegisterControlFrame) controlFrame;
		} else {
			return null;
		}
	}
	
	/**
	 * @return
	 * 		The next instruction
	 */
	public IStrategoTerm nextInstruction() {
		return block.getInstr(instr_count++);
	}
	
	/**
	 * @return
	 * 		True if there is a next instruction, false otherwise
	 */
	public boolean hasNextInstruction() {
		return block != null && null != block.getInstr(instr_count);
	}
	
	/**
	 * Jump execution to the given block.
	 * 
	 * @param block
	 * 		The block to jump to
	 */
	public void jump(Block block) {
		this.jump(block, 0);
	}
	public void jump(Block block, int count) {
		this.block = block;
		this.instr_count = count;
	}

	public Block getBlock() {
		return block;
	}

	public int getInstr_count() {
		return instr_count;
	}
	
}

package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import mb.nabl2.terms.stratego.StrategoBlob;

public class MachineThread {
	private ControlFrame controlFrame;
	private MachineState env;

	private Block block;
	private int instr_count;
	private Stack<IStrategoTerm> returnStack;
	
	public MachineThread(ControlFrame frame, MachineState env, int register_size) {
		this.controlFrame = frame;
		this.env = env;
		this.returnStack = new Stack<>();
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

	public void callContinuation(Continuation continuation) {
		this.controlFrame = continuation.getControlFrame();
		this.controlFrame.setCurrentFrame(continuation.getFrame());
		this.jump(continuation.getBlock());
		this.restoreMemory(continuation.getMemory());
	}

	public void callControlFrame(ControlFrame cf) {
		this.controlFrame = cf;
	}

	public void restoreMemory(Memory mem) {
		if (mem == null) return; // Nothing to do for the empty restore
		this.returnStack = mem.getStack();
		this.controlFrame.restoreMemory(mem);
	}

	/**
	 * @return the machine state
	 */
	public MachineState getEnv() {
		return env;
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
	
	public boolean hasReturn() {
		return !returnStack.isEmpty();
	}
	
	public Stack<IStrategoTerm> getReturns() {
		return returnStack;
	}

	public void pushReturn(IStrategoTerm result) {
		returnStack.push(result);
	}

	public IStrategoTerm popReturn() {
		return returnStack.pop();
	}
	
	@SuppressWarnings("unchecked")
	public Memory getMemory() {
		return new Memory((Stack<IStrategoTerm>) this.returnStack.clone(), controlFrame.getRegisterCopy());
	}
}

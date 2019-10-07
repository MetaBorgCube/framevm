package org.metaborg.lang.framevm_core.util;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import mb.nabl2.terms.stratego.StrategoBlob;

public class MachineThread {
	private ControlFrame controlFrame;
	private MachineState env;

	private Block block;
	private int instr_count;
	
	private IStrategoTerm[] registers;
	private Stack<IStrategoTerm> returnStack;
	
	
	private static final Pattern LOCAL = Pattern.compile("\\w([0-9]+)");
	
	public MachineThread(ControlFrame frame, MachineState env, int register_size) {
		this.controlFrame = frame;
		this.env = env;
		this.returnStack = new Stack<>();
		this.registers = new IStrategoTerm[register_size];
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
	


	private int slotId(String slot) {
		Matcher matcher = LOCAL.matcher(slot);
		matcher.matches();
		matcher.group();
		return Integer.valueOf(matcher.group(1));
	}
	
	public void set(String slot, IStrategoTerm term) {
		if (registers == null) throw new IllegalStateException("Locals not set");
		registers[slotId(slot)] = term;
	}
	
	public IStrategoTerm get(String slot) {
		if (registers == null) throw new IllegalStateException("Locals not set");
		return registers[slotId(slot)];
	}
	
	public boolean hasReturn() {
		return !returnStack.isEmpty();
	}
	
	public Stack<IStrategoTerm> getReturns() {
		return returnStack;
	}

	public IStrategoTerm[] getRegisters() {
		return registers;
	}

	public void pushReturn(IStrategoTerm result) {
		returnStack.push(result);
	}

	public IStrategoTerm popReturn() {
		return returnStack.pop();
	}
	
	@SuppressWarnings("unchecked")
	public Memory getMemory() {
		return new Memory((Stack<IStrategoTerm>) this.returnStack.clone(), (IStrategoTerm[]) registers.clone());
	}

	public void restoreMemory(Memory mem) {
		if (mem == null) return; // Nothing to do for the empty restore
		this.registers = mem.getRegisters();
		this.returnStack = mem.getStack();
	}
}

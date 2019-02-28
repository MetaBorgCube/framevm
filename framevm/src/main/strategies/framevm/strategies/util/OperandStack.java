package framevm.strategies.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;
import framevm.strategies.Frame;

public class OperandStack {
	private Block block;
	private Block returnAddr;
	private int instr_count;
	private Stack<IStrategoTerm> stack;
	private Frame returnFrame;
	private Slot returnValue;
	
	public OperandStack(Block block, Block returnAddr, Frame returnFrame) {
		this.block = block;
		this.returnAddr = returnAddr;
		this.returnFrame = returnFrame;
		
		this.instr_count = 0;
		this.stack = new Stack<>(); //TODO: pre-allocate block.max-stack
	}
	
	public IStrategoTerm next() {
		return block.getInstr(instr_count++);
	}
	
	public IStrategoTerm push(IStrategoTerm term) {
		return stack.push(term);
	}
	
	public IStrategoTerm pop() {
		return stack.pop();
	}

	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public void jump(Block block) {
		this.block = block;
		this.instr_count = 0;
	}
	
	public Frame do_return(IStrategoTerm value) {
		returnFrame.getOperandStack().do_return(returnAddr, value);
		return returnFrame;
	}

	private void do_return(Block returnAddr, IStrategoTerm value) {
		this.returnValue = new Slot(value);
		this.jump(returnAddr);
	}
	
	public Slot getReturnValue() {
		return this.returnValue;
	}
	
	public Frame getReturnFrame() {
		return this.returnFrame;
	}

	public Block getReturnAddr() {
		return this.returnAddr;
	}
}

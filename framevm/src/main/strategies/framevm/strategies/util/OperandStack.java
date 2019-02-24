package framevm.strategies.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.strc.current_definition_0_0;

import framevm.strategies.Frame;

public class OperandStack {
	private Routine routine;
	private Routine returnAddr;
	private int instr_count;
	private Stack<IStrategoTerm> stack;
	private Frame returnFrame;
	private Slot returnValue;
	
	public OperandStack(Routine routine, Routine returnAddr, Frame returnFrame) {
		this.routine = routine;
		this.returnAddr = returnAddr;
		this.returnFrame = returnFrame;
		
		this.instr_count = 0;
		this.stack = new Stack<>(); //TODO: pre-allocate routine.max-stack
	}
	
	public IStrategoTerm next() {
		return routine.getInstr(instr_count++);
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
	
	public void jump(Routine routine) {
		this.routine = routine;
		this.instr_count = 0;
	}
	
	public Frame do_return(IStrategoTerm value) {
		returnFrame.getOperandStack().do_return(returnAddr, value);
		return returnFrame;
	}

	private void do_return(Routine returnAddr, IStrategoTerm value) {
		this.returnValue = new Slot(value);
		this.jump(returnAddr);
	}
	
	public Slot getReturnValue() {
		return this.returnValue;
	}
}

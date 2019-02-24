package framevm.strategies.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

public class OperandStack {
	private Routine routine;
	private Routine returnAddr;
	private int instr_count;
	private Stack<IStrategoTerm> stack;
	
	public OperandStack(Routine routine, Routine returnAddr) {
		this.routine = routine;
		this.returnAddr = returnAddr;
		
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
}

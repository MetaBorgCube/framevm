package org.metaborg.lang.framevm_core.util;

import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public class StackControlFrame extends ControlFrame {
	private Stack<IStrategoTerm> stack;
	private int maxStack;
	
	/**
	 * Create an operand stack.
	 */
	public StackControlFrame(int contSize, int maxStack, Block block, String id) {
		super(contSize, block, id);
		this.stack = new Stack<>();
		this.maxStack = maxStack;
	}

	/**
	 * Push a term on the stack.
	 * 
	 * @param term
	 * 		The term to put on the stack
	 * @return
	 * 		The term pushed on the stack
	 */
	public IStrategoTerm push(IStrategoTerm term) {
		if (stack.size() == this.maxStack) {
			throw new StackOverflowError("Max size of " + this.maxStack + " exceeded");
		}
		return stack.push(term);
	}
	
	/**
	 * @return
	 * 		The term popped from the stack
	 */
	public IStrategoTerm pop() {
		return stack.pop();
	}

	/**
	 * @return
	 * 		false when the stack is empty, true otherwise
	 */
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public Stack<IStrategoTerm> getStack() {
		return stack;
	}

	@Override
	public void pushReturn(IStrategoTerm result) {
		this.push(result);
	}

	@Override
	public IStrategoTerm popReturn() {
		return this.pop();
	}

	@Override
	public boolean hasReturn() {
		return this.hasNext();
	}
}

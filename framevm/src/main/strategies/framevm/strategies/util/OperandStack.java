package framevm.strategies.util;

import java.util.HashMap;
import java.util.Stack;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Operand stack that gets attached to a {@link Frame} when it becomes executable.
 * This opstack holds the stack, instruction counter and return addresses.
 */
public class OperandStack {
	private Block block;
	private int instr_count;
	private Stack<IStrategoTerm> stack;
	private HashMap<String, Slot> continuations;
	private Slot returnValue;
	
	/**
	 * Create an operand stack.
	 */
	public OperandStack() {
		this.block = null;
		this.continuations = new HashMap<>();
		
		this.instr_count = 0;
		this.stack = new Stack<>(); //TODO: pre-allocate block.max-stack
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
		return null != block.getInstr(instr_count);
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
	
	/**
	 * Jump execution to the given block.
	 * 
	 * @param block
	 * 		The block to jump to
	 */
	public void jump(Block block) {
		this.block = block;
		this.instr_count = 0;
	}

	/**
	 * Called when returning to this operand stack.
	 * Stores the return value.
	 * 
	 * @param value
	 * 		The returned value
	 */
	public void setReturnValue(IStrategoTerm value) {
		this.returnValue = new Slot(value);
	}
	
	/**
	 * @return
	 * 		The return value
	 */
	public Slot getReturnValue() {
		return this.returnValue;
	}
	
	/**
	 * @param slotId 
	 * @return
	 * 		The current continuation
	 */
	public Slot getContinuation(String id) {
		return this.continuations.get(id);
	}
	
	public Block getBlock() {
		return block;
	}

	public int getInstr_count() {
		return instr_count;
	}

	public Stack<IStrategoTerm> getStack() {
		return stack;
	}

	public void setContinuation(String id, IStrategoTerm continuation) {
		this.continuations.put(id,  new Slot(continuation));
	}

	public void setException(IStrategoTerm exception) {
		this.exception = new Slot(exception);
	}

	public HashMap<String, Slot> getContinuations() {
		return continuations;
	}
}

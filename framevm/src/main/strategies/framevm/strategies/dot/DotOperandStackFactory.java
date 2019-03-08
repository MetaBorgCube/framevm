package framevm.strategies.dot;

import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;

import framevm.strategies.util.Frame;
import framevm.strategies.util.OperandStack;

/**
 * Factory for creating a dotfile representing an {@link OperandStack}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotOperandStackFactory extends DotFactory {
	
	/**
	 * Convert an operandStack to DOT representation.
	 * 
	 * @param frame
	 * 		The frame that owns the operandstack to convert
	 * @param links
	 * 		A mutable list were the links from this operandstack are added to
	 * @return
	 * 		A String containing a DOT node representing of the given opstack
	 */
	public static String build(Frame frame, List<String> links) {
		String name = operandStack(frame);
		OperandStack opstack = frame.getOperandStack();
		int count = opstack.getInstr_count();

		// Add links to the executing instruction and the stack
		if (opstack.getBlock() != null) {
			String target = block(opstack.getBlock()) + ":" + (Math.max(0, count - 1));
			links.add(blockLink(name, target));
		}
		links.add(stackLink(name, DotFactory.stack(frame)));

		// Link the return address
		if (opstack.getContinuation() != null) {
			String returnTarget_id = ((StrategoString) opstack.getContinuation().getSubterm(0)).stringValue();
			if ("_exit".equals(returnTarget_id)) {
				links.add(returnLink(name, "finish"));
			} else {
				links.add(returnLink(name, frame(returnTarget_id) + ":id"));
			}
		}
		
		// Link the exception address
		if (opstack.getException() != null) {
			String exceptionTarget_id = ((StrategoString) opstack.getException().getSubterm(0)).stringValue();
			if ("_catch".equals(exceptionTarget_id)) {
				links.add(exceptionLink(name, "exception"));
			} else {
				links.add(exceptionLink(name, frame(exceptionTarget_id) + ":id"));
			}
		}

		// Get the value in the return (r) slot
		String returnVal;
		if (opstack.getReturnValue() == null) {
			returnVal = "null";
		} else {
			returnVal = slotToString(opstack.getReturnValue(), links, name + ":r");
		}
		
		// Generate main node
		String dotString = node(name, "{<head>Opstack | {{R | Block | ret | ex | stack}| { <r>" + returnVal + " | <block> | <ret> | <ex> | <stack>}}}");
		
		// Generate the node for the local stack
		String stackString = "";
		
		// Copy the stack as we do a destructive read
		@SuppressWarnings("unchecked")
		Stack<IStrategoTerm> stack = (Stack<IStrategoTerm>) opstack.getStack().clone();
		
		count = 0;
		name = stack(frame);
		
		while (!stack.isEmpty())  {
			count++;
			stackString += "|<" + count + ">" + termToString(stack.pop(), links, name + ":" + count);
		}
		return dotString + node(name, "{<head>" + stackString + "}");
	}
}

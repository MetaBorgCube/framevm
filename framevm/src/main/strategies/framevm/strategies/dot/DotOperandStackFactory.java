package framevm.strategies.dot;

import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.Frame;
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
		String target = block(opstack.getBlock()) + ":" + (count - 1);
		links.add(blockLink(name, target));
		links.add(stackLink(name, DotFactory.stack(frame)));
		
		// Link the return address
		if (opstack.getReturnFrame() != null) {
			links.add(returnLink(name, frame(opstack.getReturnFrame()) + ":id"));
		} else {
			links.add(returnLink(name, "finish"));			
		}
		
		// Get the value in the return (r) slot
		String returnVal;
		if (opstack.getReturnValue() == null) {
			returnVal = "null";
		} else {
			returnVal = slotToString(opstack.getReturnValue(), links, name + ":r");
		}
		
		// Generate main node
		String dotString = node(name, "{<head>Opstack | {{R | Block | ret | stack}| { <r>" + returnVal + " | <block> | <ret> | <stack>}}}");
		
		// Generate the node for the local stack
		String stackString = "";
		
		// Copy the stack as we do a destructive read
		@SuppressWarnings("unchecked")
		Stack<IStrategoTerm> stack = (Stack<IStrategoTerm>) opstack.getStack().clone();
		while (!stack.isEmpty())  {
			stackString += "|" + stack.pop().toString().replace("\"", "");
		}
		return dotString + node(stack(frame), "{<head>" + stackString + "}");
	}
}

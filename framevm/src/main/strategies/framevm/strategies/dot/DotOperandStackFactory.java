package framevm.strategies.dot;

import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoString;

import framevm.strategies.util.Continuation;
import framevm.strategies.util.Frame;
import framevm.strategies.util.ControlFrame;

/**
 * Factory for creating a dotfile representing an {@link ControlFrame}.
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
		ControlFrame opstack = null;
		int count = opstack.getInstr_count();

		// Add links to the executing instruction and the stack
		if (opstack.getBlock() != null) {
			String target = block(opstack.getBlock()) + ":" + (Math.max(0, count - 1));
			links.add(blockLink(name, target));
		}
		links.add(stackLink(name, DotFactory.stack(frame)));

		// Link the continuations
		String contSlots = "";
		String contIds = "";
		Continuation[] continuations = opstack.getContinuations();
		for (int i = 0; i < continuations.length; i++) {
			Continuation cont = continuations[i];
			if (cont == null) {
				contSlots += " | c" + i;
				contIds += " | null";
			} else {
				String target_id = cont.value().getCurrentFrame().getId();
				contSlots += " | " + cont.id;
				
				contIds += " | <cont_" + cont.id + ">";
				if ("_exit".equals(target_id)) {
					links.add(continuationLink(name, "finish", cont.id));
				} else if ("_catch".equals(target_id)) {
					links.add(continuationLink(name, "exception", cont.id));
				} else {
					links.add(continuationLink(name, frame(target_id) + ":id", cont.id));
				}
			}
		}
		if (contSlots.length() == 0) {
			contSlots = " |";
		}
		if (contIds.length() == 0) {
			contIds = " |";
		}

		// Get the value in the return (r) slot
		String returnVal;
		if (opstack.getReturnValue() == null) {
			returnVal = "null";
		} else {
			returnVal = slotToString(opstack.getReturnValue(), links, name + ":r");
		}
		
		// Generate main node
		String dotString = node(name, "{<head>Opstack | {{R | Block" + contSlots + " | stack}| { <r>" + returnVal + " | <block>" + contIds + " | <stack>}}}");
		
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
		return dotString + "\n\t\t" + node(name, "{<head>" + stackString + "}");
	}
}

package org.metaborg.lang.framevm_core.dot;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.StackControlFrame;

/**
 * Factory for creating a dotfile representing an {@link ControlFrame}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotStackControlFrameFactory extends DotControlFrameFactory {
	
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
	public static void buildmemory(StackControlFrame frame, HashMap<String, String> nodes, List<String> links) {
		// Generate the node for the local stack
		String stackString = "";
		
		// Copy the stack as we do a destructive read
		@SuppressWarnings("unchecked")
		Stack<IStrategoTerm> stack = (Stack<IStrategoTerm>) frame.getStack().clone();
		
		int count = 0;
		String stackName = memory(frame);
		
		while (!stack.isEmpty())  {
			count++;
			stackString += "|<" + count + ">" + termToString(stack.pop(), nodes, links, stackName + ":" + count);
		}
		nodes.put(stackName, node(stackName, "{<head>" + stackString + "}"));
	}
	
	public static String getMemType() {
		return "Stack";
	}
}

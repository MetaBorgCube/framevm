package framevm_stacy.strategies.dot;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;
import framevm_stacy.strategies.util.Continuation;
import framevm_stacy.strategies.util.ControlFrame;
import framevm_stacy.strategies.util.Frame;

/**
 * Factory for creating a dotfile representing an {@link ControlFrame}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotControlFrameFactory extends DotFactory {
	
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
	public static String build(ControlFrame frame, HashMap<String, String> nodes, List<String> links) {
		String name = controlFrame(frame);
		if (nodes.containsKey(name)) {
			return "";
		} else {
			nodes.put(name, "");	// Mark this as existing, eventhough we are not finished creating it (This prevents infinite looping)
		}
		
		int count = frame.getInstr_count();

		// Add links to the executing instruction and the stack
		if (frame.getBlock() != null) {
			String target = block(frame.getBlock()) + ":" + (Math.max(0, count - 1));
			links.add(blockLink(name, target));
		}
		links.add(stackLink(name, DotFactory.stack(frame)));

		// Link the continuations
		String contSlots = "";
		String contIds = "";
		Continuation[] continuations = frame.getContinuations();
		for (int i = 0; i < continuations.length; i++) {
			Continuation cont = continuations[i];
			if (cont == null) {
				contSlots += " | c" + i;
				contIds += " | null";
			} else {
				String target_id = cont.value().getId();
				contSlots += " | " + cont.id;
				
				contIds += " | <cont_" + cont.id + ">";
				if ("_exit".equals(target_id)) {
					links.add(continuationLink(name, "finish", cont.id));
				} else if ("_catch".equals(target_id)) {
					links.add(continuationLink(name, "exception", cont.id));
				} else {
					ControlFrame target = cont.value();
					if (!nodes.containsKey(controlFrame(target))) {
						DotControlFrameFactory.build(target, nodes, links);
					}
					links.add(continuationLink(name, controlFrame(target) + ":id", cont.id));
				}
			}
		}
		if (contSlots.length() == 0) {
			contSlots = " |";
		}
		if (contIds.length() == 0) {
			contIds = " |";
		}
		
		// Generate main node
		String dotString = node(name, "{<id>" + frame.getId() + " | {{PC | Frame | Stack" + contSlots + "}| { <pc> | <frame> | <stack>" + contIds + "}}}");
		nodes.put(name, dotString);
		
		// Generate the node for the local stack
		String stackString = "";
		
		// Copy the stack as we do a destructive read
		@SuppressWarnings("unchecked")
		Stack<IStrategoTerm> stack = (Stack<IStrategoTerm>) frame.getStack().clone();
		
		count = 0;
		String stackName = stack(frame);
		
		while (!stack.isEmpty())  {
			count++;
			stackString += "|<" + count + ">" + termToString(stack.pop(), nodes, links, stackName + ":" + count);
		}
		nodes.put(stackName, node(stackName, "{<head>" + stackString + "}"));
		
		
		if (frame.getCurrentFrame() != null) {
			Frame dataFrame = frame.getCurrentFrame();
			DotFrameFactory.build(dataFrame, nodes, links);
			links.add(dataFrameLink(name, frame(dataFrame)));
		}
		return name;
	}
}

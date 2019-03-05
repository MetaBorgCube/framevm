package framevm.strategies.dot;

import java.util.List;
import java.util.Stack;
import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.Frame;
import framevm.strategies.util.OperandStack;

public class DotOperandStackFactory extends DotFactory {
	
	public static String build(Frame frame, List<String> links) {
		String name = operandStack(frame);
		OperandStack opstack = frame.getOperandStack();
		int count = opstack.getInstr_count();
		
		String target = block(opstack.getBlock()) + ":" + ((count < opstack.getBlock().size()) ? count : count - 1);
		links.add(blockLink(name, target));
		links.add(stackLink(name, DotFactory.stack(frame)));
		
		if (opstack.getReturnFrame() != null) {
			links.add(returnLink(name, frame(opstack.getReturnFrame()) + ":id"));
		} else {
			links.add(returnLink(name, "finish"));			
		}
		
		String returnVal;
		if (opstack.getReturnValue() == null) {
			returnVal = "null";
		} else {
			returnVal = slotToString(opstack.getReturnValue(), links, name + ":r");
		}
		
		String dotString = node(name, "{<head>Opstack | {{R | Block | ret | stack}| { <r>" + returnVal + " | <block> | <ret> | <stack>}}}");
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

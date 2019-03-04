package framevm.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.util.OperandStack;

public class DotOperandStack implements DotSerializable {

	private String dotString;
	private ArrayList<String> links;

	public DotOperandStack(Frame frame) {
		String name = "opstack_" + frame.getId();
		OperandStack opstack = frame.getOperandStack();
		
		links = new ArrayList<>();
		int count = opstack.getInstr_count();
		
		links.add(name + ":block -> block_" + opstack.getBlock().getName() + ":" + ((count < opstack.getBlock().size()) ? count : count - 1) + " [style=dotted]");
		links.add(name + ":ret -> frame_" + frame.getId() + ":id [color=green, style=dashed]");
		links.add(name + ":stack -> stack_" + frame.getId() + ":head [color=red, style=dashed]");
		
		String returnVal;
		if (opstack.getReturnValue() == null) {
			returnVal = "null";
		} else {
			String value = opstack.getReturnValue().value.toString().replace("\"", "");
			Matcher matcher = Pattern.compile("FrameRef\\((.*)\\)").matcher(value);
			if (matcher.matches()) {
				String ref = "frame_" + matcher.group(1);
				returnVal = "<" + ref + ">" + value;
				links.add(name + ":" + ref + " -> " + ref + ":id [color=blue, style=dashed]");
			} else {
				returnVal = value;
			}
		}
		this.dotString = "\t" + name + "[label=\"{<head>Opstack | {{R | Block | ret | stack}| { " + returnVal + " | <block> | <ret> | <stack>}}}\"]";
		String stackString = "";
		Stack<IStrategoTerm> stack = (Stack<IStrategoTerm>) opstack.getStack().clone();
		while (!stack.isEmpty())  {
			stackString += "|" + stack.pop().toString().replace("\"", "");
		}
		this.dotString += "\n\tstack_" + frame.getId() + "[label=\"{<head>" + stackString + "}\"]";
	}

	@Override
	public List<String> links() {
		return links;
	}

	@Override
	public String toDotString() {
		return dotString;
	}

}

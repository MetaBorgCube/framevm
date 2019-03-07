package framevm.strategies.dot;

import java.util.ArrayList;
import java.util.List;

import framevm.strategies.Frame;
import framevm.strategies.util.Block;
import framevm.strategies.util.Environment;

/**
 * Factory for creating a dotfile representing an {@link Environment}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotEnvironmentFactory extends DotFactory {

	/**
	 * Build the dot representation of the given Environment.
	 * 
	 * @param env
	 * 		The environment to convert
	 * @return
	 * 		A string containing the dot representation
	 */
	public static String build(Environment env) {
		List<String> nodeList = new ArrayList<>();
		List<String> linkList = new ArrayList<>();
		String currentFrame = frame(env.currentFrame);
		
		// Add all frames
		for (Frame frame : env.heap.values()) {
			if (frame.getId().equals("exit")) continue;
			
			nodeList.add(DotFrameFactory.build(frame, linkList));
			
			if (frame.getOperandStack() != null) {
				nodeList.add(DotOperandStackFactory.build(frame, linkList));
			}
		}
		
		nodeList.add(""); 	// Spacing
		
		// Add all code blocks
		for (Block block : env.blocks.values()) {
			if (block.getName().equals("_exit")) continue;
			
			nodeList.add(DotBlockFactory.build(block, linkList));
		}

		// Generate output string
		String nodes = toString(nodeList);
		String links = toString(linkList);

		return "digraph frames {\n"
				+ "\tnode [shape=circle]\n"
				+ "\tfinish [color=red]\n"
				+ "\tcurrent [color=red]\n\n"
				+ "\tnode [shape=record];\n\n" 
				+ nodes + "\n\n\n" 
				+ "\tcurrent -> " + currentFrame + "\n\n" 
				+ links + "}";
	}

	/**
	 * Take a list of strings and concatenate them one on each line.
	 * 
	 * @param strings
	 * 		The list to concatenate
	 * @return
	 * 		The concatenated string
	 */
	public static String toString(List<String> strings) {
		StringBuilder res = new StringBuilder();
		for (String string : strings) {
			res.append('\t').append(string).append('\n');
		}
		return res.toString();
	}
}

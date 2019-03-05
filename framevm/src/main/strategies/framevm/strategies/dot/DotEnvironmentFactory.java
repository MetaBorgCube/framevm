package framevm.strategies.dot;

import java.util.ArrayList;
import java.util.List;

import framevm.strategies.Frame;
import framevm.strategies.util.Block;
import framevm.strategies.util.Environment;

public class DotEnvironmentFactory extends DotFactory {

	public static String build(Environment env) {
		List<String> nodeList = new ArrayList<>();
		List<String> linkList = new ArrayList<>();
		String currentFrame = frame(env.currentFrame);
		
		for (Frame frame : env.heap.values()) {
			nodeList.add(DotFrameFactory.build(frame, linkList));
			
			if (frame.getOperandStack() != null) {
				nodeList.add(DotOperandStackFactory.build(frame, linkList));//.append(nodes, links);
			}
		}
		
		nodeList.add(""); 	// Spacing
		
		for (Block block : env.blocks.values()) {
			nodeList.add(DotBlockFactory.build(block, linkList));
		}

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

	public static String toString(List<String> strings) {
		StringBuilder res = new StringBuilder();
		for (String string : strings) {
			res.append('\t').append(string).append('\n');
		}
		return res.toString();
	}
}

package framevm.strategies.dot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import framevm.strategies.util.Block;
import framevm.strategies.util.MachineState;
import framevm.strategies.util.MachineThread;

/**
 * Factory for creating a dotfile representing a {@link MachineState}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotMachineStateFactory extends DotFactory {

	/**
	 * Build the dot representation of the given Environment.
	 * 
	 * @param env
	 * 		The environment to convert
	 * @return
	 * 		A string containing the dot representation
	 */
	public static String build(MachineState env) {
		HashMap<String, String> nodeList = new HashMap<>();
		List<String> linkList = new ArrayList<>();

		for (MachineThread thread : env.getThreads()) {
			String current = DotMachineThreadFactory.build(thread, nodeList, linkList);
			
			linkList.add(link("current", current));
		}

		List<String> blockList = new ArrayList<>();
		// Add all code blocks
		for (HashMap<String, Block> lib : env.blocks.values()) {
			for (Block block : lib.values()) {
				if (block.getName().equals("_exit") || block.getName().equals("_catch")) continue;
				
				blockList.add(DotBlockFactory.build(block, linkList));
			}
		}

		// Generate output string
		String nodes  = toString(nodeList);
		String blocks = toString(blockList);
		String links  = toString(linkList);

		return "digraph frames {\n"
				+ "\tnode [shape=circle];\n"
				+ "\tfinish [color=red];\n"
				+ "\texception [color=purple];\n"
				+ "\tcurrent [color=green];\n\n"
				+ "\tnode [shape=record];\n\n" 
				+ nodes + "\n\n\n"
				+ blocks + "\n\n\n"
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
	
	/**
	 * Take a hashmap of strings and ids and concatenate them one on each line.
	 * 
	 * @param strings
	 * 		The map to concatenate
	 * @return
	 * 		The concatenated string
	 */
	public static String toString(HashMap<String, String> strings) {
		StringBuilder res = new StringBuilder();
		for (String string : strings.values()) {
			res.append('\t').append(string).append('\n');
		}
		return res.toString();
	}
}

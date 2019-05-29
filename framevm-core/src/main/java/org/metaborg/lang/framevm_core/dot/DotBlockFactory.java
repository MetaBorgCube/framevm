package org.metaborg.lang.framevm_core.dot;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.metaborg.lang.framevm_core.util.Block;

/**
 * Factory for creating a dotfile representing an {@link Block}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotBlockFactory extends DotFactory {
	private final static String[] REPLACE_FROM = {"FVM_Self()"};
	private final static String[] REPLACE_TO   = {"[]"        };
 
	private final static String[] REGEX_UNPACK = {"\"([^\"]*)\"", "Bind\\((\\w+),\\d+\\)", "FVM_Link\\((\\w+)\\)", "FVM_Slot\\((\\w+)\\)", "FVM_Cont\\((\\w+)\\)", "FVM_Path\\((\\[.+\\])\\)"};
	private final static Pattern PATTERN = Pattern.compile("FVM_BoundLabel\\([\\w\\.]+,(\\w+)\\)");
	private final static Pattern TERM_INDEX_PATTERN = Pattern.compile("\\{TermIndex\\([^\\}]+\\)\\}");
	
	/**
	 * Convert a block to DOT representation.
	 * 
	 * @param block
	 * 		The block to convert
	 * @param links
	 * 		A mutable list were the links from this block are added to
	 * @return
	 * 		A string representing a DOT node of this block
	 */
	public static String build(Block block, List<String> links) {
		String name = block(block);
		
		String instrsString = "";
		for (int i = 0; i < block.size(); i++) {
			String instruction = block.getInstr(i).toString();
			instrsString += "|<" + i + ">" + sanitize(instruction, i, name, links); 
		}
		return node(name, "{<head>" + block.getName() + instrsString + "}");
	}
	
	/**
	 * Sanitize a string containing instructions to be better readable.
	 *  
	 * @param instr
	 * 		The instruction to sanitize
	 * @param idx
	 * 		The idx of this instruction (Needed for labeling)
	 * @param block
	 * 		The current block
	 * @param links\{TermIndex\([^}]+\)\}
	 * 		A mutable list were the links from this instruction are added to		
	 * @return
	 * 		The sanitized string representing the instruction
	 */
	private static String sanitize(String instr, int idx, String block, List<String> links) {
		String out = instr.substring(4);
		out = TERM_INDEX_PATTERN.matcher(out).replaceAll("");

		for (int i = 0; i < REPLACE_FROM.length; i++) {
			out = out.replaceAll(REPLACE_FROM[i], REPLACE_TO[i]);
		}
		for (String regex : REGEX_UNPACK) {
			out = out.replaceAll(regex, "$1");
		}
		
		Matcher matcher = PATTERN.matcher(out);
		while (matcher.find()) {
			String label = matcher.group(1);
			links.add(link(block + ":" + idx, block(label) + ":head", label));
			out = out.replaceFirst(PATTERN.pattern(), label);
		}
		return out;
	}
}
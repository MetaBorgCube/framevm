package framevm.strategies.dot;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framevm.strategies.util.Block;

public class DotBlockFactory extends DotFactory {
	private final static String[] REPLACE_FROM = {"Path(Self)"};
	private final static String[] REPLACE_TO   = {"[]"        };

	private final static String[] REGEX_UNPACK = {"\"([^\"]*)\"", "Path(Self)", "Link\\((\\w+)\\)", "Slot\\((\\w+)\\)", "Path\\((\\[\\w+\\])\\)"};
	private final static Pattern PATTERN = Pattern.compile("Label\\(([\\w]+)\\)");
	
	public static String build(Block block, List<String> links) {
		String name = block(block);
		
		String instrsString = "";
		for (int i = 0; i < block.size(); i++) {
			String instruction = block.getInstr(i).toString();
			instrsString += "|<" + i + ">" + sanitize(instruction, i, name, links); 
		}
		return node(name, "{<head>" + block.getName() + instrsString + "}");
	}
	
	private static String sanitize(String value, int idx, String block, List<String> links) {
		String out = value;
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
		}
		return out;
	}
}

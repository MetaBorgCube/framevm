package framevm.strategies.dot;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framevm.strategies.Frame;
import framevm.strategies.util.Block;
import framevm.strategies.util.Slot;

public abstract class DotFactory {
		private static final Pattern PATTERN = Pattern.compile("FrameRef\\((.*)\\)");
		
		public static String frame(Frame frame) {
			return frame(frame.getId());
		}
		
		private static String frame(String id) {
			return "frame_"+ id;
		}
		
		public static String block(Block block) {
			return block(block.getName());
		}
		
		public static String block(String id) {
			return "block_"+ id;
		}

		public static String operandStack(Frame frame) {
			return "opstack_"+ frame.getId();
		}
		
		public static String stack(Frame frame) {
			return "stack_"+ frame.getId();
		}
		
		public static String node(String id, String body) {
			return id + " [label=\"" + body + "\"];";
		}

		public static String link(String from, String to, String label) {
			return from + " -> " + to + " [label=" + label + "];";
		}
		
		public static String referenceLink(String from, String to) {
			return from + " -> " + to + " [color=blue, style=dashed];";
		}
		
		public static String stackLink(String from, String to) {
			return from + ":stack -> " + to + ":head [color=red, style=dashed];";
		}
		
		public static String opstackLink(String from, String to) {
			return from + ":opstack -> " + to + ":head [color=black, style=dashed];";
		}

		public static String blockLink(String from, String to) {
			return from + ":block -> " + to + " [style=dotted];";
		}
		
		public static String returnLink(String from, String to) {
			return from + ":ret -> " + to + " [color=green, style=dashed];";
		}
		
		public static String slotToString(Slot slot, List<String> links, String slotRef) {
			String value = slot.value.toString().replace("\"", "");
			Matcher matcher = PATTERN.matcher(value);
			
			if (matcher.matches()) {
				String frameRef = frame(matcher.group(1));
				links.add(referenceLink(slotRef, frameRef + ":id"));
			}
			return value;
		}
}

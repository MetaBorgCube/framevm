package framevm.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import framevm.strategies.util.Link;
import framevm.strategies.util.Slot;

public class DotFrame implements DotSerializable {
	private ArrayList<String> links;
	private String dotString;

	public DotFrame(Frame frame) {
		String name = "frame_" + frame.getId();
		this.links = new ArrayList<>();
		
		String slotsString = "{{X";
		List<Slot> slots = frame.getSlots();
		for (int i = 0; i < slots.size(); i++) {
			slotsString += "|" + i;
		}
		slotsString += "}|{<opstack>";		
		for (Slot slot : slots) {
			String value = slot.value.toString().replace("\"", "");
			Matcher matcher = Pattern.compile("FrameRef\\((.*)\\)").matcher(value);
			if (matcher.matches()) {
				String ref = "frame_" + matcher.group(1);
				slotsString += "|<" + ref + ">" + value;
				links.add(name + ":" + ref + " -> " + ref + ":id [color=blue, style=dashed]");
			} else {
				slotsString += "|" + value;
			}
		}
		slotsString += "}}";

		if (frame.getOperandStack() != null) {
			links.add( name + ":opstack -> opstack_" + frame.getId() + ":head [color=black, style=dashed]");
		}
		
		this.dotString = "\t" + name + " [label=\"{<id>" + frame.getId() + "|" + slotsString + "}\"];" ;
		
		for( Link link : frame.links()) {
			links.add(name + ":id -> frame_" + link.target.getId() + ":id [label=" + link.linkId + "]");
		}
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

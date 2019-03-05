package framevm.strategies.dot;

import java.util.List;

import framevm.strategies.Frame;
import framevm.strategies.util.Link;
import framevm.strategies.util.Slot;

public class DotFrameFactory extends DotFactory {

	public static String build(Frame frame, List<String> links) {
		String name = frame(frame);
		
		String slotsString = "{{X";
		List<Slot> slots = frame.getSlots();
		for (int i = 0; i < slots.size(); i++) {
			slotsString += "|" + i;
		}
		slotsString += "}|{<opstack>";
		
		for (int i = 0; i < slots.size(); i++) {
			Slot slot = slots.get(i);
			slotsString += "|<" + i + ">" + slotToString(slot, links, name + ":" + i);
		}
		slotsString += "}}";

		if (frame.getOperandStack() != null) {
			links.add(opstackLink(name, operandStack(frame)));
		}
		
		for( Link link : frame.links()) {
			links.add(link(name + ":id", frame(link.target) + ":id", link.linkId));
		}
		
		return node(name, "{<id>" + frame.getId() + "|" + slotsString + "}");
	}
}

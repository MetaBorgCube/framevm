package framevm.strategies.dot;

import java.util.List;

import framevm.strategies.Frame;
import framevm.strategies.util.Link;
import framevm.strategies.util.Slot;

/**
 * Factory for creating a dotfile representing an {@link Frame}.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public class DotFrameFactory extends DotFactory {

	/**
	 * Convert a frame to DOT representation.
	 * 
	 * @param frame
	 * 		The frame to convert
	 * @param links
	 * 		A mutable list were the links from this frame are added to
	 * @return
	 * 		A String containing a DOT node representing of the given frame
	 */
	public static String build(Frame frame, List<String> links) {
		String name = frame(frame);
		
		// Get the representation of slots
		// Starts with the column with ids
		String slotsString = "{{X";
		List<Slot> slots = frame.getSlots();
		for (int i = 0; i < slots.size(); i++) {
			slotsString += "|" + i;
		}

		// Than does the actual values
		slotsString += "}|{<opstack>";
		
		for (int i = 0; i < slots.size(); i++) {
			Slot slot = slots.get(i);
			slotsString += "|<" + i + ">" + slotToString(slot, links, name + ":" + i);
		}
		slotsString += "}}";

		// If the frame is executable, link to its opstack
		if (frame.getOperandStack() != null) {
			links.add(opstackLink(name, operandStack(frame)));
		}
		
		// Add all the links
		for( Link link : frame.links()) {
			links.add(link(name + ":id", frame(link.target) + ":id", link.linkId));
		}
		
		// Generate the final representation
		return node(name, "{<id>" + frame.getId() + "|" + slotsString + "}");
	}
}

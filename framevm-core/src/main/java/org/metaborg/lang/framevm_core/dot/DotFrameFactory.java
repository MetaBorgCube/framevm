package org.metaborg.lang.framevm_core.dot;

import java.util.HashMap;
import java.util.List;

import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.Link;
import org.metaborg.lang.framevm_core.util.Slot;

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
	public static String build(Frame frame, HashMap<String, String> nodes, List<String> links) {
		String name = frame(frame);
		if (nodes.containsKey(name)) {
			return name;
		} else {
			nodes.put(name, "");	// Mark this as existing, eventhough we are not finished creating it (This prevents infinite looping)
		}
		
		// Get the representation of slots
		// Starts with the column with ids
		String slotsString;
		Slot[] slots = frame.getSlots();
		if (slots.length > 0) {
			slotsString = "{{";
			for (int i = 0; i < slots.length; i++) {
				if (i == 0) {
					slotsString += i;
				} else {
					slotsString += "|" + i;
				}
			}
	
			// Then does the actual values
			slotsString += "}|{";
			
			for (int i = 0; i < slots.length; i++) {
				Slot slot = slots[i];
				if (slot == null) {
					slotsString += " null ";
				} else {
					slotsString += "<" + i + ">" + slotToString(slot, nodes, links, name + ":" + i);
				}
				if (i != slots.length - 1) {
					slotsString += "|";
				}
			}
			slotsString += "}}";
		} else {
			slotsString = "";
		}
		
		// Add all the links
		Link[] frameLinks = frame.getLinks();
		for (int i = 0; i < frameLinks.length; i++) {
			Link link = frameLinks[i];
			if (link == null) continue;
			links.add(link(name + ":id", frame(link.target) + ":id", link.linkId));
			DotFrameFactory.build(link.target, nodes, links);
		}
		
		// Generate the final representation
		nodes.put(name, node(name, "{<id>" + frame.getId() + "|" + slotsString + "}"));
		return name;
	}
}

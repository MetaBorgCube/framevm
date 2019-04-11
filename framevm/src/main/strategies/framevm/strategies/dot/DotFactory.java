package framevm.strategies.dot;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.util.Block;
import framevm.strategies.util.ControlFrame;
import framevm.strategies.util.Frame;
import framevm.strategies.util.Slot;
import mb.nabl2.stratego.StrategoBlob;

/**
 * Factory with helpers for creating DOT files.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public abstract class DotFactory {
	private static final Pattern FRAME_PATTERN = Pattern.compile("FrameRef\\((.+)\\)");
	private static final Pattern CONTINUATION_PATTERN = Pattern.compile("Continuation\\((.+)\\)");
	private static final String[] COLORS = {"green", "purple", "hotpink", 
											"dodgerblue4", "chocolate4", 
											"chocolate", "darkslategray", 
											"aquamarine", "yellowgreen", 
											"khaki3", "indianred3"};
	private static HashMap<String, String> color_map = new HashMap<>();
		
	/**
	 * Generate the id used in the DOT file for a given frame.
	 * 
	 * @param frame
	 * 		The frame to generate the id for
	 * @return
	 * 		The generated id
	 */
	public static String frame(Frame frame) {
		return frame(frame.getId());
	}
	
	/**
	 * Generate the id used in the DOT file for a frame with the given id.
	 * 
	 * @param id
	 * 		The frame id to generate the id for
	 * @return
	 * 		The generated id
	 */
	public static String frame(String id) {
		return "dataFrame_"+ id;
	}
	
	/**
	 * Generate the id used in the DOT file for a given block.
	 * 
	 * @param block
	 * 		The block to generate the id for
	 * @return
	 * 		The generated id
	 */
	public static String block(Block block) {
		return block(block.getName());
	}
	
	/**
	 * Generate the id used in the DOT file for a block with the given name.
	 * 
	 * @param name
	 * 		The block name to generate the id for
	 * @return
	 * 		The generated id
	 */
	public static String block(String name) {
		return "block_"+ name;
	}

	/**
	 * Generate the id used in the DOT file for the controlframe of the given frame.
	 * 
	 * @param frame
	 * 		The controlframe
	 * @return
	 * 		The generated id
	 */
	public static String controlFrame(ControlFrame frame) {
		return "controlFrame_"+ frame.getId();
	}
	
	/**
	 * Generate the id used in the DOT file for the local stack of the given frame.
	 * 
	 * @param frame
	 * 		The frame that holds the stack
	 * @return
	 * 		The generated id
	 */
	public static String stack(ControlFrame frame) {
		return "stack_"+ frame.getId();
	}
	
	/**
	 * Generate a node with the given id and label.
	 * 
	 * @param id
	 * 		The DOT id of this node
	 * @param body
	 * 		The label contents of this node
	 * @return
	 * 		A String containing the DOT node
	 */
	public static String node(String id, String body) {
		return id + " [label=\"" + body + "\"];";
	}

	/**
	 * Create a DOT link between the two DOT ids.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String link(String from, String to) {
		return from + " -> " + to + ";";
	}
	
	/**
	 * Create a DOT link between the two DOT ids.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @param label
	 * 		The label displayed on this link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String link(String from, String to, String label) {
		return from + " -> " + to + " [label=" + label + "];";
	}
	
	/**
	 * Create a DOT link representing a reference to a frame.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String referenceLink(String from, String to) {
		return from + " -> " + to + " [color=blue, style=dashed];";
	}
	
	/**
	 * Create a DOT link representing a reference to a control frame.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String controlReferenceLink(String from, String to) {
		return from + " -> " + to + " [color=goldenrod, style=dashed];";
	}
	
	/**
	 * Create a DOT link representing a reference to a stack.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String stackLink(String from, String to) {
		return from + ":stack -> " + to + ":head [color=red, style=dashed];";
	}
	
	/**
	 * Create a DOT link representing a reference to an operand stack.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String dataFrameLink(String from, String to) {
		return from + ":frame -> " + to + ":id [color=black, style=dashed];";
	}

	/**
	 * Create a DOT link representing a reference to an instruction in a block.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String blockLink(String from, String to) {
		return from + ":pc -> " + to + " [style=dotted];";
	}
	
	/**
	 * Create a DOT link representing a continuation path.
	 * 
	 * @param from
	 * 		The from location of the link
	 * @param to
	 * 		The to location of the link
	 * @return
	 * 		A string containing the DOT link
	 */
	public static String continuationLink(String from, String to, String id) {
		String color = getColor(id);
		return from + ":cont_" + id + " -> " + to + " [color=" + color + ", style=dashed];";
	}

	private static boolean addControlFrameRef(List<String> links, String slotRef, String frame_id) {
		if (frame_id.endsWith("__exit")) {
			links.add(controlReferenceLink(slotRef, "finish"));
		} else if (frame_id.endsWith("__catch")) {
			links.add(controlReferenceLink(slotRef, "exception"));
		} else {
			links.add(controlReferenceLink(slotRef, frame_id + ":id"));
			return true;
		}
		return false;
	}
	
	/**
	 * Convert a slot to string.
	 * When the slot contains a reference, this reference is added to the links.
	 * 
	 * @param slot
	 * 		The slot to convert
	 * @param links
	 * 		A mutable list to add the links to
	 * @param slotRef
	 * 		The DOT id of the current slot
	 * @return
	 * 		The value in the slot
	 */
	public static String slotToString(Slot slot, HashMap<String, String> nodes, List<String> links, String slotRef) {
		return termToString(slot.value, nodes, links, slotRef);
	}
	
	/**
	 * Convert a term to string.
	 * When the term is a reference, this reference is added to the links.
	 * 
	 * @param term
	 * 		The term to convert
	 * @param links
	 * 		A mutable list to add the links to
	 * @param slotRef
	 * 		The DOT id of the current slot
	 * @return
	 * 		The value in the term
	 */
	public static String termToString(IStrategoTerm term, HashMap<String, String> nodes, List<String> links, String slotRef) {
		String value = term.toString().replace("\"", "");
		Matcher matcher = FRAME_PATTERN.matcher(value);
		
		if (matcher.matches()) {	// If it is a reference
			Frame frame = (Frame) ((StrategoBlob) term.getSubterm(0)).value();
			String frameRef = frame(frame.getId());
			DotFrameFactory.build(frame, nodes, links);
			links.add(referenceLink(slotRef, frameRef + ":id"));
			return "FrameRef(" + frame.getId() + ")";
		}
		
		matcher = CONTINUATION_PATTERN.matcher(value);
		if (matcher.matches()) {	// If it is a continuation
			ControlFrame frame = (ControlFrame) ((StrategoBlob) term.getSubterm(0)).value();
			String frameRef = controlFrame(frame);
			if (addControlFrameRef(links, slotRef, frameRef)) {
				DotControlFrameFactory.build(frame, nodes, links);
			}
			return "Continuation(" + frame.getId() + ")";
		}
		return value;
	}
		
	private static String getColor(String id) {
		String color;
		if (color_map.containsKey(id)) {
			color = color_map.get(id);
		} else {
			int idx = color_map.size() % COLORS.length;
			color = COLORS[idx];
			color_map.put(id, color);
		}
		return color;
	}
}

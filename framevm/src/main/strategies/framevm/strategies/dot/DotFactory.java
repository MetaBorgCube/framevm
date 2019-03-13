package framevm.strategies.dot;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.util.Block;
import framevm.strategies.util.Frame;
import framevm.strategies.util.Slot;

/**
 * Factory with helpers for creating DOT files.
 * @see <a href="https://en.wikipedia.org/wiki/DOT_(graph_description_language)">DOT (graph descriptionlanguage)</a>
 */
public abstract class DotFactory {
	private static final Pattern FRAME_PATTERN = Pattern.compile("FrameRef\\((.+)\\)");
	private static final Pattern CONTINUATION_PATTERN = Pattern.compile("Continuation\\((.+),.+\\)");
		
		
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
			return "frame_"+ id;
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
		 * Generate the id used in the DOT file for the operandstack of the given frame.
		 * 
		 * @param frame
		 * 		The frame that holds the opstack
		 * @return
		 * 		The generated id
		 */
		public static String operandStack(Frame frame) {
			return "opstack_"+ frame.getId();
		}
		
		/**
		 * Generate the id used in the DOT file for the local stack of the given frame.
		 * 
		 * @param frame
		 * 		The frame that holds the stack
		 * @return
		 * 		The generated id
		 */
		public static String stack(Frame frame) {
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
		public static String opstackLink(String from, String to) {
			return from + ":opstack -> " + to + ":head [color=black, style=dashed];";
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
			return from + ":block -> " + to + " [style=dotted];";
		}
		
		/**
		 * Create a DOT link representing a return path.
		 * 
		 * @param from
		 * 		The from location of the link
		 * @param to
		 * 		The to location of the link
		 * @return
		 * 		A string containing the DOT link
		 */
		public static String returnLink(String from, String to) {
			return from + ":ret -> " + to + " [color=green, style=dashed];";
		}
		
		/**
		 * Create a DOT link representing an exception path.
		 * 
		 * @param from
		 * 		The from location of the link
		 * @param to
		 * 		The to location of the link
		 * @return
		 * 		A string containing the DOT link
		 */
		public static String exceptionLink(String from, String to) {
			return from + ":ex -> " + to + " [color=purple, style=dashed];";
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
		public static String slotToString(Slot slot, List<String> links, String slotRef) {
			return termToString(slot.value, links, slotRef);
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
		public static String termToString(IStrategoTerm term, List<String> links, String slotRef) {
			String value = term.toString().replace("\"", "");
			Matcher matcher = FRAME_PATTERN.matcher(value);
			
			if (matcher.matches()) {	// If it is a reference
				String frameRef = frame(matcher.group(1));
				addFrameRef(links, slotRef, frameRef);
			}
			
			matcher = CONTINUATION_PATTERN.matcher(value);
			
			if (matcher.matches()) {	// If it is a continuation
				String frameRef = frame(matcher.group(1));
				addFrameRef(links, slotRef, frameRef);
			}
			return value;
		}

		/**
		 * Add a reference to a frame to the given set of links.
		 * If this frame happens to be the _exit or _catch frame,
		 * the links are correctly drawn to the end points.
		 * 
		 * @param links
		 * 		a list of links to add to
		 * @param slotRef
		 * 		the current slot reference as starting point of the link
		 * @param frame_id
		 * 		the frame to link to
		 */
		private static void addFrameRef(List<String> links, String slotRef, String frame_id) {
			if (frame_id.equals("frame__exit")) {
				links.add(referenceLink(slotRef, "finish"));
			} else if (frame_id.equals("frame__catch")) {
				links.add(referenceLink(slotRef, "exception"));
			} else {
				links.add(referenceLink(slotRef, frame_id + ":id"));
			}
		}
}

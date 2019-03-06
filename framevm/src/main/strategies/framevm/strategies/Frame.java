package framevm.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoTerm;
import framevm.strategies.util.Link;
import framevm.strategies.util.OperandStack;
import framevm.strategies.util.Slot;

/**
 * Class representing a frame.
 * A frame can be made executable by attaching an operand stack to it.
 */
public class Frame {
	private OperandStack operandStack;
	private List<Slot> slots;
	private HashMap<String, Link> links;
	private String id;

	/**
	 * Constructor for a new frame with the given id.
	 * 
	 * @param id
	 * 		The id of this frame
	 */
	public Frame(String id) {
		this.operandStack = null;
		this.slots = new ArrayList<>();
		this.links = new HashMap<>();
		this.id = id;
	}
	
	/**
	 * Link this frame to the given frame.
	 * 
	 * @param label
	 * 		The label of the {@link Link}
	 * @param parent
	 * 		The {@link Frame} to link to
	 */
	public void link(String label, Frame parent) {
		links.put(label, new Link(label, parent));
	}
	
	/**
	 * Set the value stored in a {@link Slot}.
	 * Creates a {@link Slot} when needed (Do not rely on this, this will become static probably)
	 * 
	 * @param id 
	 * 		The location of the {@link Slot} in this frame
	 * @param value 
	 * 		The value to store in the {@link Slot}
	 */
	public void set(int id, IStrategoTerm value) {
		if (id + 1 < slots.size()) {	// Slot exists
			slots.get(id).value = value;
		} else {						// Expand frame
			slots.add(id, new Slot(value));
		}
	}
	
	/**
	 * Mark this frame as executable by setting up its {@link OperandStack}.
	 */
	public void setExecutable() {
		this.operandStack = new OperandStack();
	}

	/**
	 * @return
	 * 		The {@link OperandStack} of this frame
	 */
	public OperandStack getOperandStack() {
		return operandStack;
	}
	
	/**
	 * Get a {@link Slot} with the given label.
	 * As this label is a String, it can only be the return slot.
	 * 
	 * @param lbl
	 * 		The slot to get
	 * @return
	 * 		The requested slot 
	 */
	public Slot getSlot(String lbl) {
		if ("r".equals(lbl)) {
			return this.operandStack.getReturnValue();
		}
		return null;
	}

	/**
	 * Get a {@link Slot} with the given id
	 * 
	 * @param id
	 * 		The id to get
	 * @param create
	 * 		Whether to create the slot if it does not exist
	 * @return
	 * 		The {@link Slot}, or <code>null</code> if not found
	 */
	public Slot getSlot(int id, boolean create) {
		if (id < slots.size()) {
			return slots.get(id);
		} else {
			if (create) {
				Slot slot = new Slot(null);
				slots.add(id, slot);
				return slot;
			} else {
				return null;
			}
		}
	}

	/**
	 * Get the {@link Link} with the given id.
	 * 
	 * @param linkId
	 * 		The id of the requested link
	 * @return
	 * 		The requested link
	 */
	public Link getLink(String linkId) {
		return links.get(linkId);
	}
	
	/**
	 * @return
	 * 		The id of this frame
	 */
	public String getId() {
		return id;
	}

	@Override
	public String toString() {
		String id = "id: " + this.getId();
		String stack = "Stack: " + (this.operandStack == null ? "null" : this.operandStack.toString());
		String links = "Links: " + this.links.toString();
		String slots = "Slots: " + this.slots.toString();
		return "Frame(\n\t\t" + id + ",\n\t\t" + stack + ",\n\t\t" + links + ",\n\t\t" + slots + "\n\t)";
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public Collection<Link> links() {
		return this.links.values();
	}
}

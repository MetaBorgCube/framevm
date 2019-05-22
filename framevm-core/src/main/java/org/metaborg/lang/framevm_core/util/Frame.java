package org.metaborg.lang.framevm_core.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * Class representing a frame.
 * A frame can be made executable by attaching an operand stack to it.
 */
public class Frame {
	private Slot[] slots;
	private Link[] links;
	private String id;

	/**
	 * Constructor for a new frame with the given id.
	 * 
	 * @param id
	 * 		The id of this frame
	 */
	public Frame(String id, int slots, int links) {
		this.slots = new Slot[slots];
		this.links = new Link[links];
		this.id = id;
	}
	
	public Frame(String id, Frame original) {
		this.slots = new Slot[original.slots.length];
		for (int i = 0; i < this.slots.length; i++) {
			Slot slot = original.slots[i];
			slots[i] = slot;
		}
		this.links = new Link[original.links.length];
		for (int i = 0; i < this.links.length; i++) {
			Link link = original.links[i];
			if (link == null) continue;
			links[i] = new Link(link.linkId, link.target);
		}
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
	public void link(int idx, String label, Frame parent) {
		links[idx] = new Link(label, parent);
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
		getSlot(id).update(value);
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
	public Slot getSlot(int id) {
		if (id >= slots.length) throw new ArrayIndexOutOfBoundsException("Slot with index " + id + " does not exist");

		Slot slot = slots[id];
		if (slot == null) {
			Slot newSlot = new Slot(null);
			slots[id] = newSlot;
			return newSlot;
		} else {
			return slot;
		}
	}

	/**
	 * Get the {@link Link} with the given index.
	 * 
	 * @param linkId
	 * 		The index of the requested link
	 * @return
	 * 		The requested link
	 */
	public Link getLink(int linkIdx) {
		if (linkIdx >= links.length) return null;
		return links[linkIdx];
	}
	
	/**
	 * @return
	 * 		The id of this frame
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return
	 * 		the slots of this frame
	 */
	public Slot[] getSlots() {
		return slots;
	}

	/**
	 * @return
	 * 		the links of this frame
	 */
	public Link[] getLinks() {
		return links;
	}
	
	@Override
	public String toString() {
		return "Frame(" + this.id + ")";
	}
}

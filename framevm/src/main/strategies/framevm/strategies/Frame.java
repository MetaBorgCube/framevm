package framevm.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoTerm;

import framevm.strategies.util.Link;
import framevm.strategies.util.OperandStack;
import framevm.strategies.util.Routine;
import framevm.strategies.util.Slot;

public class Frame {
	private OperandStack operandStack;
	public List<Slot> slots;
	private HashMap<String, Link> links;
	
	// TODO: remove this, it shouldn't be needed with proper get/set resolve
	public String id;

	public Frame(String id) {
		this.operandStack = null;
		this.slots = new ArrayList<>();
		this.links = new HashMap<>();
		this.id = id;
	}
	
	public void link(String id, Frame parent) {
		links.put(id, new Link(id, parent));
	}
	
	public void set(int id, IStrategoTerm value) {
		if (id + 1 < slots.size()) {	// Slot exists
			slots.get(id).value = value;
		} else {						// Expand frame
			slots.add(id, new Slot(value));
		}
	}
	
	public void setExecutable(Routine routine, Routine returnAddr, Frame returnFrame) {
		this.operandStack = new OperandStack(routine, returnAddr, returnFrame);
	}

	public OperandStack getOperandStack() {
		return operandStack;
	}
	
	public Slot getSlot(String lbl) {
		if ("r".equals(lbl)) {
			return this.operandStack.getReturnValue();
		}
		return null;
	}

	/**
	 * Get Slot, create one if not present
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

	public Link getLink(String linkId) {
		return links.get(linkId);
	}
	
	@Override
	public String toString() {
		String id = "id: " + this.id;
		String stack = "Stack: " + (this.operandStack == null ? "null" : this.operandStack.toString());
		String links = "Links: " + this.links.toString();
		String slots = "Slots: " + this.slots.toString();
		return "Frame(\n\t\t" + id + ",\n\t\t" + stack + ",\n\t\t" + links + ",\n\t\t" + slots + "\n\t)";
	}
}

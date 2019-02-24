package framevm.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.spoofax.terms.StrategoList;
import org.spoofax.terms.StrategoString;

import framevm.strategies.util.OperandStack;
import framevm.strategies.util.Routine;
import framevm.strategies.util.Slot;

public class Frame {
	private OperandStack operandStack;
	public List<Slot> slots;
	private HashMap<String, Frame> parents;
	
	// TODO: remove this, it shouldn't be needed with proper get/set resolve
	public String id;

	public Frame(String id) {
		this.operandStack = null;
		this.slots = new ArrayList<>();
		this.parents = new HashMap<>();
		this.id = id;
	}
	
	public void link(String id, Frame parent) {
		parents.put(id, parent);
	}
	
	public void set(int id, IStrategoTerm value) {
		if (id + 1 < slots.size()) {	// Slot exists
			slots.get(id).value = value;
		} else {						// Expand frame
			slots.add(id, new Slot(value));
		}
	}
	
	public void setExecutable(Routine routine, Routine returnAddr) {
		this.operandStack = new OperandStack(routine, returnAddr);
	}

	public OperandStack getOperandStack() {
		return operandStack;
	}
	
	public Slot resolve(StrategoList path, boolean create) {
		Frame current = this;
		Slot res = null;
		
		for (IStrategoTerm term : path) {
			int idx = Integer.valueOf(((StrategoString) term).stringValue());
			res = current.getSlot(idx, create);

			if (res.isFramePointer) {
				//TODO
//				current = res.getFrame();
			}
		}
		return res;
	}
	
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
}

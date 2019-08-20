package org.metaborg.lang.framevm_core.util;

import org.spoofax.interpreter.terms.IStrategoTerm;

/**
 * A labeled block containing instructions
 */
public class Block {
	private String name;
	private IStrategoTerm[] body;
	private int size;
	
	/**
	 * Constructor of a block with given id and instructions.
	 * 
	 * @param name
	 * 		The name of this block
	 * @param body
	 * 		The instructions contained in this block
	 */
	public Block(String name, IStrategoTerm[] body, int size) {
		this.name = name;
		this.body = body;
		this.size = size;
	}

	/**
	 * @return
	 * 		The name of this block
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the instruction at the given index.
	 * 
	 * @param idx
	 * 		The index of the instruction to get
	 * @return
	 * 		The instruction or <code>null</code> if index is out of range
	 */
	public IStrategoTerm getInstr(int idx) {
		if (idx >= body.length) return null;
		return body[idx];
	}

	public int size() {
		return body.length;
	}

	public int getSize() {
		return size;
	}
}
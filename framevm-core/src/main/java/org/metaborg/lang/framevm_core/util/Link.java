package org.metaborg.lang.framevm_core.util;

/**
 * A link to a {@link frame}.
 */
public class Link {
	public String linkId;
	public Frame target;

	/**
	 * Create a link to a target with the given id.
	 * 
	 * @param linkId
	 * 		The id of this link
	 * @param target
	 */
	public Link(String linkId, Frame target) {
		this.target = target;
		this.linkId = linkId;
	}
	
	@Override
	public String toString() {
		return "=>" + target.getId();
	}

}

package framevm.strategies.util;

import framevm.strategies.Frame;

public class Link {

	public String linkId;
	public Frame target;

	public Link(String linkId, Frame target) {
		this.target = target;
		this.linkId = linkId;
	}
	
	@Override
	public String toString() {
		return "=>" + target.id;
	}

}

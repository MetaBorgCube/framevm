package org.metaborg.lang.framevm_core.util;

public class Continuation {

	private ControlFrame controlFrame;
	private Block block;
	private Memory memory;
	private Frame frame;
	
	public Continuation(ControlFrame frame, Block block, Memory memory) {
		this.controlFrame = frame;
		this.block = block;
		this.memory = memory;
		this.frame = controlFrame.getCurrentFrame();
	}
	
	public ControlFrame getControlFrame() {
		return this.controlFrame;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public Frame getFrame() {
		return this.frame;
	}

	public void update(Continuation cont) {
		this.controlFrame = cont.getControlFrame();
		this.block = cont.getBlock();
		this.memory = cont.getMemory();
		this.frame = cont.getFrame();
	}

	public Memory getMemory() {
		return memory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Continuation [controlFrame=" + controlFrame + ", block=" + block + ", frame=" + frame + "]";
	}
	
	
}
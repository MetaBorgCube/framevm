package framevm.strategies.util;

public class Continuation {

	private ControlFrame controlFrame;
	public String id;
	
	public Continuation(String id, ControlFrame frame) {
		this.controlFrame = frame;
		this.id = id;
	}
	
	public ControlFrame value() {
		return this.controlFrame;
	}

	public void update(ControlFrame newFrame) {
		this.controlFrame = newFrame;
	}
}

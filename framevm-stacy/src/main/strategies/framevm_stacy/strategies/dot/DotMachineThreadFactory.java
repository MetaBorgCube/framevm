package framevm_stacy.strategies.dot;

import java.util.HashMap;
import java.util.List;

import framevm_stacy.strategies.util.ControlFrame;
import framevm_stacy.strategies.util.MachineThread;

public class DotMachineThreadFactory extends DotFactory {

	public static String build(MachineThread thread, HashMap<String, String> nodeList, List<String> linkList) {
		ControlFrame controlFrame = thread.getControlFrame();
		return DotControlFrameFactory.build(controlFrame, nodeList, linkList);
	}

}

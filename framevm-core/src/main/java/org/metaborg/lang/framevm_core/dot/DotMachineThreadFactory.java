package org.metaborg.lang.framevm_core.dot;

import java.util.HashMap;
import java.util.List;

import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.MachineThread;

public class DotMachineThreadFactory extends DotFactory {

	public static String build(MachineThread thread, HashMap<String, String> nodeList, List<String> linkList) {
		ControlFrame controlFrame = thread.getControlFrame();
		return DotControlFrameFactory.build(controlFrame, nodeList, linkList);
	}

}

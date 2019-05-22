package org.metaborg.lang.framevm_core.dot;

import java.util.HashMap;
import java.util.List;

import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
import org.metaborg.lang.framevm_core.util.StackControlFrame;

public class DotControlFrameFactory {

	public static String build(ControlFrame controlFrame, HashMap<String, String> nodes, List<String> links) {
		if (controlFrame instanceof StackControlFrame) {
			return DotStackControlFrameFactory.build((StackControlFrame) controlFrame, nodes, links);
		} else {
			return DotRegisterControlFrameFactory.build((RegisterControlFrame) controlFrame, nodes, links);
		}
	}
}

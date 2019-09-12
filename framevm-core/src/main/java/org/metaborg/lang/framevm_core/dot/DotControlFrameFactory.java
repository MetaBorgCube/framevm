package org.metaborg.lang.framevm_core.dot;

import java.util.HashMap;
import java.util.List;
import org.metaborg.lang.framevm_core.util.Continuation;
import org.metaborg.lang.framevm_core.util.ControlFrame;
import org.metaborg.lang.framevm_core.util.Frame;
import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
import org.metaborg.lang.framevm_core.util.StackControlFrame;

public class DotControlFrameFactory extends DotFactory {
	
	public static String build(ControlFrame frame, HashMap<String, String> nodes, List<String> links) {
		String name = controlFrame(frame);
		if (nodes.containsKey(name)) {
			return ""; // Node is already created
		} else {
			nodes.put(name, "");	// Mark this as existing, eventhough we are not finished creating it (This prevents infinite looping)
		}
		
		// Add links to the executing instruction and the stack
//		if (frame.getBlock() != null) {
//			int count = frame.getInstr_count();
//			String target = block(frame.getBlock()) + ":" + (Math.max(0, count - 1));
//			links.add(blockLink(name, target));
//		}
		links.add(memLink(name, DotFactory.memory(frame)));
		
		if (frame.getCurrentFrame() != null) {
			Frame dataFrame = frame.getCurrentFrame();
			DotFrameFactory.build(dataFrame, nodes, links);
			links.add(dataFrameLink(name, frame(dataFrame)));
		}
		
		// Link the continuations
		String contSlots = "";
		String contIds = "";
		Continuation[] continuations = frame.getContinuations();
		for (int i = 0; i < continuations.length; i++) {
//			Continuation cont = continuations[i];
//			if (cont == null) {
//				contSlots += " | c" + i;
//				contIds += " | null";
//			} else {
//				String target_id = cont.value().getId();
//				contSlots += " | " + cont.id;
//				
//				contIds += " | <cont_" + cont.id + ">";
//				if ("_exit".equals(target_id)) {
//					links.add(continuationLink(name, "finish", cont.id));
//				} else if ("_catch".equals(target_id)) {
//					links.add(continuationLink(name, "exception", cont.id));
//				} else {
//					ControlFrame target = cont.value();
//					if (!nodes.containsKey(controlFrame(target))) {
//						DotControlFrameFactory.build(target, nodes, links);
//					}
//					links.add(continuationLink(name, controlFrame(target) + ":id", cont.id));
//				}
//			}
		}
		if (contSlots.length() == 0) {
			contSlots = " |";
		}
		if (contIds.length() == 0) {
			contIds = " |";
		}
		
		String memType;
		if (frame instanceof StackControlFrame) {
			memType = DotStackControlFrameFactory.getMemType();
			DotStackControlFrameFactory.buildmemory((StackControlFrame) frame, nodes, links);
		} else {
			memType = DotRegisterControlFrameFactory.getMemType();
//			memType = "";
			DotRegisterControlFrameFactory.buildmemory((RegisterControlFrame) frame, nodes, links);
		}
		
		// Generate main node
		String dotString = node(name, "{<id>" + frame.getId() + " | {{PC | Frame | " + memType + contSlots + "}| { <pc> | <frame> | <mem>" + contIds + "}}}");
		nodes.put(name, dotString);
		
		
		return name;
	}
}

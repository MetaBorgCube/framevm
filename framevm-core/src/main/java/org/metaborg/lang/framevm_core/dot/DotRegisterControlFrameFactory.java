//package org.metaborg.lang.framevm_core.dot;
//
//import java.util.HashMap;
//import java.util.List;
//
//import org.metaborg.lang.framevm_core.util.RegisterControlFrame;
//import org.spoofax.interpreter.terms.IStrategoTerm;
//
//public class DotRegisterControlFrameFactory extends DotControlFrameFactory {
//
//	public static void buildmemory(RegisterControlFrame controlFrame, HashMap<String, String> nodes, List<String> links) {
//		String slots = "";
//		String values = "";
//		String registerName = memory(controlFrame);
//		
//		IStrategoTerm[] locals = controlFrame.getLocals();
//		for(int i =0; i < locals.length; i++) {
//			IStrategoTerm term = locals[i];
//			String termString;
//			if (term == null) {
//				termString = "null";
//			} else {
//				termString = termToString(term, nodes, links, registerName + ":" + i);
//			}
//			slots += " | r" + i;
//			values += " | <" + i + "> " + termString;
//		}
//		if (!slots.isEmpty()) {
//			slots = slots.substring(2);
//			values = values.substring(2);
//		}
//		nodes.put(registerName, node(registerName, "{<head> | {{" + slots + "} | {" + values + "}}}"));
//	}
//	
//	public static String getMemType() {
//		return "Register";
//	}
//
//}

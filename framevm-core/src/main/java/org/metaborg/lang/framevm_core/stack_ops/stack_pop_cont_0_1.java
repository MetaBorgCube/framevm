package org.metaborg.lang.framevm_core.stack_ops;

public class stack_pop_cont_0_1 extends stack_pop {
	public static stack_pop_cont_0_1 instance = new stack_pop_cont_0_1();

	@Override
	protected boolean accepted(String name) {
		return "Continuation".equals(name);
	}

	@Override
	protected String accepted() {
		return "Continuation";
	}
}

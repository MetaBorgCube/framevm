package org.metaborg.lang.framevm_core.register;

public class rgr_get_int_0_1 extends rgr_get {
	public static rgr_get_int_0_1 instance = new rgr_get_int_0_1();

	@Override
	protected boolean accepted(String name) {
		return "IntV".equals(name);
	}

	@Override
	protected String accepted() {
		return "IntV";
	}
}

package framevm_stacy.strategies.stack_ops;

public class stack_pop_closure_0_1 extends stack_pop {
	public static stack_pop_closure_0_1 instance = new stack_pop_closure_0_1();

	@Override
	protected boolean accepted(String name) {
		return "ClosV".equals(name);
	}

	@Override
	protected String accepted() {
		return "ClosV";
	}
}

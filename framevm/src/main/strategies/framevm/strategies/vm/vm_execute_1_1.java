package framevm.strategies.vm;

import org.spoofax.interpreter.terms.IStrategoTerm;
import org.strategoxt.lang.Context;
import org.strategoxt.lang.Strategy;

import framevm.strategies.util.Environment;
import mb.nabl2.stratego.StrategoBlob;


public class vm_execute_1_1 extends Strategy {
	public static vm_execute_1_1 instance = new vm_execute_1_1();

	@Override
	public IStrategoTerm invoke(Context context, IStrategoTerm current, Strategy eval, IStrategoTerm envBlob) {
		Environment env = (Environment) ((StrategoBlob) envBlob).value();

		while (env.currentFrame.getOperandStack().hasNextInstruction()) {
			IStrategoTerm instruction = env.currentFrame.getOperandStack().nextInstruction();
			IStrategoTerm tuple = context.getFactory().makeTuple(instruction, new StrategoBlob(env));
			if (eval.invoke(context, tuple) == null) break;
		}
		return new StrategoBlob(env);
	}
}

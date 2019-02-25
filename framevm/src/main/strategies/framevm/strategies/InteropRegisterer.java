package framevm.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

import framevm.strategies.frame_ops.frame_get_link_0_1;
import framevm.strategies.frame_ops.frame_get_slot_0_1;
import framevm.strategies.frame_ops.frame_new_0_1;
import framevm.strategies.frame_ops.frame_set_0_1;
import framevm.strategies.stack_ops.stack_pop_0_1;
import framevm.strategies.stack_ops.stack_push_0_1;

public class InteropRegisterer extends JavaInteropRegisterer {
    public InteropRegisterer() {
        super(new Strategy[] {
        		next_instr_0_0.instance,
        		
        		init_vm_0_0.instance,
        		start_vm_0_1.instance,
        		store_routine_0_1.instance,
        		stop_vm_0_1.instance,
        		vm_print_0_1.instance,
        		vm_jump_0_1.instance,
        		
        		vm_call_0_1.instance,
        		vm_return_0_1.instance,

        		stack_push_0_1.instance,
        		stack_pop_0_1.instance,
        		
        		frame_new_0_1.instance,
        		frame_get_link_0_1.instance,
        		frame_get_slot_0_1.instance,
        		frame_set_0_1.instance,
        		frame_link_0_1.instance,
        		frame_this_0_1.instance
        });
    }
}

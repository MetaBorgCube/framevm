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
        		vm_init_0_0.instance,
        		vm_start_0_1.instance,
        		vm_store_block_0_1.instance,
        		vm_stop_0_1.instance,
        		vm_print_0_1.instance,
        		vm_debug_0_1.instance,
        		vm_jump_0_1.instance,
        		vm_get_cc_0_1.instance,
        		vm_set_cc_0_1.instance,
        		
        		vm_call_0_1.instance,
        		vm_return_0_1.instance,

        		stack_push_0_1.instance,
        		stack_pop_0_1.instance,
        		
        		frame_new_0_1.instance,
        		frame_get_link_0_1.instance,
        		frame_get_slot_0_1.instance,
        		frame_set_0_1.instance,
        		frame_link_0_1.instance,
        		frame_this_0_1.instance,
        		
        		vm_execute_1_1.instance        		
        });
    }
}

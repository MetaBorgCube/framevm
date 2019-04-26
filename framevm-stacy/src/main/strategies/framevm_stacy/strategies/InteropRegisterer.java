package framevm_stacy.strategies;

import org.strategoxt.lang.JavaInteropRegisterer;
import org.strategoxt.lang.Strategy;

import framevm_stacy.strategies.continuation.cont_call_0_1;
import framevm_stacy.strategies.continuation.cont_copy_0_1;
import framevm_stacy.strategies.continuation.cont_get_0_1;
import framevm_stacy.strategies.continuation.cont_set_0_1;
import framevm_stacy.strategies.continuation.cont_transfer_0_1;
import framevm_stacy.strategies.continuation.cont_this_0_1;
import framevm_stacy.strategies.frame_ops.frame_copy_0_1;
import framevm_stacy.strategies.frame_ops.frame_get_link_0_1;
import framevm_stacy.strategies.frame_ops.frame_get_slot_0_1;
import framevm_stacy.strategies.frame_ops.frame_link_0_1;
import framevm_stacy.strategies.frame_ops.frame_new_0_1;
import framevm_stacy.strategies.frame_ops.frame_set_current_0_1;
import framevm_stacy.strategies.frame_ops.frame_set_slot_0_1;
import framevm_stacy.strategies.frame_ops.frame_size_0_1;
import framevm_stacy.strategies.frame_ops.frame_this_0_1;
import framevm_stacy.strategies.stack_ops.stack_pop_any_0_1;
import framevm_stacy.strategies.stack_ops.stack_pop_closure_0_1;
import framevm_stacy.strategies.stack_ops.stack_pop_cont_0_1;
import framevm_stacy.strategies.stack_ops.stack_pop_frame_0_1;
import framevm_stacy.strategies.stack_ops.stack_pop_int_0_1;
import framevm_stacy.strategies.stack_ops.stack_push_0_1;
import framevm_stacy.strategies.stack_ops.stack_size_0_1;
import framevm_stacy.strategies.vm.vm_cont_new_0_1;
import framevm_stacy.strategies.vm.vm_debug_0_1;
import framevm_stacy.strategies.vm.vm_execute_1_1;
import framevm_stacy.strategies.vm.vm_init_0_0;
import framevm_stacy.strategies.vm.vm_jump_0_1;
import framevm_stacy.strategies.vm.vm_print_0_1;
import framevm_stacy.strategies.vm.vm_print_chars_0_1;
import framevm_stacy.strategies.vm.vm_start_0_1;
import framevm_stacy.strategies.vm.vm_stop_0_1;
import framevm_stacy.strategies.vm.vm_store_block_0_1;

public class InteropRegisterer extends JavaInteropRegisterer {
	public InteropRegisterer() {
		super(new Strategy[] {        		
				vm_init_0_0.instance,
				vm_start_0_1.instance,
				vm_store_block_0_1.instance,
				vm_stop_0_1.instance,

				vm_print_0_1.instance,
				vm_print_chars_0_1.instance,
				vm_debug_0_1.instance,
				vm_jump_0_1.instance,
				vm_cont_new_0_1.instance,

				stack_push_0_1.instance,
				stack_pop_any_0_1.instance,
				stack_pop_int_0_1.instance,
				stack_pop_frame_0_1.instance,
				stack_pop_cont_0_1.instance,
				stack_pop_closure_0_1.instance,
				stack_size_0_1.instance,

				frame_new_0_1.instance,
				frame_size_0_1.instance,
				frame_get_link_0_1.instance,
				frame_get_slot_0_1.instance,
				frame_set_slot_0_1.instance,
				frame_set_current_0_1.instance,
				frame_link_0_1.instance,
				frame_this_0_1.instance,
				frame_copy_0_1.instance,

				vm_execute_1_1.instance,

				cont_get_0_1.instance,
				cont_set_0_1.instance,
				cont_transfer_0_1.instance,
				cont_this_0_1.instance,
				cont_call_0_1.instance,
				cont_copy_0_1.instance
		});
	}
}

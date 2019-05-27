package org.metaborg.lang.framevm_core;

import org.strategoxt.lang.Strategy;

import org.metaborg.lang.framevm_core.continuation.cont_call_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_copy_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_get_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_receive_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_set_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_this_0_1;
import org.metaborg.lang.framevm_core.continuation.cont_transfer_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_copy_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_get_link_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_get_slot_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_link_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_new_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_set_current_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_set_slot_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_size_0_1;
import org.metaborg.lang.framevm_core.frame_ops.frame_this_0_1;
import org.metaborg.lang.framevm_core.register.rgr_get_any_0_1;
import org.metaborg.lang.framevm_core.register.rgr_get_closure_0_1;
import org.metaborg.lang.framevm_core.register.rgr_get_cont_0_1;
import org.metaborg.lang.framevm_core.register.rgr_get_frame_0_1;
import org.metaborg.lang.framevm_core.register.rgr_get_int_0_1;
import org.metaborg.lang.framevm_core.register.rgr_set_0_1;
import org.metaborg.lang.framevm_core.register.rgr_size_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_pop_any_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_pop_closure_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_pop_cont_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_pop_frame_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_pop_int_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_push_0_1;
import org.metaborg.lang.framevm_core.stack_ops.stack_size_0_1;
import org.metaborg.lang.framevm_core.vm.vm_cont_new_0_1;
import org.metaborg.lang.framevm_core.vm.vm_debug_0_1;
import org.metaborg.lang.framevm_core.vm.vm_execute_1_1;
import org.metaborg.lang.framevm_core.vm.vm_has_lib_0_1;
import org.metaborg.lang.framevm_core.vm.vm_init_rgr_0_0;
import org.metaborg.lang.framevm_core.vm.vm_init_stc_0_0;
import org.metaborg.lang.framevm_core.vm.vm_jump_0_1;
import org.metaborg.lang.framevm_core.vm.vm_print_0_1;
import org.metaborg.lang.framevm_core.vm.vm_print_chars_0_1;
import org.metaborg.lang.framevm_core.vm.vm_start_0_1;
import org.metaborg.lang.framevm_core.vm.vm_stop_0_1;
import org.metaborg.lang.framevm_core.vm.vm_store_block_0_1;

public class FrameVM {
	public static Strategy[] framevmExtend() {
		return framevmExtend(null);
	}
	public static Strategy[] framevmExtend(Strategy[] original) {
		Strategy[] newStrategies = new Strategy[] {        		
				vm_init_stc_0_0.instance,        		
				vm_init_rgr_0_0.instance,
				vm_start_0_1.instance,
				vm_store_block_0_1.instance,
				vm_stop_0_1.instance,
				vm_has_lib_0_1.instance,

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

				rgr_set_0_1.instance,
				rgr_get_any_0_1.instance,
				rgr_get_int_0_1.instance,
				rgr_get_frame_0_1.instance,
				rgr_get_cont_0_1.instance,
				rgr_get_closure_0_1.instance,
				rgr_size_0_1.instance,

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
				cont_receive_0_1.instance,
				cont_this_0_1.instance,
				cont_call_0_1.instance,
				cont_copy_0_1.instance
		};
		
		if (original == null || original.length == 0) return newStrategies;
		
		// Copy them to the new array
		Strategy[] result = new Strategy[original.length + newStrategies.length];
		for (int i = 0; i < original.length; i++) {
			result[i] = original[i];
		}
		for (int i = 0; i < newStrategies.length; i++) {
			result[i + original.length] = newStrategies[i];
		}
		return result;
	}
}

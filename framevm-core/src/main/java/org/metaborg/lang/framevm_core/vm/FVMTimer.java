package org.metaborg.lang.framevm_core.vm;

import org.metaborg.lang.framevm_core.FVMStrategy;

public abstract class FVMTimer extends FVMStrategy {
	private static long time = 0;
	
	public void start() {
		FVMTimer.time = System.currentTimeMillis();
	}
	
	public long get() {
		return FVMTimer.time;
	}
	
	public long stop() {
		long now = System.currentTimeMillis();
		long duration = now - FVMTimer.time;
		FVMTimer.time = now;
		return duration;
	}
}

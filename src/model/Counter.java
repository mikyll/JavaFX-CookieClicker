package model;

public class Counter {
	private long value;
	
	public Counter() {
		this.value = 0;
	}
	
	public void increase() {
		this.value++;
	}
	public long getValue() {
		return this.value;
	}
}

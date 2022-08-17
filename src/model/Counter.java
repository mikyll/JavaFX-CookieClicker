package model;

public class Counter {
	private int value;
	
	public Counter() {
		this.value = 0;
	}
	
	public void increase() {
		this.value++;
	}
	public int getValue() {
		return this.value;
	}
}

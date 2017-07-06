package com.marving.ds.queue;

/**
 *
 * @title: 优先级队列-堆实现的基本元素类
 * @author: Marvin Guo
 * @create time: 2017年1月2日
 * @param <T>
 */
class HeapElement<T> implements Comparable<HeapElement<T>>{

	private T element;
	private int priority;
	// 设置最小优先级
	private final int MINEST_PRIORITY = -1;

	public HeapElement(T element){

		this.element = element;
		this.priority = MINEST_PRIORITY;
	}

	public HeapElement(T element, int priority){

		this.element = element;
		this.priority = priority;
	}

	@Override
	public int compareTo(HeapElement<T> he) {

		return this.priority-he.getPriority();
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public T getElement() {
		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj){
		if(!HeapElement.class.isInstance(obj)){
			return false;
		}
		HeapElement<T> he = (HeapElement<T>)obj;
		return this.element.equals(he.element);

	}
	@Override
	public int hashCode(){
		return this.element.hashCode();
	}
	@Override
	public String toString(){
		return "[" + this.element + ", " + this.priority + "]";
	}
}
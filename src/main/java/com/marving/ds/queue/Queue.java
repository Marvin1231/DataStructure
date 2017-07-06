package com.marving.ds.queue;

public interface Queue<T>{

	/**
	 * 返回对头元素，并移除该元素
	 * @return
	 */
	public T poll();

	/**
	 * 返回对头元素
	 * @return
	 */
	public T peek();

	/**
	 * 插入elemnt到队尾
	 * @param element
	 */
	public void offer(T element);

	public void offer(T element, int priority);
}

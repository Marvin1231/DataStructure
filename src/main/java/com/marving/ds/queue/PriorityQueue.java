package com.marving.ds.queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @title: 优先级队列(元素值不重复)
 * 方法：入队，出队，获取队头元素，修改优先级
 * @author: Marvin Guo
 * @create time: 2017年1月2日
 * @param <T>
 */
public class PriorityQueue<T> implements Queue<T>, Iterable<HeapElement<T>>{

	private List<HeapElement<T>> heap = new LinkedList<HeapElement<T>>();

	public PriorityQueue(){
	}

	public PriorityQueue(T[] numbers){
		for(T t: numbers)
			offer(t);
	}

	/**
	 * 获取优先级最高元素，并移除
	 */
	@Override
	public T poll() {

		T e = heap.get(0).getElement();
		swap(0,heap.size()-1);
		heap.remove(heap.size()-1);
		maxHeapify(0);
		return e;
	}

	/**
	 * 获取优先级最高元素
	 */
	@Override
	public T peek() {
		return heap.get(0).getElement();
	}

	/**
	 * 元素入队,如果已包含，则不入队
	 */
	@Override
	public void offer(T element) {
		HeapElement<T> he = getHeapElement(element);
		if(he ==null){
			heap.add(new HeapElement<T>(element));
		}
	}

	/**
	 * 元素入队,如果已包含该元素，则修改其优先级
	 */
	@Override
	public void offer(T element,int priority) {

		this.offer(element);
		this.changePriority(element, priority);
	}

	/**
	 * 修改队列优先级
	 * @param i
	 * @param newp
	 */
	private void changePriority(int i, int newp,int tag){

		HeapElement<T> he = heap.get(i);
		int oldp = heap.get(i).getPriority();
		he.setPriority(newp);
		//增大优先级
		if(oldp < newp){
			HeapElement<T> parentHe = heap.get(i/2);
			while(i > 0&&parentHe.getPriority() < he.getPriority()){
				swap(i,i/2);
				i = i/2;
			}
		}
		//减小
		if(oldp > newp){
			maxHeapify(i);
		}
	}

	/**
	 * 改变元素优先级到newp
	 * @param element
	 * @param newp
	 */
	public void changePriority(T element, int newp){
		int index = indexOfElement(element);
		if(index == -1){
			return;
		}
		changePriority(index,newp,0);
	}

	/**
	 * 增加某个元素的优先级，increasep为增加优先级的大小
	 * @param element
	 * @param increasep
	 */
	public void increasePriority(T element, int increasep){
		if(increasep < 0){
			return;
		}
		HeapElement<T> he = getHeapElement(element);
		if(he ==null){
			return;
		}
		changePriority(element,he.getPriority()+increasep);
	}

	/**
	 * 降低某个元素的优先级，decreasep为降低优先级的大小
	 * @param element
	 * @param decreasep
	 */
	public void decreasePriority(T element, int decreasep){
		if(decreasep < 0){
			return;
		}
		HeapElement<T> he = getHeapElement(element);
		if(he ==null){
			return;
		}
		changePriority(element,he.getPriority()- decreasep);
	}

	/**
	 * 维护堆的第index个元素
	 * @param index
	 */
	private void maxHeapify(int index){
		int left = index*2 +1;
		int right = index*2 + 2;
		int largest = index;
		if(left< heap.size() && heap.get(left).getPriority() > heap.get(index).getPriority()){
			largest = left;
		}
		if(right< heap.size() && heap.get(right).getPriority() > heap.get(largest).getPriority()){
			largest = right;
		}
		if(index != largest){
			swap(index,largest);
			maxHeapify(largest);
		}
	}

	/**
	 * List两数之间交换
	 * @param i
	 * @param j
	 */
	private void swap(int i,int j){
		HeapElement<T> hei = heap.get(i);
		HeapElement<T> hej = heap.get(j);
		heap.set(i, hej);
		heap.set(j, hei);
	}

	/**
	 * 获取元素对应的堆对象
	 * @param element
	 * @return
	 */
	private HeapElement<T> getHeapElement(T element){
		HeapElement<T> he = new HeapElement<T>(element);
		for(HeapElement<T> h:heap){
			if(h.equals(he)){
				return h;
			}
		}
		return null;
	}

	/**
	 * 获取元素的位置
	 * @param element
	 * @return
	 */
	private int indexOfElement(T element){
		HeapElement<T> he = new HeapElement<T>(element);
		int index = -1;
		for(int i = 0; i < heap.size();i++){
			if(he.equals(heap.get(i))){
				return i;
			}
		}
		return index;
	}

	/**
	 * 迭代器
	 */
	@Override
	public Iterator<HeapElement<T>> iterator() {
		return new Iterator<HeapElement<T>>(){
			volatile int current = 0;
			@Override
			public boolean hasNext() {

				return current< heap.size();
			}

			@Override
			public synchronized HeapElement<T> next() {
				return heap.get(current++);
			}
		};
	}

	public void printQueue(){
		for(HeapElement<T> item : heap)
			System.out.print(item);
		System.out.println();
	}

	public static void main(String[] args){

		Integer[] numbers ={9,10,11};
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(numbers);
		pq.offer(1,10);
		pq.offer(2,11);
		pq.offer(3,5);
		pq.offer(4,1);
		pq.increasePriority(1, 2);
		pq.decreasePriority(1, 9);
		pq.offer(5);
		pq.offer(4,2);
		pq.printQueue();
		int i = pq.poll();
		System.out.println(i);
		pq.printQueue();
		int j = pq.peek();
		System.out.println(j);
		pq.printQueue();
	}

}





package com.marving.util.callback;
public class PrintDataC<T> implements CallBack<T>{

	@Override
	public void handleData(T data) {

		System.out.println(data);
	}

}

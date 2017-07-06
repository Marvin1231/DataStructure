package com.marving.util.callback;

/**
 * Created by mercop on 2017/7/6.
 * 回调获取最大值
 */
public class GetMaxOfDatasC implements CallBack<Integer>{
	
	private int max = -1;
	@Override
	public void handleData(Integer data) {
		if(data == null){
			data = 0;
		}
		if(max < data){
			max = data;
		}
	}
	
	public int getMax(){
		return max;
	}
}

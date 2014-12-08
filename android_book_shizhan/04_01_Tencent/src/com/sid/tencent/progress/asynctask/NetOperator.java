package com.sid.tencent.progress.asynctask;

//模拟访问网络的操作
public class NetOperator {
	public void operate(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

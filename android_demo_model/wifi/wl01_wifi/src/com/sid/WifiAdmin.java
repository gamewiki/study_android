package com.sid;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiAdmin {

	private WifiManager wifiManager;
	private WifiInfo wifiInfo;
	private List<ScanResult> wifiList;
	private List<WifiConfiguration> wifiConfigurations;
	WifiLock wifiLock;
	public WifiAdmin(Context context){
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		wifiInfo = wifiManager.getConnectionInfo();
	}
	
	public void openWifi(){
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
	}
	
	public void closeWifi(){
		if (!wifiManager.isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		}
	}
	
	//锁定WifiLock，当下载大文件时需要锁定
	public void AcquireWifiLock(){
		wifiLock.acquire();
	}
	
	public void ReleaseWifiLock(){
		if (wifiLock.isHeld()) {
			wifiLock.acquire();
		}
	}
	
	public void createWifiLock(){
		wifiLock = wifiManager.createWifiLock("Test");
	}
	
	//得到配置网络
	public List<WifiConfiguration> getConfiguration(){
		return wifiConfigurations;
	}
	//指定配置好的网络进行连接
	public void ConnectConfiguration(int index){
		if(index>wifiConfigurations.size()){
			return;
		}
		wifiManager.enableNetwork(wifiConfigurations.get(index).networkId, true);
	}
	
	public void startScan(){
		wifiManager.startScan();
		wifiList = wifiManager.getScanResults();
		wifiConfigurations = wifiManager.getConfiguredNetworks();
	}
	
	public List<ScanResult> getWifiList(){
		return wifiList;
	}
	
	//查看扫描结果
	public StringBuilder lookUpScan(){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < wifiList.size(); i++) {
			stringBuilder.append("Index_"+(i+1)+":");
			//将ScanResult信息转化成一个字符包
			//其中转换的信息包括
			stringBuilder.append((wifiList.get(i)).toString());
			stringBuilder.append("\n\r");
		}
		return stringBuilder;
	}
	
	public String getMacAddress(){
		return (wifiInfo == null)?"NULL":wifiInfo.getMacAddress();
	}
	
	public int getIPAddress(){
		return wifiInfo == null?0:wifiInfo.getIpAddress();
	}
	
	public int getNetWorkId(){
		return wifiInfo==null?0:wifiInfo.getNetworkId();
	}
	
	public String getWifiInfo(){
		return wifiInfo==null?"NULL":wifiInfo.toString();
	}
	
	//添加一个网络并连接
	public void addNetWork(WifiConfiguration wcg){
		int wcgId = wifiManager.addNetwork(wcg);
		wifiManager.enableNetwork(wcgId, true);
	}
	
	public void disconectWifi(int netId){
		wifiManager.disableNetwork(netId);
		wifiManager.disconnect();
	}
	
	
}

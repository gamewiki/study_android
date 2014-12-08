package com.sid.sim;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

public class SIMActivity extends ListActivity {
	private TelephonyManager telMgr;
	private List<String> item = new ArrayList<String>();
	private List<String> value = new ArrayList<String>();

	@SuppressWarnings("static-access")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/* 载入main.xml Layout */
		setContentView(R.layout.activity_sim);
		telMgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

		/* 将取得的信息写入List中 */
		/* 取得SIM卡状态 */
		item.add(getResources().getText(R.string.str_list0).toString());
		if (telMgr.getSimState() == telMgr.SIM_STATE_READY) {
			value.add("很好");
		} else if (telMgr.getSimState() == telMgr.SIM_STATE_ABSENT) {
			value.add("没有SIM卡");
		} else {
			value.add("SIM卡处于被锁定或未知的状态");
		}

		/* 取得SIM卡卡号 */
		item.add(getResources().getText(R.string.str_list1).toString());
		if (telMgr.getSimSerialNumber() != null) {
			value.add(telMgr.getSimSerialNumber());
		} else {
			value.add("获取失败");
		}

		/* 取得SIM卡供货商代码 */
		item.add(getResources().getText(R.string.str_list2).toString());
		if (telMgr.getSimOperator().equals("")) {
			value.add("获取失败");
		} else {
			value.add(telMgr.getSimOperator());
		}

		/* 取得SIM卡供货商名称 */
		item.add(getResources().getText(R.string.str_list3).toString());
		if (telMgr.getSimOperatorName().equals("")) {
			value.add("获取失败");
		} else {
			value.add(telMgr.getSimOperatorName());
		}

		/* 取得SIM卡国别 */
		item.add(getResources().getText(R.string.str_list4).toString());
		if (telMgr.getSimCountryIso().equals("")) {
			value.add("获取失败");
		} else {
			value.add(telMgr.getSimCountryIso());
		}

		/* 使用自定义的MyAdapter来将数据传入ListActivity */
		setListAdapter(new MyAdapter(this, item, value));
	}
}

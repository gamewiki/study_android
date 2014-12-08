package org.marsdroid.oauth05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.marsdroid.model.WeiBoData;
import org.marsdroid.model.WeiBoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WeiBoAdapter extends BaseAdapter{
	//WeiBoList�����е����ݣ������˷������������ص���������
	private WeiBoList weiBoList;
	//info���д洢��һ����ȡ�ص�����΢������
	private List<WeiBoData> info = null;
	//View����Ļ���
	private Map<Integer,View> rowViews = new HashMap<Integer,View>();
	private Context context = null;
	
	public WeiBoAdapter(WeiBoList weiBoList,Context context){
		this.weiBoList = weiBoList;
		info = weiBoList.getData().getInfo();
		this.context = context;
	}
	//���ص��е�Adapter���У����������ٸ�item
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return info.size();
	}
	//����λ�ã��õ���Ӧ��item����
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}
	//����λ�ã��õ���Ӧ��item�����ID
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//ListViewͨ������getView()�������õ���Ӧ��View���󣬲�������ʾ��Activity����
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = rowViews.get(position);
		if(rowView == null){
			//����һ��LayoutInflater����
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			//����LayoutInflater�����inflate��������������һ��View����
			rowView = layoutInflater.inflate(R.layout.item, null);
			//�õ���View���е������ؼ�
			TextView nameView = (TextView)rowView.findViewById(R.id.nameId);
			TextView textView = (TextView)rowView.findViewById(R.id.textId);
			//����getItem�����������õ���Ӧλ�õ�weiBoData����
			WeiBoData weiBoData = (WeiBoData)getItem(position);
			nameView.setText(weiBoData.getName());
			textView.setText(weiBoData.getText());
			rowViews.put(position, rowView);
		}
		return rowView;
	}

}

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
	//WeiBoList对象当中的数据，代表了服务器端所返回的所有数据
	private WeiBoList weiBoList;
	//info当中存储了一次所取回的所有微博数据
	private List<WeiBoData> info = null;
	//View对象的缓存
	private Map<Integer,View> rowViews = new HashMap<Integer,View>();
	private Context context = null;
	
	public WeiBoAdapter(WeiBoList weiBoList,Context context){
		this.weiBoList = weiBoList;
		info = weiBoList.getData().getInfo();
		this.context = context;
	}
	//返回当中的Adapter当中，共包含多少个item
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return info.size();
	}
	//根据位置，得到相应的item对象
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return info.get(position);
	}
	//根据位置，得到相应的item对象的ID
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	//ListView通过调用getView()方法，得到相应的View对象，并将其显示在Activity当中
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = rowViews.get(position);
		if(rowView == null){
			//生成一个LayoutInflater对象
			LayoutInflater layoutInflater = LayoutInflater.from(context);
			//调用LayoutInflater对象的inflate方法，可以生成一个View对象
			rowView = layoutInflater.inflate(R.layout.item, null);
			//得到该View当中的两个控件
			TextView nameView = (TextView)rowView.findViewById(R.id.nameId);
			TextView textView = (TextView)rowView.findViewById(R.id.textId);
			//调用getItem（）方法，得到对应位置的weiBoData对象
			WeiBoData weiBoData = (WeiBoData)getItem(position);
			nameView.setText(weiBoData.getName());
			textView.setText(weiBoData.getText());
			rowViews.put(position, rowView);
		}
		return rowView;
	}

}

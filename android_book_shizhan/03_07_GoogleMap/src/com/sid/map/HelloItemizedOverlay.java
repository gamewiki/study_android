package com.sid.map;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * 在mapView之上创建一个图层，需要创建一个类，实现Overlay，并生成该类对象
 * 然后添加到MapView.getOverlays();
 * 
 * 一个OverlayItem对象就代表了一个在地图上显示的标记
 * @author Administrator
 *
 */
public class HelloItemizedOverlay extends ItemizedOverlay<OverlayItem> {
	/**创建一个List对象，用于持有该图层中的所有标记对象*/
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	/**使用弹出对话框时需要用到的*/
	private Context mContext;
	
	public HelloItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	/**
	 * 第一个参数用于指定标记所使用的默认图片
	 * @param defaultMarker
	 * @param context
	 */
	public HelloItemizedOverlay(Drawable defaultMarker, Context context) {
		//使用boundCenterBottom方法根据经纬度来确定标记位置（经纬度的点，放于标记的下方的正中央）
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
	}
	/**
	 * 用于创建一个OverlayItem对象
	 * i表示获取第几个OverlayIterm
	 */
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}
	/**
	 * 返回当前Overlay当中所包含的OverlayItem对象
	 */
	@Override
	public int size() {
		return mOverlays.size();
	}
	/**
	 * 用于将生成好的overlayItem对象添加到现有的图层中
	 * @param overlay
	 */
	public void addOverlay(OverlayItem overlayItem) {
	    mOverlays.add(overlayItem);
	    //重新生成，并调用其相关的方法
	    populate();
	}
	/**
	 * 当用户点击这个标记的时候所触发的操作
	 */
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
}

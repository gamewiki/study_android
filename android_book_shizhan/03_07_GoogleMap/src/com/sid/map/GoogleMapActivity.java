package com.sid.map;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GoogleMapActivity extends MapActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        
        MapView mapView = (MapView) findViewById(R.id.mapViewId);
        //显示缩放条
        mapView.setBuiltInZoomControls(true);
        //获取用户得到的所有图层
        List<Overlay> mapOverlays = mapView.getOverlays();
    	Drawable drawable = this.getResources().getDrawable(R.drawable.mp3_blueround_stop);
    	
    	HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable,this);
    	//创建一个经纬度的指定点
    	GeoPoint point = new GeoPoint(19240000,-99120000);
    	OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
    	itemizedoverlay.addOverlay(overlayitem);
    	
    	mapOverlays.add(itemizedoverlay);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}

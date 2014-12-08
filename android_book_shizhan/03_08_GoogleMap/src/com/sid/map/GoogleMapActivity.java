package com.sid.map;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class GoogleMapActivity extends MapActivity {

    private Projection projection;
    private List<Overlay> overlays;
    private MapController mapController;
    private GeoPoint beginPoint;
    private GeoPoint endPoint;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //起始经纬度
        beginPoint = new GeoPoint(19240000, -99120000);
        endPoint = new GeoPoint(19340000, -99220000);
        
        MapView mapView = (MapView) findViewById(R.id.mapViewId);
        mapView.setBuiltInZoomControls(true);
        
        //controller用于对地图进行控制
        mapController = mapView.getController();
        overlays = mapView.getOverlays();
        projection = mapView.getProjection();
        //toPixel(GeoPoint,Point)方法表示:通过geoPoint来获得一个屏幕的x,y
        //fromPixel(x,y)方法表示:通过x,y来获得一个点的经纬度

        overlays.add(new PointOverLay(beginPoint));
        overlays.add(new PointOverLay(endPoint));
        overlays.add(new LineOverLay(beginPoint,endPoint));
        
        //将地图移动到指定位置
        mapController.animateTo(beginPoint);
        //设置地图放大的级别
        mapController.setZoom(12);
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

    class LineOverLay extends Overlay{
		private GeoPoint begin;
    	private GeoPoint end;
    	public LineOverLay(GeoPoint beginPoint, GeoPoint endPoint) {
    		this.begin = beginPoint;
    		this.end = endPoint;
		}


    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    		super.draw(canvas, mapView, shadow);
    		Paint paint = new Paint();
    		paint.setColor(Color.BLUE);
    		paint.setStyle(Paint.Style.FILL_AND_STROKE);
    		paint.setStrokeWidth(2);

    		Point beginpoint = new Point();
    		Point endpoint = new Point();
    		Path path = new Path();
    		projection.toPixels(begin, beginpoint);
    		projection.toPixels(end, endpoint);
    		path.moveTo(endpoint.x, endpoint.y);
    		path.lineTo(beginpoint.x, beginpoint.y);
    		
    		canvas.drawPath(path, paint);
    	}
    }

    class PointOverLay extends Overlay{
		private GeoPoint geoPoint;
    	public PointOverLay(GeoPoint beginPoint) {
    		this.geoPoint = beginPoint;
		}

    	@Override
    	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    		super.draw(canvas, mapView, shadow);
    		Point point = new Point();
    		projection.toPixels(geoPoint, point);
            //指定图片
    		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mp3_blueround_stop);
    		//画笔对象
    		Paint paint = new Paint();
    		//画布：canvas.drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
    		canvas.drawBitmap(bitmap, point.x, point.y-16,paint);
    	}
    }
}

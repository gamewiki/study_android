package com.nono.shakeball;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * *****************************************************************************
 * <b>类名:IBody</b> <br/>
 * 功能：物体接口类<br/>
 * 日期: 2013年7月29日<br/>
 * 
 * @author 谢广泉 xiegqooo@gmail.com
 * @version 1.0.0
 * 
 *****************************************************************************
 */
public interface IBody {
	public void draw(Canvas canvas, Paint paint);
	
	public boolean isCollision();
	
	public void go();
	
	 interface BodyCollisionListener{
		 public void onCollision(IBody body);
	 }
}

package com.cn.bitmap.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.view.Surface;
public class GraphicsBitmapUtils {

	// �Ŵ���СͼƬ
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	// ���bitmp�ķ���
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;

	}
	
	public static Drawable BitmapToDrawable(Bitmap bitmap){

	 return new BitmapDrawable(bitmap); 


	}

	// ��ô���Ӱ��ͼƬ����
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}

	// ��Drawableת��ΪBitmap
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
				width, height / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
				(height + height / 2), Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		// Set the Transfer mode to be porter duff and destination in
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		// Draw a rectangle using the paint with our linear gradient
		canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}
	
	public static  void writerFileForByteArray(byte[] data)
	{
	 Bitmap $bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
	    if($bitmap==null)
        return;
		
	    String filename="/sdcard/10.8/"+System.currentTimeMillis()+".png";
	 
	  
	    
	    File f=new File(filename);
	    FileOutputStream fos=null;
	    try {
			f.createNewFile();
			fos=new FileOutputStream(f);
			$bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
public static byte[] Bitmap2Bytes(Bitmap bm){   
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();     
	    bm.compress(Bitmap.CompressFormat.PNG, 100, baos);     
	    return baos.toByteArray();   
	   }  
public static byte[] Bitmap2Bytes(Bitmap bm,int cos){   
    ByteArrayOutputStream baos = new ByteArrayOutputStream();     
    bm.compress(Bitmap.CompressFormat.PNG, cos, baos);     
    return baos.toByteArray();   
   } 
 
 public static  void writerFileForBitmap(Bitmap bitmap)
	{

		
	    String filename="/sdcard/10.8/"+System.currentTimeMillis()+".png";
	 
	  
	    
	    File f=new File(filename);
	    FileOutputStream fos=null;
	    try {
			f.createNewFile();
			fos=new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	
	}
 /**
  * ͼƬ�ϳ�
  * @param bitmap
  * @return
  */
private Bitmap createBitmap( Bitmap src, Bitmap watermark ) {
     if( src == null ) {
         return null;
     }
     int w = src.getWidth();
     int h = src.getHeight();
     int ww = watermark.getWidth();
     int wh = watermark.getHeight();
     //create the new blank bitmap
     Bitmap newb = Bitmap.createBitmap( w, h, Config.ARGB_8888 );//����һ���µĺ�SRC���ȿ��һ����λͼ
     Canvas cv = new Canvas( newb );
     //draw src into
     cv.drawBitmap( src, 0, 0, null );//�� 0��0���꿪ʼ����src
     //draw watermark into
     cv.drawBitmap( watermark, w - ww + 5, h - wh + 5, null );//��src�����½ǻ���ˮӡ
     //save all clip   
     cv.save( Canvas.ALL_SAVE_FLAG );//����
     //store
     cv.restore();//�洢
     return newb;
}

 



 /**
  * ͼƬԲ��
  * @param bitmap
  * @return
  */
 public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) { 
     Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), 
         bitmap.getHeight(), Config.ARGB_8888); 
     Canvas canvas = new Canvas(output); 

     final int color = 0xff424242; 
     final Paint paint = new Paint(); 
     final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); 
     final RectF rectF = new RectF(rect); 
     final float roundPx = 25; 

     paint.setAntiAlias(true); 
     canvas.drawARGB(0, 0, 0, 0); 
     paint.setColor(color); 
     canvas.drawRoundRect(rectF, roundPx, roundPx, paint); 

     paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); 
     canvas.drawBitmap(bitmap, rect, rect, paint); 
     return output; 
 }

 


 

 /**
  * ���š���ת����תͼƬ
  * @param bmpOrg
  * @param rotate
  * @return
  */
 private android.graphics.Bitmap gerZoomRotateBitmap(
   android.graphics.Bitmap bmpOrg, int rotate) {
  // ��ȡͼƬ��ԭʼ�Ĵ�С
  int width = bmpOrg.getWidth();
  int height = bmpOrg.getHeight();

  int newWidth = 300;
  int newheight = 300;
  // �������ŵĸߺͿ�ı���
  float sw = ((float) newWidth) / width;
  float sh = ((float) newheight) / height;
  // ��������ͼƬ���õ�Matrix����
  android.graphics.Matrix matrix = new android.graphics.Matrix();
  // ���ŷ�תͼƬ�Ķ���
  // sw sh�ľ���ֵΪ���ſ�ߵı�����swΪ������ʾX����ת��shΪ������ʾY����ת
  matrix.postScale(sw, sh);
  // ��ת30*
  matrix.postRotate(rotate);
  //����һ���µ�ͼƬ   
  android.graphics.Bitmap resizeBitmap = android.graphics.Bitmap
    .createBitmap(bmpOrg, 0, 0, width, height, matrix, true);
  return resizeBitmap;
 }

 /** 
  * ͼƬ��ת 
  *  
  * @param bmp 
  *            Ҫ��ת��ͼƬ 
  * @param degree 
  *            ͼƬ��ת�ĽǶȣ���ֵΪ��ʱ����ת����ֵΪ˳ʱ����ת 
  * @return 
  */  
 public static Bitmap rotateBitmap(Bitmap bmp, float degree) {  
     Matrix matrix = new Matrix();  
     matrix.postRotate(degree);  
     return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);  
 }  

 /** 
  * ͼƬ���� 
  *  
  * @param bm 
  * @param scale 
  *            ֵС����Ϊ��С������Ϊ�Ŵ� 
  * @return 
  */  
 public static Bitmap resizeBitmap(Bitmap bm, float scale) {  
     Matrix matrix = new Matrix();  
     matrix.postScale(scale, scale);  
     return Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);  
 }  

 /** 
  * ͼƬ���� 
  *  
  * @param bm 
  * @param w 
  *            ��С��Ŵ�ɵĿ� 
  * @param h 
  *            ��С��Ŵ�ɵĸ� 
  * @return 
  */  
 public static Bitmap resizeBitmap(Bitmap bm, int w, int h) {  
     Bitmap BitmapOrg = bm;  

     int width = BitmapOrg.getWidth();  
     int height = BitmapOrg.getHeight();  

     float scaleWidth = ((float) w) / width;  
     float scaleHeight = ((float) h) / height;  

     Matrix matrix = new Matrix();  
     matrix.postScale(scaleWidth, scaleHeight);  
     return Bitmap.createBitmap(BitmapOrg, 0, 0, width, height, matrix, true);  
 }  

 /** 
  * ͼƬ��ת 
  *  
  * @param bm 
  * @param flag 
  *            0Ϊˮƽ��ת��1Ϊ��ֱ��ת 
  * @return 
  */  
 public static Bitmap reverseBitmap(Bitmap bmp, int flag) {  
     float[] floats = null;  
     switch (flag) {  
     case 0: // ˮƽ��ת  
         floats = new float[] { -1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f };  
         break;  
     case 1: // ��ֱ��ת  
         floats = new float[] { 1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f };  
         break;  
     }  

     if (floats != null) {  
         Matrix matrix = new Matrix();  
         matrix.setValues(floats);  
         return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);  
     }  

     return null;  
 }  

}



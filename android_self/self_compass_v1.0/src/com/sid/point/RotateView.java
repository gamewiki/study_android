package com.sid.point;


import java.io.InputStream;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RotateView extends View implements Runnable{
	/**设置提示性文字*/
	private String textNote = "";
	/**设置提示性文字*/
	private String angleNote = "";
	/**获取屏幕的宽度*/
	private Integer windowWidth = 420;
	/**获取屏幕的高度*/
	private Integer windowHeight = 800;
	/** 图像画笔*/
	private Paint mPaint = new Paint();
	/** 文字画笔*/
	private Paint tPaint = new Paint();
	/** 文字画笔*/
	private Paint aPaint = new Paint();
	/** 设置数组长度*/
	private int size = 8;
	/** 存放图片资源数组和对应的配置参数*/
	private Bitmap[] mBitmapArray = new Bitmap[size];
	/** 存放图片资源数组宽度和对应的配置参数*/
	int[] mBitmapWidth = new int[size];
	/** 存放图片资源数组宽度和对应的配置参数*/
	int[] mBitmapHeight = new int[size];
	/** 输入流用于读取资源*/
	InputStream is;
	/** 资源*/
	private Resources mRes;
	/** 旋转角度*/
	Integer Angle = 0;
	public RotateView(Context context) {
		super(context);
		this.textNote = context.getString(R.string.textNote);
		this.angleNote= context.getString(R.string.angleNote); 
		//装载资源
		mRes = RotateView.this.getResources();
		//设置位图工厂参数
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = 2; // scaled down by 200 
		//设置位图
		SetBitmapArray(0, opts, R.drawable.background);
		SetBitmapArray(1, opts, R.drawable.mark);
		SetBitmapArray(2, opts, R.drawable.redmark);
		SetBitmapArray(3, opts, R.drawable.pointer);
		//开启线程
		new Thread(this).start();
	}
	private void SetBitmapArray(int index, BitmapFactory.Options opts,
			int resId) {
		//获取资源
		is = mRes.openRawResource(resId);
		mBitmapArray[index] = BitmapFactory.decodeStream(is);
		mBitmapWidth[index] = mBitmapArray[index].getWidth();
		mBitmapHeight[index] = mBitmapArray[index].getHeight();
		mBitmapArray[index + size/2] = BitmapFactory.decodeStream(is, null, opts);
		mBitmapWidth[index + size/2] = mBitmapArray[index + size/2].getWidth();
		mBitmapHeight[index + size/2] = mBitmapArray[index + size/2].getHeight();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		//设置画笔参数
		tPaint.setTextAlign(Paint.Align.CENTER);
		tPaint.setTextSize(AppConstant.TNOTE_SIZE);
		tPaint.setColor(AppConstant.TEXT_COLOR);
		//设置画笔参数
		aPaint.setTextAlign(Paint.Align.CENTER);
		aPaint.setTextSize(AppConstant.ANOTE_SIZE);
		aPaint.setColor(AppConstant.TEXT_COLOR);
		//设置画笔参数
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);
		canvas.drawColor(Color.GRAY);
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		int cx = w / 2;
		int cy = h / 2;
		int mCurrentOrientation = getResources().getConfiguration().orientation;
		if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
			canvas.translate(cx, cy);
			drawPictures(canvas, 0);
		} else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
			canvas.translate(cx, cy - 20);
			drawPictures(canvas, 3);
		}
	}
	/**
	 * 根据传入的值
	 * 勾画位图
	 * @param canvas
	 * @param idDelta
	 */
	private void drawPictures(Canvas canvas, int idDelta) {
		int num = 2;
		if (Angle != null) {
			canvas.drawBitmap(mBitmapArray[0 + idDelta],
					-mBitmapWidth[0 + idDelta] / num,
					-mBitmapHeight[0 + idDelta] / num, mPaint);
			canvas.drawBitmap(mBitmapArray[1 + idDelta],
					-mBitmapWidth[1 + idDelta] / num,
					-mBitmapHeight[1 + idDelta] / num, mPaint);
			canvas.drawBitmap(mBitmapArray[2 + idDelta],
					-mBitmapWidth[2 + idDelta] / num,
					-mBitmapHeight[2 + idDelta] / num, mPaint);
			canvas.rotate(-Angle);
			canvas.drawBitmap(mBitmapArray[3 + idDelta],
					-mBitmapWidth[3 + idDelta] / num,
					-mBitmapHeight[3 + idDelta] / num, mPaint);
			canvas.rotate(360 + Angle);
			canvas.drawText(textNote, 0, 0-(windowHeight/2)+AppConstant.ANOTE_TNOTE_PADDING, tPaint);
			canvas.drawText(angleNote, 0, 0-(windowHeight/2)+AppConstant.ANOTE_TNOTE_PADDING+AppConstant.ANOTE_TNOTE_DIFFER, aPaint);
		} else {
			canvas.drawBitmap(mBitmapArray[0 + idDelta],
					-mBitmapWidth[0 + idDelta] / num,
					-mBitmapHeight[0 + idDelta] / num, mPaint);
			canvas.drawBitmap(mBitmapArray[1 + idDelta],
					-mBitmapWidth[1 + idDelta] / num,
					-mBitmapHeight[1 + idDelta] / num, mPaint);
			canvas.drawBitmap(mBitmapArray[2 + idDelta],
					-mBitmapWidth[2 + idDelta] / num,
					-mBitmapHeight[2 + idDelta] / num, mPaint);
			canvas.drawBitmap(mBitmapArray[3 + idDelta],
					-mBitmapWidth[3 + idDelta] / num,
					-mBitmapHeight[3 + idDelta] / num, mPaint);
		}
	}
	//线程处理
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				Thread.currentThread().interrupt();
			}
			this.postInvalidate();
		}
	}
	public Integer getAngle() {
		return Angle;
	}
	public void setAngle(Integer angle) {
		Angle = angle;
	}
	public String getTextNote() {
		return textNote;
	}
	public void setTextNote(String textNote) {
		this.textNote = textNote;
	}
	public String getAngleNote() {
		return angleNote;
	}
	public void setAngleNote(String angleNote) {
		this.angleNote = angleNote;
	}
	public Integer getWindowWidth() {
		return windowWidth;
	}
	public void setWindowWidth(Integer windowWidth) {
		this.windowWidth = windowWidth;
	}
	public Integer getWindowHeight() {
		return windowHeight;
	}
	public void setWindowHeight(Integer windowHeight) {
		this.windowHeight = windowHeight;
	}
}

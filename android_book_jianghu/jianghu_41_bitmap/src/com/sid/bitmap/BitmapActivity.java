package com.sid.bitmap;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BitmapActivity extends Activity implements SurfaceHolder.Callback {
	/* 创建私有Camera对象 */
	private Camera mCamera01;
	private Button mButton01, mButton02, mButton03;

	/* 作为review照下来的相片之用 */
	private ImageView mImageView01;
	private String TAG = "HIPPO";
	private SurfaceView mSurfaceView01;
	private SurfaceHolder mSurfaceHolder01;

	/* 默认相机预览模式为false */
	private boolean bIfPreview = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/* 使应用程序全屏幕运行，不使用title bar */
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_bitmap);

		/* 添加红色正方形红框View，供User对准条形码 */
		DrawCaptureRect mDraw = new DrawCaptureRect(BitmapActivity.this, 110,
				10, 100, 100, getResources().getColor(R.drawable.lightred));

		/* 将创建的红色方框添加至此Activity中 */
		addContentView(mDraw, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		/* 取得屏幕解析像素 */
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		mImageView01 = (ImageView) findViewById(R.id.myImageView1);

		/* 以SurfaceView作为相机Preview之用 */
		mSurfaceView01 = (SurfaceView) findViewById(R.id.mSurfaceView1);

		/* 绑定SurfaceView，取得SurfaceHolder对象 */
		mSurfaceHolder01 = mSurfaceView01.getHolder();

		/* Activity必须实现SurfaceHolder.Callback */
		mSurfaceHolder01.addCallback(BitmapActivity.this);

		/* 额外的设置预览大小设置，在此不使用 */
		// mSurfaceHolder01.setFixedSize(320, 240);

		/*
		 * 以SURFACE_TYPE_PUSH_BUFFERS(3) 作为SurfaceHolder显示类型
		 */
		mSurfaceHolder01.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		mButton01 = (Button) findViewById(R.id.myButton1);
		mButton02 = (Button) findViewById(R.id.myButton2);
		mButton03 = (Button) findViewById(R.id.myButton3);

		/* 打开相机及预览二维条形码 */
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				/* 自定义初始化打开相机函数 */
				try {
					initCamera();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		/* 停止预览 */
		mButton02.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {

				/* 自定义重置相机，并关闭相机预览函数 */
				resetCamera();
			}
		});

		/* 拍照QR Code二维条形码 */
		mButton03.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/* 自定义拍照函数 */
				takePicture();
			}
		});
	}

	/* 自定义初始相机函数 */
	private void initCamera() throws IOException {
		if (!bIfPreview) {
			/* 若相机非在预览模式，则打开相机 */
			mCamera01 = Camera.open();
		}

		if (mCamera01 != null && !bIfPreview) {
			Log.i(TAG, "inside the camera");

			/* 创建Camera.Parameters对象 */
			Camera.Parameters parameters = mCamera01.getParameters();

			/* 设置相片格式为JPEG */
			parameters.setPictureFormat(PixelFormat.JPEG);

			/* 指定preview的屏幕大小 */
			parameters.setPreviewSize(160, 120);

			/* 设置图片分辨率大小 */
			parameters.setPictureSize(160, 120);

			/* 将Camera.Parameters设置予Camera */
			mCamera01.setParameters(parameters);

			/* setPreviewDisplay唯一的参数为SurfaceHolder */
			mCamera01.setPreviewDisplay(mSurfaceHolder01);

			/* 立即运行Preview */
			mCamera01.startPreview();
			bIfPreview = true;
		}
	}

	/* 拍照撷取图像 */
	private void takePicture() {
		if (mCamera01 != null && bIfPreview) {
			/* 调用takePicture()方法拍照 */
			mCamera01.takePicture(shutterCallback, rawCallback, jpegCallback);
		}
	}

	/* 相机重置 */
	private void resetCamera() {
		if (mCamera01 != null && bIfPreview) {
			mCamera01.stopPreview();
			/* 扩展学习，释放Camera对象 */
			// mCamera01.release();
			mCamera01 = null;
			bIfPreview = false;
		}
	}

	private ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			// Shutter has closed
		}
	};

	private PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] _data, Camera _camera) {
		}
	};

	private PictureCallback jpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] _data, Camera _camera) {
			try {
				/* onPictureTaken传入的第一个参数即为相片的byte */
				Bitmap bm = BitmapFactory.decodeByteArray(_data, 0,
						_data.length);

				int resizeWidth = 160;
				int resizeHeight = 120;
				float scaleWidth = ((float) resizeWidth) / bm.getWidth();
				float scaleHeight = ((float) resizeHeight) / bm.getHeight();

				Matrix matrix = new Matrix();
				/* 使用Matrix.postScale方法缩小 Bitmap Size */
				matrix.postScale(scaleWidth, scaleHeight);

				/* 创建新的Bitmap对象 */
				Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0,
						bm.getWidth(), bm.getHeight(), matrix, true);

				/* 撷取4:3图片的居中红色框部分100x100像素 */
				Bitmap resizedBitmapSquare = Bitmap.createBitmap(resizedBitmap,
						30, 10, 100, 100);

				/* 将拍照的图文件以ImageView显示出来 */
				mImageView01.setImageBitmap(resizedBitmapSquare);

				/* 将传入的图文件译码成字符串 */
				String strQR2 = decodeQRImage(resizedBitmapSquare);
				if (strQR2 != "") {
					if (URLUtil.isNetworkUrl(strQR2)) {
						/* OMIA规范，网址条形码，打开浏览器上网 */
						mMakeTextToast(strQR2, true);
						Uri mUri = Uri.parse(strQR2);
						Intent intent = new Intent(Intent.ACTION_VIEW, mUri);
						startActivity(intent);
					} else if (eregi("wtai://", strQR2)) {
						/* OMIA规范，手机拨打电话格式 */
						String[] aryTemp01 = strQR2.split("wtai://");
						Intent myIntentDial = new Intent(
								"android.intent.action.CALL", Uri.parse("tel:"
										+ aryTemp01[1]));
						startActivity(myIntentDial);
					} else if (eregi("TEL:", strQR2)) {
						/* OMIA规范，手机拨打电话格式 */
						String[] aryTemp01 = strQR2.split("TEL:");
						Intent myIntentDial = new Intent(
								"android.intent.action.CALL", Uri.parse("tel:"
										+ aryTemp01[1]));
						startActivity(myIntentDial);
					} else {
						/* 若仅是文字，则以Toast显示出来 */
						mMakeTextToast(strQR2, true);
					}
				}

				/* 显示完图文件，立即重置相机，并关闭预览 */
				resetCamera();

				/* 再重新启动相机继续预览 */
				initCamera();
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
		}
	};

	public void mMakeTextToast(String str, boolean isLong) {
		if (isLong == true) {
			Toast.makeText(BitmapActivity.this, str, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(BitmapActivity.this, str, Toast.LENGTH_SHORT).show();
		}
	}

	private boolean checkSDCard() {
		/* 判断记忆卡是否存在 */
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/* 解碼传入的Bitmap图片 */
	public String decodeQRImage(Bitmap myBmp) {
		String strDecodedData = "";
		try {
			QRCodeDecoder decoder = new QRCodeDecoder();
			strDecodedData = new String(decoder.decode(new AndroidQRCodeImage(
					myBmp)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDecodedData;
	}

	/* 自定义实现QRCodeImage类 */
	class AndroidQRCodeImage implements QRCodeImage {
		Bitmap image;

		public AndroidQRCodeImage(Bitmap image) {
			this.image = image;
		}

		public int getWidth() {
			return image.getWidth();
		}

		public int getHeight() {
			return image.getHeight();
		}

		public int getPixel(int x, int y) {
			return image.getPixel(x, y);
		}
	}

	/* 绘制相机预览画面里的正方形方框 */
	class DrawCaptureRect extends View {
		private int colorFill;
		private int intLeft, intTop, intWidth, intHeight;

		public DrawCaptureRect(Context context, int intX, int intY,
				int intWidth, int intHeight, int colorFill) {
			super(context);
			this.colorFill = colorFill;
			this.intLeft = intX;
			this.intTop = intY;
			this.intWidth = intWidth;
			this.intHeight = intHeight;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Paint mPaint01 = new Paint();
			mPaint01.setStyle(Paint.Style.FILL);
			mPaint01.setColor(colorFill);
			mPaint01.setStrokeWidth(1.0F);
			/* 在画布上绘制红色的四条方边框作为瞄准器 */
			canvas.drawLine(this.intLeft, this.intTop, this.intLeft + intWidth,
					this.intTop, mPaint01);
			canvas.drawLine(this.intLeft, this.intTop, this.intLeft,
					this.intTop + intHeight, mPaint01);
			canvas.drawLine(this.intLeft + intWidth, this.intTop, this.intLeft
					+ intWidth, this.intTop + intHeight, mPaint01);
			canvas.drawLine(this.intLeft, this.intTop + intHeight, this.intLeft
					+ intWidth, this.intTop + intHeight, mPaint01);
			super.onDraw(canvas);
		}
	}

	/* 自定义比较字符串函数 */
	public static boolean eregi(String strPat, String strUnknow) {
		String strPattern = "(?i)" + strPat;
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strUnknow);
		return m.find();
	}

	@Override
	public void surfaceChanged(SurfaceHolder surfaceholder, int format, int w,
			int h) {
		Log.i(TAG, "Surface Changed");
	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceholder) {
		Log.i(TAG, "Surface Changed");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
		Log.i(TAG, "Surface Destroyed");
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}

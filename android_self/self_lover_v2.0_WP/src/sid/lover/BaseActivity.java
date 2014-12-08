package sid.lover;

import cn.waps.AppConnect;
import sid.lover.ui.views.MenuRightAnimations;
import sid.utils.ExitApplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class BaseActivity extends Activity implements OnTouchListener {
	private boolean areButtonsShowing;
	/** 四个小按钮的组合的布局 */
	private RelativeLayout composerButtonsWrapper;
	/** 大按钮，隐藏的布局 */
	private RelativeLayout composerButtonsShowHideButton;
	/** 大按钮 */
	private ImageView composerButtonsShowHideButtonIcon;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.base_activity);

		init();

        LinearLayout ll = (LinearLayout) findViewById(R.id.AdLinearLayout);
        AppConnect.getInstance(this).showBannerAd(this, ll);
		// 添加到activity管理列表中
		ExitApplication.getInstance().addActivity(this);
	}

	protected void onCreate(Bundle savedInstanceState, int layoutId) {
		super.onCreate(savedInstanceState);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layoutId);

		init();
	}

	private void init() {
		MenuRightAnimations.initOffset(BaseActivity.this);
		composerButtonsWrapper = (RelativeLayout) findViewById(R.id.composer_buttons_wrapper);
		composerButtonsShowHideButton = (RelativeLayout) findViewById(R.id.composer_buttons_show_hide_button);
		composerButtonsShowHideButtonIcon = (ImageView) findViewById(R.id.composer_buttons_show_hide_button_icon);

		composerButtonsShowHideButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onClickView(v, false);
			}
		});
		for (int i = 0; i < composerButtonsWrapper.getChildCount(); i++) {
			composerButtonsWrapper.getChildAt(i).setOnClickListener(
					new View.OnClickListener() {
						@Override
						public void onClick(View view) {
							if (areButtonsShowing) {
//								Toast.makeText(getApplicationContext(),"argo=" + view.getId() + " click", Toast.LENGTH_SHORT).show();
//								Toast.makeText(getApplicationContext(),"id=" + R.id.composer_button_photo + " click", Toast.LENGTH_SHORT).show();
								int id = view.getId();
								Intent intent = new Intent();
								if (id == R.id.composer_button_people) {
									intent.setClass(BaseActivity.this,AboutLoverActivity.class);
								} else if (id == R.id.composer_period) {
									intent.setClass(BaseActivity.this,CalendarActivity.class);
								} else if (id == R.id.composer_button_thought) {
									intent.setClass(BaseActivity.this,SendMsgActivity.class);
								} else if (id == R.id.composer_button_photo) {
									intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
									startActivityForResult(intent, 1);
								}
								BaseActivity.this.startActivity(intent);
							}
						}
					});
		}

		composerButtonsShowHideButton.startAnimation(MenuRightAnimations.getRotateAnimation(0, 360, 200));

		View v = findViewById(R.id.composer_buttons_wrapper);
		v.setOnTouchListener(this);
		
	}

	public void onClickView(View v, boolean isOnlyClose) {
		if (isOnlyClose) {
			if (areButtonsShowing) {
				MenuRightAnimations.startAnimationsOut(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(-315, 0, 300));
				areButtonsShowing = !areButtonsShowing;
			}
		} else {
			if (!areButtonsShowing) {
				MenuRightAnimations.startAnimationsIn(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(0, -315, 300));
			} else {
				MenuRightAnimations.startAnimationsOut(composerButtonsWrapper,300);
				composerButtonsShowHideButtonIcon.startAnimation(MenuRightAnimations.getRotateAnimation(-315, 0, 300));
			}
			areButtonsShowing = !areButtonsShowing;
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
//		System.out.println("=======onTouch========");
		onClickView(v, true);
		return false;
	}

//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		Toast.makeText(getApplicationContext(),"onActivityResult:"+resultCode,Toast.LENGTH_SHORT).show();
//		if (resultCode == Activity.RESULT_OK) {
//			String sdStatus = Environment.getExternalStorageState();
//			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//				Log.v("检查存储卡","存储卡当前不可用.");
//				Toast.makeText(getApplicationContext(),"存储卡当前不可用",Toast.LENGTH_SHORT).show();
//				return;
//			}
//			Bundle bundle = data.getExtras();
//			Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
//			FileUtils.getInstance().compressAndSaveBitmapToSDCard(bitmap, 
//					"loverstory",String.valueOf(new Date().getTime()), 80);
////			((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);// 将图片显示在ImageView里
//			Toast.makeText(getApplicationContext(),"存储成功",Toast.LENGTH_SHORT).show();
//		}
//	}
}

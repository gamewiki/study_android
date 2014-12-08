package com.cn.picture;

import java.util.zip.Inflater;

import com.cn.Abimation.utils.CanClickAnimationListener;
import com.cn.bitmap.utils.GraphicsBitmapUtils;
import com.mygallery.activity.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRotate implements OnClickListener {
	private ImageView imageview;
	private View bg;
	private DisplayNextView displayNextView;
	private Context context;
	private View convertView;
	private LayoutInflater mInflater;
	private boolean now_zhengfan;
	private View container_bg;

	public ViewRotate(Context context, View convertView,
			LayoutInflater mInflater) {
		this.context = context;
		this.convertView = convertView;
		this.mInflater = mInflater;
		now_zhengfan = true;
		AnimationUtils.loadAnimation(context, R.anim.my_alpha_action);
		init();
	}

	public void init() {

		
		bg = (ViewGroup) convertView.findViewById(R.id.container);
		bg.findViewById(R.id.btn_more).setOnClickListener(this);
		container_bg = convertView.findViewById(R.id.container_bg);

		container_bg
		.setBackgroundDrawable(GraphicsBitmapUtils.
				BitmapToDrawable(GraphicsBitmapUtils.getRoundedCornerBitmap(GraphicsBitmapUtils
				.drawableToBitmap(context.getResources()
						.getDrawable(R.drawable.zh)))));
	}

	private void applyRotation(int position, float start, float end) {
		// Find the center of the container
		final float centerX = bg.getWidth() / 2.0f;
		final float centerY = bg.getHeight() / 2.0f;
		final Rotate3d rotation = new Rotate3d(start, end, centerX, centerY,
				310.0f, false);
		rotation.setDuration(500);
		rotation.setFillAfter(false);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(position, true));
		bg.startAnimation(rotation);
		

AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		bg.setEnabled(false);
		applyRotation(0, 0, 90);
	}

	private final class DisplayNextView implements Animation.AnimationListener {
		private final int mPosition;
		private final boolean b;

		private DisplayNextView(int position, boolean t) {
			mPosition = position;
			b = t;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			if (b) {
				bg.post(new SwapViews(mPosition));
				if (now_zhengfan) {
					bg.findViewById(R.id.backe_bg1).setVisibility(
							View.INVISIBLE);
				} else {
					bg.findViewById(R.id.backe_bg2).setVisibility(
							View.INVISIBLE);
				}
			} else {

				bg.setEnabled(true);
				if (now_zhengfan) {
					bg.findViewById(R.id.backe_bg2).setVisibility(View.VISIBLE);
					bg.setOnClickListener(ViewRotate.this);
					bg.setClickable(true);

					AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
					alphaAnim.setDuration(2000);
					alphaAnim.setStartOffset(500);
					alphaAnim
							.setAnimationListener(new CanClickAnimationListener(
									bg));
					bg.findViewById(R.id.backe_bg2).startAnimation(alphaAnim);
					now_zhengfan = false;
				} else {
					bg.findViewById(R.id.backe_bg1).setVisibility(View.VISIBLE);
					bg.setOnClickListener(ViewRotate.this);

					now_zhengfan = true;
					bg.setClickable(false);
					View btn = bg.findViewById(R.id.btn_more);
					btn.setOnClickListener(ViewRotate.this);

//					container_bg
//							.setBackgroundDrawable(GraphicsBitmapUtils.BitmapToDrawable(GraphicsBitmapUtils.getRoundedCornerBitmap(GraphicsBitmapUtils
//									.drawableToBitmap(context.getResources()
//											.getDrawable(R.drawable.zh)))));

					AlphaAnimation alphaAnim = new AlphaAnimation(0, 1);
					alphaAnim.setDuration(2000);
					alphaAnim.setStartOffset(500);
					alphaAnim
							.setAnimationListener(new CanClickAnimationListener(
									bg, btn));

					bg.findViewById(R.id.backe_bg1).startAnimation(alphaAnim);
					// bg.findViewById(R.id.backe_bg1).startAnimation(
					// CopyOfTestRotate.this.animation);

				}
			}

		}

		public void onAnimationRepeat(Animation animation) {

		}
	}

	private final class SwapViews implements Runnable {
		private final int mPosition;

		public SwapViews(int position) {
			mPosition = position;
		}

		public void run() {
			final float centerX = bg.getWidth() / 2.0f;
			final float centerY = bg.getHeight() / 2.0f;
			Rotate3d rotation;
			if (mPosition > -1) {

				rotation = new Rotate3d(90, 180, centerX, centerY, 310.0f,
						false);
				rotation.setAnimationListener(new DisplayNextView(mPosition,
						false));
			} else {
				rotation = new Rotate3d(90, 0, centerX, centerY, 310.0f, false);
			}
			rotation.setDuration(500);
			rotation.setFillAfter(false);
			rotation.setInterpolator(new DecelerateInterpolator());
			bg.startAnimation(rotation);
			bg.setEnabled(false);
		}
	}

}
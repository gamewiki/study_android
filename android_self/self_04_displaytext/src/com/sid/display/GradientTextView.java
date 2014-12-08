package com.sid.display;
import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

public class GradientTextView extends TextView {
	private ArrayList<String> showTextList = new ArrayList<String>();
	private String showText = null;
	private String[] texts;
	private static int i = 0;
	private static int currentIndex = 0;
	private int mGradientSpeed = 500;
	private int mTextChangeTime = 1000;
	private boolean isGradient = false;
	private boolean lockText = false;

	public GradientTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GradientTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GradientTextView(Context context) {
		super(context);
	}

	public void setGradientSpeed(int time) {
		this.mGradientSpeed = time;
	}

	public void setTextChangeTime(int time) {
		this.mTextChangeTime = time;
	}

	public boolean getGradientFlag() {
		return isGradient;
	}

	public final void setCustomText(CharSequence text) {
		if (!lockText) {
			setText(text);
		}
	}

	public final void appendCustomText(CharSequence text) {
		if (!lockText) {
			append(text);
		}
	}

	public void addShowText(String text) {
		showTextList.add(text);
	}

	public void removeShowText(String text) {
		if (showTextList.contains(text)) {
			showTextList.remove(text);
		}
	}

	public void removeAllShowTexts() {
		showTextList.clear();
	}

	public void setShowText(String text) {
		this.showText = text;
		addShowText(text);
	}

	private void onPrepareText(String text) {
		texts = new String[text.length()];
		for (int i = 0; i < texts.length; i++) {
			texts[i] = showText.toString().substring(i, i + 1);
		}
	}

	public void startGradient() {
		setCustomText(null);
		Message msg = new Message();
		msg.what = MSG_START_GRADIENT;
		msg.arg1 = i;
		mHandler.sendMessage(msg);
	}

	private void StartCurrTextGradient(int time) {
		lockText = false;
		isGradient = true;
		showText = showTextList.get(currentIndex);
		onPrepareText(showText);
		i = 0;
		Message msg1 = new Message();
		msg1.what = MSG_START_EACHTEXT_GRADIENT;
		msg1.arg1 = i;
		mHandler.sendMessageDelayed(msg1, time);
	}

	public void stopGradient() {
		if (currentIndex < showTextList.size() - 1) {
			StopCurrTextGradient(0);
		} else {
			stopAllGradient();
		}
	}

	private void StopCurrTextGradient(int time) {
		setCustomText(showText);
		lockText = true;
		Message msg1 = new Message();
		msg1.what = MSG_STOP_EACHTEXT_GRADIENT;
		mHandler.sendMessageDelayed(msg1, time);
	}

	private void stopAllGradient() {
		setCustomText(showText);
		lockText = true;
		Message msg = new Message();
		msg.what = MSG_STOP_GRADIENT;
		mHandler.sendMessage(msg);
	}

	private static final int MSG_START_GRADIENT = 0;
	private static final int MSG_START_EACHTEXT_GRADIENT = 1;
	private static final int MSG_STOP_GRADIENT = 2;
	private static final int MSG_STOP_EACHTEXT_GRADIENT = 3;

	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == MSG_START_GRADIENT) {
				mOnGradientFinishListener.onGradientStart();
				if (showTextList != null) {
					currentIndex = 0;
					StartCurrTextGradient(0);
				}
			} else if (msg.what == MSG_STOP_GRADIENT) {
				if (mHandler.hasMessages(MSG_START_EACHTEXT_GRADIENT)) {
					mHandler.removeMessages(MSG_START_EACHTEXT_GRADIENT);
				}
				isGradient = false;
				mOnGradientFinishListener.onGradientFinish();
			} else if (msg.what == MSG_STOP_EACHTEXT_GRADIENT) {
				if (mHandler.hasMessages(MSG_START_EACHTEXT_GRADIENT)) {
					mHandler.removeMessages(MSG_START_EACHTEXT_GRADIENT);
				}
				mOnGradientFinishListener.onGradientTextChange();
				currentIndex++;
				StartCurrTextGradient(mTextChangeTime);
			} else if (msg.what == MSG_START_EACHTEXT_GRADIENT) {
				if (msg.arg1 < texts.length) {
					if (i == 0) {
						setCustomText(null);
					}
					appendCustomText(texts[i]);
					i++;
					Message msg1 = new Message();
					msg1.what = MSG_START_EACHTEXT_GRADIENT;
					msg1.arg1 = i;
					mHandler.sendMessageDelayed(msg1, mGradientSpeed);
				} else {
					stopGradient();
				}
			}
		}
	};

	private onGradientFinishListener mOnGradientFinishListener = null;

	public void setOnGradientFinishListener(onGradientFinishListener listener) {
		this.mOnGradientFinishListener = listener;
	}

	public static interface onGradientFinishListener {
		public void onGradientStart();

		public void onGradientTextChange();

		public void onGradientFinish();
	}
}
package com.example.self_05_font;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView text1 = null;
	Typeface fontFace = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text1 = (TextView) findViewById(R.id.text1);
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/pop.ttf");
//		text1.setText("pop字体");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzct.ttf");
//		text1.setText("方正粗圆");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzgl.ttf");
//		text1.setText("方正古隶");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzhl.ttf");
//		text1.setText("方正华隶");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzjljt.ttf");
//		text5.setText("方正静蕾简体.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzktjt.ttf");
//		text6.setText("方正卡通简体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
//		text7.setText("方正流行体简体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzmwt.ttf");
//		text8.setText("方正喵呜体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzpty.TTF");
//		text9.setText("方正胖头鱼.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzqtjt.ttf");
//		text10.setText("方正启体简体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzxz.TTF");
//		text11.setText("方正小篆.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzybxs.TTF");
//		text12.setText("方正硬笔行书.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzzy.ttf");
//		text13.setText("方正正圆.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/fzzy2.TTF");
//		text14.setText("方正准圆.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hksnzt.ttf");
//		text15.setText("华康少女字体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hkwwt.TTF");
//		text16.setText("华康娃娃体.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hkzhzt.TTF");
//		text17.setText("华康中黑字体.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hwcy.TTF");
//		text18.setText("华文彩云.TTF");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hwxs.ttf");
//		text19.setText("华文新宋.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hwxw.TTF");
//		text20.setText("华文新魏.TTF");
		fontFace = Typeface.createFromAsset(getAssets(),"fonts/hwxk.ttf");
//		text21.setText("华文行楷.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/kt.ttf");
//		text22.setText("楷体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/ls.ttf");
//		text23.setText("隶书.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/lm.ttf");
//		text24.setText("明兰.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/njygy.ttf");
//		text25.setText("诺基亚古印.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/pglh.ttf");
//		text26.setText("苹果丽黑.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/sjt.ttf");
//		text27.setText("瘦金体.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/wryh.ttf");
//		text28.setText("微软雅黑14M.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/wqywmh.ttf");
//		text29.setText("文泉驿微米黑.ttf");
//		fontFace = Typeface.createFromAsset(getAssets(),"fonts/yy.ttf");
//		text30.setText("幼圆.ttf");
		text1.setTypeface(fontFace);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}

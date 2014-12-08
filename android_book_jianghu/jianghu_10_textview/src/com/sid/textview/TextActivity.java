package com.sid.textview;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 拨打手机
 * 发送邮件
 * @author Administrator
 *
 */
public class TextActivity extends Activity {
	private TextView textView;
	private EditText editText;
	private EditText editphoneText;
	private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        editText = (EditText) findViewById(R.id.editId);
        textView = (TextView) findViewById(R.id.textId);
        button = (Button) findViewById(R.id.button);
        editphoneText = (EditText) findViewById(R.id.editphoneId);
        //设置监听回车按钮
        editText.setOnKeyListener(new EditText.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				textView.setText("您输入的内容是："+editText.getText());
				return false;
			}
		});
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String value = editphoneText.getText().toString();
				if(Tools.isPhoneNumberValid(value)){
					textView.setText("您输入的号码是："+editphoneText.getText());
					Intent intent = new Intent("android.intent.action.CALL"
							,Uri.parse("tel:"+value));
					startActivity(intent);
				}else if(Tools.isEmail(value)){

			        Intent mEmailIntent = new Intent(android.content.Intent.ACTION_SEND);  
			     
			        mEmailIntent.setType("plain/text");
			        String strEmailReciver = "XXXXX@163.com";
			        String strEmailCc = "副本";
			        String strEmailSubject = "主题";
			        String strEmailBody = "内容";
			       
			        mEmailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, strEmailReciver); 
			        mEmailIntent.putExtra(android.content.Intent.EXTRA_CC, strEmailCc);
			        mEmailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, strEmailSubject);
			        mEmailIntent.putExtra(android.content.Intent.EXTRA_TEXT, strEmailBody);
			 
			        startActivity(Intent.createChooser(mEmailIntent, getResources().getString(R.string.str_message))); 
				}else{
					textView.setText("您输入的号码不正确");
				};
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_text, menu);
        return true;
    }
}

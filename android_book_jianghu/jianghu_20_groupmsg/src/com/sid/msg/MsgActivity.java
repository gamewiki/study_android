package com.sid.msg;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MsgActivity extends Activity {
	private TextView mTextView01;
	private TextView mTextView3;
	private TextView mTextView5;
	private Button mButton01;
	private Button mButton02;
	/* 先声明strMessage为String */
	String strMessage;

	private static final int PICK_CONTACT_SUBACTIVITY = 2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msg);

		mTextView01 = (TextView) findViewById(R.id.myTextView1);
		mButton01 = (Button) findViewById(R.id.myButton1);
		mTextView3 = (TextView) findViewById(R.id.myTextView3);
		mButton02 = (Button) findViewById(R.id.myButton2);
		mTextView5 = (TextView) findViewById(R.id.myTextView5);

		/* 设置第一个Button事件 */
		mButton01.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("content://contacts/people");
				Intent intent = new Intent(Intent.ACTION_PICK, uri);
				/* 去撷取mTextView3里的内容 */
				strMessage = mTextView3.getText().toString();
				startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
			}
		});

		/* 设置第二个Button事件 */
		mButton02.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("content://contacts/people");
				Intent intent = new Intent(Intent.ACTION_PICK, uri);
				/* 去撷取mTextView5里的内容 */
				strMessage = mTextView5.getText().toString();
				startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case PICK_CONTACT_SUBACTIVITY:
			final Uri uriRet = data.getData();
			if (uriRet != null) {
				try {
					/* 必须要有android.permission.READ_CONTACTS权限 */
					Cursor c = managedQuery(uriRet, null, null, null, null);
					c.moveToFirst();
					/* 抓取通讯录的姓名 */
					String strName = c.getString(c.getColumnIndexOrThrow(People.NAME));
					/* 抓取通讯录的电话 */
					String strPhone = c.getString(c.getColumnIndexOrThrow(People.NUMBER));

					/* 设置要寄给通讯录里的电话 */
					String strDestAddress = strPhone;
					System.out.println(strMessage);
					SmsManager smsManager = SmsManager.getDefault();
					PendingIntent mPI = PendingIntent.getBroadcast(MsgActivity.this, 0, new Intent(), 0);
					/* 寄出短信 */
					smsManager.sendTextMessage(strDestAddress, null,strMessage, mPI, null);
					/* 用Toast显示传送中 */
					Toast.makeText(MsgActivity.this,
							getString(R.string.str_msg) + strName,
							Toast.LENGTH_SHORT).show();

					mTextView01.setText(strName + ":" + strPhone);
				} catch (Exception e) {
					mTextView01.setText(e.toString());
					e.printStackTrace();
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}

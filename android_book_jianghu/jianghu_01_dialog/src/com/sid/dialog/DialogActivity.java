package com.sid.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DialogActivity extends Activity {
	Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        button = (Button) findViewById(R.id.buttonDialog);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(DialogActivity.this, "toast内容", Toast.LENGTH_LONG).show();
				showDialog(2);
			}
		});
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
		return dialogCreate(DialogActivity.this,id);
    }

    private Dialog dialogCreate(DialogActivity dialogActivity, int id) {

		AlertDialog.Builder builder = new AlertDialog.Builder(dialogActivity);
    	switch (id) {
		case 1:
			builder.setIcon(R.drawable.ic_action_search);
			builder.setTitle(R.string.buttonTest);
			builder.setMessage(R.string.msg);
			builder.setPositiveButton(R.string.buttonName1, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle("您点击了确认按钮");
				}
			});
			return builder.create();
		case 4:
			builder.setIcon(R.drawable.ic_action_search);
			builder.setTitle(R.string.buttonTest);
			builder.setMessage(R.string.msg);
			builder.setNeutralButton(R.string.buttonName1, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle("您点击了详情按钮");
				}
			});
			return builder.create();
		case 3:
			builder.setIcon(R.drawable.ic_action_search);
			builder.setTitle(R.string.buttonTest);
			builder.setMessage(R.string.msg);
			//设置取消按钮
			builder.setNegativeButton(R.string.buttonName1, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					setTitle("您点击了取消按钮");
				}
			});
			return builder.create();
		case 2:
			ProgressDialog dialog = new ProgressDialog(dialogActivity);
			dialog.setTitle("正在处理中……");
			dialog.setMessage("请等待");
			return dialog;
		default:
			break;
		}
		return null;
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dialog, menu);
        return true;
    }
}

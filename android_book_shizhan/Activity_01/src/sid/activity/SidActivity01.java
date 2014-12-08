package sid.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

/**
 * 创建activity的要点
 * 1.一个activity就是一个java类；并且继承Activity
 * 2.要复写onCreate方法
 * 3.每一个Activity都需要在AndroidManifest.xml中进行配置（某一个包含<intent-filter>；则这个先执行）
 * 4.为Activity添加必要的控件
 * 
 * @author Administrator
 *
 */
public class SidActivity01 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sid_activity01);
        //获取TextView组件id的定义在layout文件夹中的布局组件中进行定义：@+id/XXX
        TextView myTextView = (TextView)findViewById(R.id.myTextView);
        Button myButton = (Button)findViewById(R.id.myButton);
        myTextView.setText("my first textView");
        myButton.setText("my first button");
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sid_activity01, menu);
        return true;
    }
}

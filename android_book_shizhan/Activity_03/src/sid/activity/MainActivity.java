package sid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText factor1;
	private EditText factor2;
	private TextView symbol;
	private Button button;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        factor1 = (EditText)findViewById(R.id.param1);
        factor2 = (EditText)findViewById(R.id.param2);
        symbol = (TextView)findViewById(R.id.symbol);
        symbol.setText(R.string.symbol);
        button = (Button)findViewById(R.id.myButton);
        button.setOnClickListener(new MyOnClickListenner());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
    	//0：组的id；1：编号；1：排序；exit：显示的文字
    	menu.add(0, 1, 1, R.string.exit);
    	menu.add(0, 2, 2, R.string.about);
    	return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getItemId()==1){
    		finish();
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    class MyOnClickListenner implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.putExtra("param1", factor1.getText().toString());
			intent.putExtra("param2", factor2.getText().toString());
			intent.setClass(MainActivity.this, ResultActivity.class);
			MainActivity.this.startActivity(intent);
		}
    }
}

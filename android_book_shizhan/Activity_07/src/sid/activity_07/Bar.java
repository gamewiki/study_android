package sid.activity_07;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class Bar extends Activity {
	private ProgressBar bar1;
	private ProgressBar bar2;
	private Button button;
	private int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        bar1 = (ProgressBar)findViewById(R.id.bar1);
        bar2 = (ProgressBar)findViewById(R.id.bar2);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i==0){
					bar1.setVisibility(ProgressBar.VISIBLE);
					bar2.setVisibility(ProgressBar.VISIBLE);
				}else if(i<bar1.getMax()){
					bar1.setProgress(i);
					bar1.setSecondaryProgress(i+10);
					bar2.setProgress(i);
				}else{
					bar1.setVisibility(ProgressBar.GONE);
					bar2.setVisibility(ProgressBar.GONE);
				}
				i = i+10;
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_bar, menu);
        return true;
    }
}

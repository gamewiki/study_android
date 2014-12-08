package sid.activity_06;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Radio extends Activity {
	private TextView text;
	private RadioGroup radioGroup;
	private RadioButton radioman;
	private RadioButton radiowoman;
	private CheckBox swim;
	private CheckBox run;
	private CheckBox read;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        text = (TextView)findViewById(R.id.Text);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioman = (RadioButton)findViewById(R.id.radioman);
        radiowoman = (RadioButton)findViewById(R.id.radiowoman);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(radioman.getId()==checkedId){
					text.setText("男");
					Toast.makeText(Radio.this, "I am man", Toast.LENGTH_SHORT).show();
				}else if(radiowoman.getId()==checkedId){
					text.setText("女");
					Toast.makeText(Radio.this, "I am woman", Toast.LENGTH_SHORT).show();
				}
			}
		});

        swim = (CheckBox)findViewById(R.id.swim);
        run = (CheckBox)findViewById(R.id.run);
        read = (CheckBox)findViewById(R.id.read);
        
        swim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					text.setText("swim is onchecked");
					Toast.makeText(Radio.this, "I am swimming", Toast.LENGTH_SHORT).show();
				}else{
					text.setText("swim is not onchecked");
				}
			}
		});
        
        run.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					text.setText("swim is onchecked");
					Toast.makeText(Radio.this, "I am running", Toast.LENGTH_SHORT).show();
				}else{
					text.setText("swim is not onchecked");
				}
			}
		});
        
        read.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					text.setText("swim is onchecked");
					Toast.makeText(Radio.this, "I am reading", Toast.LENGTH_SHORT).show();
				}else{
					text.setText("swim is not onchecked");
				}
			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_radio, menu);
        return true;
    }
}

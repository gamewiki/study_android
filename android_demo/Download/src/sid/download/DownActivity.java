package sid.download;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DownActivity extends Activity {
	Button mp3 = null;
	Button text = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down);
        mp3 = (Button)findViewById(R.id.mp3);
        mp3.setOnClickListener(new MyMP3OnClickListener());
        text = (Button)findViewById(R.id.text);
        text.setOnClickListener(new MyTEXTOnClickListener());
    }
    
    class MyMP3OnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			HttpDownloader downloader = new HttpDownloader(); 
			int result = downloader.downFile("http://lrc.bzmtv.com/LRC_db/2010-2-3-JNDOPOONMMCJNHONOJRGOF0-144837.lrc", "voa", "144837.lrc");
			System.out.println(result);
		}
    }
    
    class MyTEXTOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			HttpDownloader downloader = new HttpDownloader(); 
			String lrc = downloader.downText("http://lrc.bzmtv.com/LRC_db/2010-2-3-JNDOPOONMMCJNHONOJRGOF0-144837.lrc");
			System.out.println(lrc);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_down, menu);
        return true;
    }
}

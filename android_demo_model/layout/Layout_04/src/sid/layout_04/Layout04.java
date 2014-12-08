package sid.layout_04;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Layout04 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout04);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_layout04, menu);
        return true;
    }
}

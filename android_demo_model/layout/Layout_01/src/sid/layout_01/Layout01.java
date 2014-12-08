package sid.layout_01;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Layout01 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout01);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_layout01, menu);
        return true;
    }
}

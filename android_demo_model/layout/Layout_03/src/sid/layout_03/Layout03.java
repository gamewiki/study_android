package sid.layout_03;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Layout03 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout03);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_layout03, menu);
        return true;
    }
}

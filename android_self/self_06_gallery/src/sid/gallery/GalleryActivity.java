package sid.gallery;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GalleryActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gallery);

		Integer[] images = { R.drawable.img001, R.drawable.img003,
				R.drawable.img004, R.drawable.img005, R.drawable.img002 };

		ImageAdapter adapter = new ImageAdapter(this, images);
		adapter.createReflectedImages();

		GalleryFlow galleryFlow = (GalleryFlow) findViewById(R.id.gallery_flow);
		galleryFlow.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_gallery, menu);
		return true;
	}
}

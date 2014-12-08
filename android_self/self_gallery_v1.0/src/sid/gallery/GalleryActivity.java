package sid.gallery;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
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
		
		for (int i = 1; i < images.length+1; i++) {
			Resources res=getResources();
			System.out.println("============"+"img"+(i<10?"00"+i:i<100?"0"+i:i));
			int id = res.getIdentifier("img"+(i<10?"00"+i:i<100?"0"+i:i),"drawable",getPackageName());
			System.out.println(id);
			System.out.println(images[i-1]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_gallery, menu);
		return true;
	}
}

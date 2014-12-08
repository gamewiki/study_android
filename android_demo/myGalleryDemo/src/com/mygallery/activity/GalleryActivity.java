package com.mygallery.activity;



import com.cn.picture.ViewRotate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;

public class GalleryActivity extends Activity {
	
	int[] imgIds = {R.drawable.a, R.drawable.b, 
			R.drawable.c, R.drawable.d, R.drawable.e};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mygallery);
        
        Gallery gallery = (Gallery) findViewById(R.id.gallery);
        BaseAdapter adapter = new GalleryAdpter(this);
        gallery.setAdapter(adapter);
        
        OnItemClickListener listener = new OnItemClickListener() {

        	
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Gallery gallery = (Gallery) findViewById(R.id.gallery);
				gallery.setSelection(position);
			}
        };
        gallery.setSelection(Integer.MAX_VALUE/2);
        gallery.setOnItemClickListener(listener);
    }
    
    class GalleryAdpter extends BaseAdapter {

    	private LayoutInflater mInflater=GalleryActivity.this.getLayoutInflater();
    	
    	public  GalleryAdpter(Context context)
    	{
    		mInflater=GalleryActivity.this.getLayoutInflater();
    	}
    	
    	
		public int getCount() {
			return Integer.MAX_VALUE;
//			return imgIds.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) 
		{
	

			convertView=mInflater.inflate(R.layout.mygaller_item,null);
            new ViewRotate(GalleryActivity.this,convertView,mInflater);
			return  convertView;

		}
    }
}
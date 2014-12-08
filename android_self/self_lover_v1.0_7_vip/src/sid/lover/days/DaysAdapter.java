package sid.lover.days;

import java.util.List;

import sid.lover.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/** 自定义的Adapter，继承android.widget.BaseAdapter */
public class DaysAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	/**变量声明 mIcon1*/
	private Bitmap mIcon1;
	private List<String> items;
	private List<String> paths;

	/* MyAdapter的构造器，传入三个参数 */
	public DaysAdapter(Context context, List<String> it, List<String> pa) {
		/* 参数初始化 */
		mInflater = LayoutInflater.from(context);
		items = it;
		paths = pa;
		mIcon1 = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.clock);
	}

	/* 因继承BaseAdapter，需覆盖以下方法 */
	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			/* 使用自定义的file_row作为Layout */
			convertView = mInflater.inflate(R.layout.note_row, null);
			/* 初始化holder的text与icon */
			holder = new ViewHolder();
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.icon = (ImageView) convertView.findViewById(R.id.icon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(paths.get(position));
		holder.icon.setImageBitmap(mIcon1);
		return convertView;
	}

	/* class ViewHolder */
	private class ViewHolder {
		TextView text;
		ImageView icon;
	}
}

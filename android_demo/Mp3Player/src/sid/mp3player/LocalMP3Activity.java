package sid.mp3player;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import sid.model.MP3Info;
import sid.utils.AppConstant;
import sid.utils.FileUtils;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocalMP3Activity extends ListActivity {
	private static final int UPDATE = 1;
	private static final int ABOUT = 2;
	private List<MP3Info> mp3InfoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_mp3_list);
	}
	
	@Override
	protected void onResume() {
		updateListView();
		super.onResume();
	}

	/**
	 * 用户点击menu之后；会调用该方法 我们可以再这个方法中加入自己的按钮控件
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 参数分别是：组id，itemid，排序位置，文本内容
		menu.add(0, UPDATE, 1, R.string.mp3list_update);
		menu.add(0, ABOUT, 2, R.string.mp3list_about);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 用户点击menu按钮后调用的方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// System.out.println("itemID:"+item.getItemId());
		if (item.getItemId() == UPDATE) {
		} else if (item.getItemId() == ABOUT) {
			// System.out.println("用户点击关于");
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * 点击列表的对应数据调用的方法
	 * 传递MP3Info
	 * 启动service
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//根据用户点击的位置来获取相应的MP3对象
		if (mp3InfoList!=null&&!mp3InfoList.isEmpty()) {
			MP3Info mp3Info = mp3InfoList.get(position);
			Intent intent = new Intent();
			intent.putExtra("mp3Info", mp3Info);
			intent.setClass(LocalMP3Activity.this, PlayerActivity.class);
			this.startActivity(intent);
		}
		super.onListItemClick(l, v, position, id);
	}

	/**
	 * 更新歌曲列表所用到的方法
	 */
	private void updateListView() {
		mp3InfoList = getMP3InfoList(AppConstant.URL.BASE_URL_LOCAL_DIR);
		SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3InfoList);
		// 将这个simpleAdapter对象设置到ListAdapter当中
		setListAdapter(simpleAdapter);
	}

	/**
	 * 生成一个simpleAdapter对象
	 * 用于动态的生成歌曲列表
	 * @param mp3InfoList
	 * @return
	 */
	private SimpleAdapter buildSimpleAdapter(List<MP3Info> mp3InfoList) {
		// 生成一个List对象，并按照simpleAdapter的标准，将MP3Info信息放入list当中
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Iterator<MP3Info> iterator = mp3InfoList.iterator(); iterator.hasNext();) {
			MP3Info mp3Info = (MP3Info) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("mp3_name", mp3Info.getMp3Name());
			map.put("mp3_size", mp3Info.getMp3Size());
			list.add(map);
		}
		// 创建一个simpleAdapter对象
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
				R.layout.mp3_info_item,
				new String[] { "mp3_name", "mp3_size" }, new int[] {
						R.id.mp3_name, R.id.mp3_size });
		return simpleAdapter;
	}

	/**
	 * 获取本地文件列表
	 * 并生成相应的MP3Info
	 * 同时添加到List中
	 * @param xmlStr
	 * @return
	 */
	private List<MP3Info> getMP3InfoList(String dir){
		List<MP3Info> mp3Infos = new ArrayList<MP3Info>();
		MP3Info mp3Info = new MP3Info();
		FileUtils fileUtils = new FileUtils();
		File[] files = fileUtils.getFiles(dir);
		for (File file : files) {
			if (file.getName().endsWith("mp3")||file.getName().endsWith("MP3")) {
				mp3Info.setMp3Name(file.getName());
				mp3Info.setMp3Size(file.length()+"");
				mp3Info.setLrcName(file.getName().substring(0,file.getName().lastIndexOf(".")+1)+"lrc");
				mp3Infos.add(mp3Info);
			}
		}
		return mp3Infos;
	}

}

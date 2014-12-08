package sid.mp3player;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import sid.download.HttpDownloader;
import sid.model.MP3Info;
import sid.mp3player.service.DownloadService;
import sid.utils.AppConstant;
import sid.xml.Mp3ListContentHandler;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Mp3Activity extends ListActivity {
	private List<MP3Info> mp3InfoList = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_mp3_list);
	}

	/**
	 * 用户点击menu之后；会调用该方法 我们可以再这个方法中加入自己的按钮控件
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 参数分别是：组id，itemid，排序位置，文本内容
		menu.add(0, AppConstant.SETTING, 1, R.string.mp3list_setting);
		menu.add(0, AppConstant.EXIT, 2, R.string.mp3list_exit);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 用户点击menu按钮后调用的方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == AppConstant.SETTING) {
			updateListView();
		} else if (item.getItemId() == AppConstant.EXIT) {
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
		MP3Info mp3Info = mp3InfoList.get(position);
//		System.out.println(mp3Info);
		Intent intent = new Intent();
		intent.putExtra("mp3Info", mp3Info);
		intent.putExtra("dirPath", AppConstant.URL.BASE_URL_LOCAL_DIR);
		intent.putExtra("mp3URL", AppConstant.URL.BASE_URL_MP3);
		intent.setClass(Mp3Activity.this, DownloadService.class);
		startService(intent);
		super.onListItemClick(l, v, position, id);
	}

	/**
	 * 更新歌曲列表所用到的方法
	 */
	private void updateListView() {
		// 下载包含所有mp3文件的xml文件
		String xml = downloadXML(AppConstant.URL.BASE_URL_RESOURCE);
		// 解析xml；并将结果放入MP3Info对象，并放入MP3InfoList当中
		mp3InfoList = parse(xml.trim());
		SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3InfoList);
		// 将这个simpleAdapter对象设置到ListAdapter当中
		setListAdapter(simpleAdapter);
	}

	/**
	 * 根据传入的url进行下载xml文件
	 * 并作为String对象返回
	 * @param urlStr
	 * @return
	 */
	private String downloadXML(String urlStr) {
		HttpDownloader httpDownloader = new HttpDownloader();
		return httpDownloader.downText(urlStr);
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
	 * 读取转换xml文件
	 * 并生成相应的MP3Info
	 * 同时添加到List中
	 * @param xmlStr
	 * @return
	 */
	private List<MP3Info> parse(String xmlStr) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<MP3Info> mp3Inofs = new ArrayList<MP3Info>();
		try {
			XMLReader xmlReader = saxParserFactory.newSAXParser()
					.getXMLReader();
			Mp3ListContentHandler mp3ListContentHandler = new Mp3ListContentHandler(
					mp3Inofs);
			xmlReader.setContentHandler(mp3ListContentHandler);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			for (Iterator<MP3Info> iterator = mp3Inofs.iterator(); iterator
					.hasNext();) {
				MP3Info mp3Info = iterator.next();
				System.out.println(mp3Info);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mp3Inofs;
	}
}

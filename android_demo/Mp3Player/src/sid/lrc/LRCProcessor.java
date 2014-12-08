package sid.lrc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LRCProcessor {

	/**
	 * 返回一个队列；
	 * 队列中包含两个队列；
	 * 分别为时间点和歌词信息
	 * @param inputStream
	 * @return
	 */
	public ArrayList<Queue> process(InputStream inputStream){
		//存放时间点数据
		Queue<Long> timeMills = new LinkedList<Long>();
		//存放时间点所对应的歌词信息
		Queue<String> messages = new LinkedList<String>();
		//返回的队列
		ArrayList<Queue> queues = new ArrayList<Queue>();
		try {
			//创建一个BufferReader对象；因为他有读行功能
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String temp = null;
			int i = 0;
			//创建一个正则表达式
			Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
			String result = null;
			boolean b = true;
			//根据正则表达式按行找出时间点和歌词信息
			while ((temp = bufferedReader.readLine())!=null) {
				i++;
				Matcher matcher = pattern.matcher(temp);
				if (matcher.find()) {
					if (result!=null) {
						messages.add(result);
					}
					String timeStr = matcher.group();
					Long timeMill = time2Long(timeStr.substring(1, timeStr.length()-1));
					if (b) {
						timeMills.offer(timeMill);
					}
					String msg = temp.substring(10);
					result = "" + msg + "\n";
				}else{
					result = result + temp + "\n";
				}
			}
			messages.add(result);
			queues.add(timeMills);
			queues.add(messages);
		} catch (PatternSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queues;
	}

	/**
	 * 将分钟，秒全部转换成毫秒级的
	 * @param timeStr
	 * @return
	 */
	private Long time2Long(String timeStr) {
		String s[] = timeStr.split(":");
		int min = Integer.parseInt(s[0]);
		String ss[] = s[1].split("\\.");
		int sec = Integer.parseInt(ss[0]);
		int mill = Integer.parseInt(ss[1]);
		return min*60*1000+sec*1000+mill*10l;
	}
}

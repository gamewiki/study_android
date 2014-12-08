package sid.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDownloader {
	public static final int SUCCESS = 0;
	public static final int FAILED = -1;
	public static final int EXIST = 1;

	/**
	 * 根据URL下载文件，前提是这个文件一定要是文本文件
	 * 1.创建一个URL
	 * 2.通过URL的openConnection创建HttpUrlConnection对象
	 * 3.获得inputStream
	 * 
	 * @param urlString
	 * @return
	 */
	public String downText(String urlString) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader br = null;
		try {
			URL url = new URL(urlString);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			while ((line = br.readLine())!= null) {
				sb.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	
	/**
	 * 返回整形参数
	 * @param urlString
	 * @param dir
	 * @param fileName
	 * @return
	 */
	public int downFile(String urlString,String dir,String fileName) {
		InputStream input = null;
			try {
				FileUtils fileUtils = new FileUtils();
				if(fileUtils.isExist(fileName,dir)){
					return EXIST;
				}else{
					input = getInputStreamFromUrl(urlString);
					File resultFile = fileUtils.wirte2SDFromInput(dir, fileName, input);
					if(resultFile==null){
						return FAILED;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				return FAILED;
			}finally{
				try {
					if(input!=null){
						input.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		return SUCCESS;
	}

	/**
	 * 根据输入的url，获得输入流
	 * 
	 * @param urlString
	 * @return
	 * @throws IOException
	 */
	private InputStream getInputStreamFromUrl(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		InputStream input =  urlConn.getInputStream();
		return input;
	}
}

package sid.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {

	private String SDCardRoot = null;
	
	public FileUtils(){
		//获得当前外部存储设备的目录
		SDCardRoot = Environment.getExternalStorageDirectory()+File.separator;
	}
	
	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public File createFileInSDCard(String fileName,String dir) throws IOException{
		File file = new File(SDCardRoot+dir+File.separator+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public File createDirInSDCard(String dirName){
		File dir = new File(SDCardRoot+dirName+File.separator);
		System.out.println(dir.mkdir());
		return dir;
	}

	/**
	 * 判断文件是否已经存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isExist(String fileName,String path){
		File file = new File(SDCardRoot+path+File.separator+fileName);
		return file.exists();
	}
	
	/**
	 * 将文件夹、文件名和输入流传入sdka
	 * 
     * <!-- 写入SD卡的权限设置 -->
 	 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     * <!-- 访问网络的设置 -->
	 * <uses-permission android:name="android.permission.INTERNET" />
	 * 
	 * @param dirpath 写入sdcard的文件夹名称
	 * @param fileName 写入的文件名称
	 * @param input 输入流
	 * @return
	 */
	public File wirte2SDFromInput(String dirpath,String fileName,InputStream input){
		File file = null;
		OutputStream output = null;
		try {
			createDirInSDCard(dirpath);
			file = createFileInSDCard(fileName,dirpath);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4*1024];
			int temp ;
			while ((temp = input.read(buffer)) != -1) {
				output.write(buffer,0,temp);
			}
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(output!=null){
					output.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}
	
	public File[] getFiles(String path){
		File file = new File(SDCardRoot+path);
		System.out.println("读取的文件路径："+SDCardRoot+path);
		File[] files = file.listFiles();
		return files;
	}
	public String getSDPATH() {
		return SDCardRoot;
	}
	public void setSDPATH(String sDPATH) {
		SDCardRoot = sDPATH;
	}
	
}

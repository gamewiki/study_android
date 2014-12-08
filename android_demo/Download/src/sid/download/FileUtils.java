package sid.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {

	private String SDPATH = null;
	public FileUtils(){
		SDPATH = Environment.getExternalStorageDirectory()+"/";
	}
	
	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public File createSDFile(String fileName) throws IOException{
		File file = new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	
	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public File createSDDir(String dirName){
		File dir = new File(SDPATH+dirName);
		dir.mkdir();
		return dir;
	}

	/**
	 * 判断文件是否已经存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isExist(String fileName){
		File file = new File(SDPATH+fileName);
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
			createSDDir(dirpath);
			file = createSDFile(dirpath+"/"+fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4*1024];
			while ((input.read(buffer)!=-1)) {
				output.write(buffer);
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
	public String getSDPATH() {
		return SDPATH;
	}
	public void setSDPATH(String sDPATH) {
		SDPATH = sDPATH;
	}
	
}

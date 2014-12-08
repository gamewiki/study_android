package sid.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.os.Environment;

public class FileUtils {
	private volatile static FileUtils fileutils = null;
	private static String SDCardRoot = null;
	private FileUtils(){
		//获得当前外部存储设备的目录
		SDCardRoot = Environment.getExternalStorageDirectory()+File.separator;
		this.createDirInSDCard(AppConstant.LOVERDIR);
	}
	
	public static FileUtils getInstance(){
		if (fileutils==null) {
			synchronized (FileUtils.class) {
				if (fileutils==null) {
					fileutils = new FileUtils();
				}
			}
			fileutils = new FileUtils();
		}
		return fileutils;
	}

	
	/**
	 * 在SD卡上创建文件
	 * 
	 * @param fileName 文件名称
	 * @param dir 指定目录
	 * @return
	 * @throws IOException
	 */
	public ResultLover createFileInSDCard(String fileName,String dir) throws IOException{
		ResultLover rl = new ResultLover();
		createDirInSDCard(AppConstant.LOVERDIR+File.separator+dir);
		File file = new File(SDCardRoot+File.separator+AppConstant.LOVERDIR+File.separator+dir+File.separator+fileName);
		if (!file.exists()) {
			file.createNewFile();
			rl.setObj(file);
		}else{
			rl.setMsg("指定文件已存在！请重新指定文件名称！");
			rl.setResult(false);
		}
		return rl;
	}
	
	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 * @return
	 */
	public File createDirInSDCard(String dirName){
		File dir = new File(SDCardRoot+dirName+File.separator);
		if (!dir.exists()) {
			dir.mkdir();
		}
		return dir;
	}

	/**
	 * 判断文件是否已经存在
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean isExist(String fileName,String dir){
		File file = new File(SDCardRoot+File.separator+AppConstant.LOVERDIR+File.separator+dir+File.separator+fileName);
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
			ResultLover rl = createFileInSDCard(fileName,dirpath);
			if (rl.getResult()) {
				file = (File) rl.getObj();
				output = new FileOutputStream(file);
				byte buffer[] = new byte[4*1024];
				int temp ;
				while ((temp = input.read(buffer)) != -1) {
					output.write(buffer,0,temp);
				}
				output.flush();
			}
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

	/**
	 * 
	 * 压缩且保存图片到SDCard
	 * @param rawBitmap 位图
	 * @param dir 存储目录
	 * @param fileName 文件名称
	 * @param quality 压缩比的值,0-100.0 意味着小尺寸压缩,100意味着高质量压缩
	 */
	public ResultLover compressAndSaveBitmapToSDCard(Bitmap rawBitmap,String dir,
			String fileName, int quality) {
		ResultLover rl = new ResultLover();
		try {
			createDirInSDCard(AppConstant.LOVERDIR+File.separator+dir);
			String saveFilePath = SDCardRoot + File.separator + AppConstant.LOVERDIR + File.separator + dir+ File.separator + fileName+".jpeg";
			File saveFile = new File(saveFilePath);
			if (!saveFile.exists()) {
				saveFile.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(saveFile);
				if (fileOutputStream != null) {
					// 把位图的压缩信息写入到一个指定的输出流中
					// 第一个参数format为压缩的格式
					// 第二个参数quality为图像压缩比的值,0-100.0 意味着小尺寸压缩,100意味着高质量压缩
					// 第三个参数stream为输出流
					rawBitmap.compress(Bitmap.CompressFormat.JPEG, quality,fileOutputStream);
				}
				fileOutputStream.flush();
				fileOutputStream.close();
				rl.setObj(dir+ File.separator + fileName+".jpeg");
			}else{
				rl.setMsg("文件已存在，请重新存储");
				rl.setResult(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rl.setMsg("存储失败");
			rl.setResult(false);
		}
		return rl;
	}
	public File[] getFiles(String path){
		File file = new File(SDCardRoot+File.separator+path);
		File[] files = file.listFiles();
		return files;
	}
	public String getStoryFilePath(){
		return SDCardRoot + File.separator + AppConstant.LOVERDIR + File.separator;
	}
	public String getSDPATH() {
		return SDCardRoot;
	}
	public void setSDPATH(String sDPATH) {
		SDCardRoot = sDPATH;
	}
	
}

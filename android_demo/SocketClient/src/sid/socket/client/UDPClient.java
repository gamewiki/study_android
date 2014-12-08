package sid.socket.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class UDPClient {
	public static void main(String[] args) {
		try {
			//创建一个socket对象，指定服务器端的ip和端口号
			Socket socket = new Socket("192.168.1.104",4567);
			//使用inputStream读取硬盘上的文件
			InputStream inputStream = new FileInputStream("f://file/words.txt");
			//从socket当中得到outputstream
			OutputStream outputStream = socket.getOutputStream();
			byte buffer[] = new byte[1024*4];
			int temp = 0;
			//将inputStream当中的数据取出，并写入到outputstream当中
			while((temp = inputStream.read(buffer))!= -1){
				outputStream.write(buffer,0,temp);
			}
			outputStream.flush();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

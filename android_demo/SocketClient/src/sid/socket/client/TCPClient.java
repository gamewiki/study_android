package sid.socket.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String[] args) {
		try {
			//创建一个DatagramSocket对象，指定服务器端的端口号
			DatagramSocket socket = new DatagramSocket(4567);
			//创建一个DatagramSocket对象，指定服务器端的ip
			InetAddress serverAddress = InetAddress.getByName("192.168.1.104");
			String str = "hello";
			byte data[] = str.getBytes();
			//创建一个Datagrampacket对象，并指定要将这个数据包发送的网络当中的哪个地址以及端口号
			DatagramPacket pecket = new DatagramPacket(data, data.length,serverAddress,4567);
			//调用socket对象的send方法，发送数据
			socket.send(pecket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

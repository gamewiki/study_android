package sid.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SocketActivity extends Activity {
	Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        button = (Button)findViewById(R.id.startButton);
        button.setOnClickListener(new StartSocket());
    }
    
    class StartSocket implements OnClickListener{
		@Override
		public void onClick(View v) {
			new SocketThread().start();
		}
    }
    
    class SocketThread extends Thread{
//    	/**
//    	 * 基于TCP的服务器方面
//    	 */
//    	@Override
//    	public void run() {
//    		//声明一个ServerSocket对象
//    		ServerSocket serverSocket = null;
//    		try {
//    			//创建一个ServerSocket对象，并让这个Socket在4567端口监听
//				serverSocket = new ServerSocket(4567);
//				//调用ServerSocket的accept方法接收客户端所发送的请求
//				//如果客户端没有发送请求，则会阻塞；如果客户端发送请求后；会返回一个socket对象（代表客户端和服务器端的一个链接）
//				//然后开始执行之下的代码
//				Socket socket = serverSocket.accept();
//				//从socket对象中得到InputStream
//				InputStream inputStream = socket.getInputStream();
//				byte buffer[] = new byte[1024*4];
//				int temp = 0;
//				//从InputStream中读取客户端所发送的数据
//				while((temp = inputStream.read(buffer))!= -1){
//					System.out.println(new String(buffer,0,temp));
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				try {
//					serverSocket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//    	}
    	
    	/**
    	 * 基于UDP协议的方法
    	 */
    	@Override
    	public void run() {
    		try {
    			//创建一个DatagramSocket对象，并指定监听的端口号
				DatagramSocket datagramSocket = new DatagramSocket(4567);
				byte data[] = new byte[1024];
				//创建一个空的DatagramPacket对象
				DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
				//使用receive方法接收客户端所发送的数据
				//类似于TCP协议方法中的accept，同样是阻塞方法
				datagramSocket.receive(datagramPacket);
				System.out.println(datagramPacket.getLength());

				String result1 = new String(datagramPacket.getData());
				String result2 = new String(datagramPacket.getData(),datagramPacket.getOffset(),datagramPacket.getLength());
				System.out.println("UDPClient发送的数据："+result1);
				System.out.println("UDPClient发送的完整数据："+result2);
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_socket, menu);
        return true;
    }
}

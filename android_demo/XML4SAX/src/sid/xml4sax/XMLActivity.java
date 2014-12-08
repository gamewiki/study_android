package sid.xml4sax;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class XMLActivity extends Activity {
	Button button = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new MyClickListener());
    }

    class MyClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
//			HttpDownloader hd = new HttpDownloader();
//			String resultStr = hd.downText("");
			String resultStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
								+"<workers>"
								+"<worker id=\"AQ01\"><name>Mark</name>"
								+"<sex>男</sex>"
								+"<status>经理</status>"
								+"<address>北京</address>"
								+"<money>4000</money>"
								+"</worker>"
								+"<worker id=\"AQ02\">"
								+"<name>LUCY</name>"
								+"<sex>女</sex>"
								+"<status>员工</status>"
								+"<address>上海</address>"
								+"<money>1000</money>"
								+"</worker>"
								+"<worker id=\"AQ03\">"
								+"<name>lily</name>"
								+"<sex>女</sex>"
								+"<status>员工</status>"
								+"<address>北京</address>"
								+"<money>2000</money>"
								+"</worker>"
								+"</workers>";
			System.out.println(resultStr);
			try {
				//创建一个saxParserFactory
				SAXParserFactory factory = SAXParserFactory.newInstance();
				XMLReader reader = factory.newSAXParser().getXMLReader();
				//为XMLreader设置内容处理器
				reader.setContentHandler(new MyContentHandler());
				//开始解析文件
				reader.parse(new InputSource(new StringReader(resultStr)));
			} catch (FactoryConfigurationError e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_xml, menu);
        return true;
    }
}

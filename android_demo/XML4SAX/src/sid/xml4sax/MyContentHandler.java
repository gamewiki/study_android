package sid.xml4sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyContentHandler extends DefaultHandler {
	String hisname,address,money,sex,status,tagname;
	
	public void startDocument() throws SAXException {
		System.out.println("startDocument……");
	}
	public void endDocument() throws SAXException {
		System.out.println("endDocument……");
	}

	/**
	 * uri：
	 * localname：不带前缀的名字
	 * qname：带前缀的名字
	 * attribute：属性名
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagname = localName;
		if(localName.equals("worker")){
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println(attributes.getLocalName(i)+"="+attributes.getValue(i));
			}
		}
	}
	
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(localName.equals("worker")){
			this.printout();
		}
	}
	
	private void printout() {
		System.out.println("name:"+hisname);
		System.out.println("sex:"+sex);
		System.out.println("status:"+status);
		System.out.println("address:"+address);
		System.out.println("money:"+money);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(tagname.equals("name")){
			hisname = new String(ch,start,length);
		}else if(tagname.equals("sex")){
			sex = new String(ch,start,length);
		}else if(tagname.equals("status")){
			status = new String(ch,start,length);
		}else if(tagname.equals("address")){
			address = new String(ch,start,length);
		}else if(tagname.equals("money")){
			money = new String(ch,start,length);
		}
	}
}

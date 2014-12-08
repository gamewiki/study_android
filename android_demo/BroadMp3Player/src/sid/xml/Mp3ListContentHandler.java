package sid.xml;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import sid.model.MP3Info;

public class Mp3ListContentHandler extends DefaultHandler {
	private List<MP3Info> infos = null;
	private MP3Info mp3Info = null;
	private String tagName = null;
	/** 
	 * 为防止characters执行多遍
	 * 造成数据丢失
	 * 设置一个sb用来存储数据
	 */
	private StringBuffer sb = new StringBuffer();


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String temp = new String(ch, start, length);
		System.out.println("the temp is :"+temp);
        //不管在startElement到endElement的过程中，执行了多少次characters， 都会将内容添加到StringBuilder中，不会丢失内容 
		sb.append(temp);
		super.characters(ch, start, length);
	}
	

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
        //原来在characters中取值，现改在此取值  
        String value = sb.toString();  
		if(localName.equals("id")){
			mp3Info.setId(Integer.valueOf(value));
		}else if(localName.equals("mp3name")){
			mp3Info.setMp3Name(value);
		}else if(localName.equals("mp3size")){
			mp3Info.setMp3Size(value);
		}else if(localName.equals("lrcname")){
			mp3Info.setLrcName(value);
		}else if(localName.equals("lrcsize")){
			mp3Info.setLrcSize(value);
		} 
		if(localName.equals("resource")){
			System.out.println("end resource");
			infos.add(mp3Info);
		}
		tagName = null;
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		tagName = localName;
		sb.setLength(0);
		if(tagName.equals("resource")){
			System.out.println("start resource");
			mp3Info = new MP3Info();
		}
		super.startElement(uri, localName, qName, attributes);
	}

	public Mp3ListContentHandler(List<MP3Info> infos) {
		super();
		this.infos = infos;
	}

	public List<MP3Info> getInfos() {
		return infos;
	}

	public void setInfos(List<MP3Info> infos) {
		this.infos = infos;
	}
}

package jdk.detail.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class UserSaxHandler.
 * x
 *
 * @date 2013-7-8 17:47:43
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class UserSaxHandler extends DefaultHandler {

	@Override
	public void characters(char[] chs, int start, int length) throws SAXException {
		super.characters(chs, start, length);
		String nodeText = new String(chs, start, length);
		System.out.println("当前节点文本为:"+nodeText);
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		System.out.println("解析xml结束");
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		super.endElement(namespaceURI, localName, qName);
	}

	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("开始解析xml");
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
		super.startElement(namespaceURI, localName, qName, atts);
		System.out.println("namespaceURI:"+namespaceURI+",localName:"+localName+",qName:"+qName);
	}
	

}

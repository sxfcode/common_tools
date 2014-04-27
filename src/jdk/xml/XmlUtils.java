package jdk.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class XmlUtils. docoment代表整个文档 node是文档中的最基本的抽象 元素
 * element是node的子对象，是最常用的操作对象。
 * 
 * @date 2013-7-1 15:54:06
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class XmlUtils {

	/**
	 * 读取xml文件.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void read(String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document d = db.parse(fileName);
			// d.normalize();
			NodeList list = d.getElementsByTagName("学生");
			for (int i = 0; i < list.getLength(); i++) {
				System.out.println("第" + i + "个元素:");
				Element e = (Element) list.item(i);
				NodeList atts = e.getChildNodes();
				for (int j = 0; j < atts.getLength(); j++) {
					if ("#text".equals(atts.item(j).getNodeName())) {
						continue;
					}
					System.out.println(atts.item(j).getNodeName() + ":"
							+ atts.item(j).getTextContent());
					atts.item(j).setTextContent(
							atts.item(j).getTextContent() + "testEnd");
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 写入xml.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void write(String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// step 1:创建dom树
			DocumentBuilder domBuilder = dbf.newDocumentBuilder();
			Document doc = domBuilder.newDocument();
			Element root = doc.createElement("学生会");
			doc.appendChild(root);
			for (int i = 0; i < 3; i++) {
				Element student = doc.createElement("学生");
				root.appendChild(student);

				Element number = doc.createElement("编号");
				number.setTextContent("content" + i);
				// nodeValue可能用于节点比较,排序之类。
				// number.setNodeValue("value" + i);
				student.appendChild(number);

				Element sex = doc.createElement("性别");
				sex.setTextContent("content 男" + i);
				// sex.setNodeValue("value 男");
				student.appendChild(sex);
			}
			doc.normalize();

			// step 2:将dom树写到文件中
			TransformerFactory formerFactory = TransformerFactory.newInstance();
			Transformer former = formerFactory.newTransformer();
			Properties properties = former.getOutputProperties();
			properties.setProperty(OutputKeys.ENCODING, "gb2312");
			properties.setProperty(OutputKeys.VERSION, "2.0");
			properties.setProperty(OutputKeys.METHOD, "xml");
			former.setOutputProperties(properties);
			DOMSource source = new DOMSource(doc);
			File file = new File(fileName);
			StreamResult result = new StreamResult(file);
			former.transform(source, result);
			System.out.println("输出dom到文件:" + fileName);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对象转xml.
	 *
	 * @param obj comments
	 * @param fileName comments
	 */
	public static void object2Xml(Object obj,String fileName) {
		// step 1:组装对象 
		Map<String,ArrayList<UserBean>> map = new HashMap<String,ArrayList<UserBean>>();
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		for (int i = 0; i < 3; i++) {
			UserBean user = new UserBean(i+"","name"+i);
			list.add(user);
		}
		map.put("用户列表", list);
		
		UserBean user = new UserBean("1","小白");
		
		// 将对象输出到文件
		File file = new File(fileName);
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			XMLEncoder encoder = new XMLEncoder(bos);
			encoder.writeObject(user);
			// 注意关闭
			encoder.close();
			System.out.println("输出对象到 xml文件:"+fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * xml转对象.
	 *
	 * @param fileName comments
	 * @param obj comments
	 */
	public static void xml2Objet(String fileName,Object obj) {
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName));
			XMLDecoder decoder = new XMLDecoder(bis);
			UserBean user  = (UserBean)decoder.readObject();
			System.out.println("输出:");
			System.out.println(user.getId());
			System.out.println(user.getName());
			// 注意关闭
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将xml按照xsl指定格式输出到html.
	 *
	 * @param xmlName comments
	 * @param htmlName comments
	 * @param xslName comments
	 */
	public static void writeJaxp(String xmlName,String htmlName,String xslName) {
		try {
			// step 1:指定输出格式(通过xsl)
			TransformerFactory formerFactory = TransformerFactory.newInstance();
			StreamSource xslsource = new StreamSource(new File(xslName));
			Transformer former = formerFactory.newTransformer(xslsource);
			Properties properties = former.getOutputProperties();
			properties.setProperty(OutputKeys.ENCODING, "UTF-8");
			properties.setProperty(OutputKeys.VERSION, "1.0");
			properties.setProperty(OutputKeys.METHOD, "html");
			
			// step 2:指定输入xml
			StreamSource xmlSource = new StreamSource(new File(xmlName));
			
			// step 3:指定输出html
			StreamResult htmlResult = new StreamResult(new File(htmlName));
			
			// step 4:执行
			former.transform(xmlSource, htmlResult);
			System.out.println("输出xml到文件html:" + htmlName);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 通过sax方式来解析xml.
	 *
	 * @param fileName comments
	 */
	public static void readSax(String fileName){
		SAXParserFactory saxParserFac = SAXParserFactory.newInstance();
		SAXParser saxParser;
		try {
			saxParser = saxParserFac.newSAXParser();
			UserSaxHandler handler = new UserSaxHandler();
			saxParser.parse(new File(fileName), handler);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// read("D:/dev/workspace/common_tools/resource/students.xml");
		// write("D:/dev/workspace/common_tools/resource/students3.html");
		//object2Xml(null,"D:/dev/workspace/common_tools/resource/students4.xml");
		//xml2Objet("D:/dev/workspace/common_tools/resource/students4.xml",null);
//		 writeJaxp("D:/dev/workspace/common_tools/resource/students.xml",
//				 "D:/dev/workspace/common_tools/resource/students.html",
//				 "D:/dev/workspace/common_tools/resource/students.xsl");
		 readSax("D:/dev/workspace/common_tools/resource/students.xml");
	}
}
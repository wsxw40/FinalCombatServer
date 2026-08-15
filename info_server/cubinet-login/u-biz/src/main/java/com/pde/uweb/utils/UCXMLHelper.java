package com.pde.uweb.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

//import org.apache.log4j.Logger;
//import org.w3c.dom.Document;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;
//import com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;

//import org.apache.log4j.Logger;
//import org.jdom2.Document;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;

/**
 * ================================================ Discuz! Ucenter API for JAVA
 * ================================================ XML工具类，处理UC Client接收到返回结果。
 * UC Client会收到UC Server返回的XML结果 该类将XML中的数据提取成一个List按顺序读取即可。
 * 
 */

public class UCXMLHelper {

	private static final Logger logger = Logger.getLogger(UCXMLHelper.class);

	public static LinkedList<String> unserialize(String input) {

		LinkedList<String> result = new LinkedList<String>();
		SAXBuilder builder = new SAXBuilder();
		StringReader sr = new StringReader(input);
		try {
			Document doc = builder.build(sr);
			Element root = doc.getRootElement();
			List<Element> childs = root.getChildren("item");

			for (Element element : childs) {
				result.add(element.getValue().trim());
			}
		} catch (JDOMException | IOException e) {
			logger.error(e);
		} finally {
			if (sr != null) {
				sr.close();
			}
		}

		return result;
	}
	// public static LinkedList<String> unserialize(String input) {
	//
	// LinkedList<String> result = new LinkedList<String>();
	//
	// DOMParser parser = new DOMParser();
	// try {
	// parser.parse(new InputSource(new StringReader(input)));
	// Document doc = parser.getDocument();
	// NodeList nl = doc.getChildNodes().item(0).getChildNodes();
	// int length = nl.getLength();
	// for (int i = 0; i < length; i++) {
	// if (nl.item(i).getNodeType() == Document.ELEMENT_NODE)
	// result.add(nl.item(i).getTextContent());
	// }
	// } catch (SAXException e) {
	// logger.error(e);
	// } catch (IOException e) {
	// logger.error(e);
	// }
	// return result;
	// }

}

package com.siri.dom;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest extends DefaultHandler { // 내가 원하는 것만 핸들러 사용가능하다.
	// sax: 이벤트 기반의 파서
	// SAXParseException <-- XML문서에 관련된 에러(Well - formed하지 못한 문서)
	// ( valid하지 못한 문서)
	SAXParserFactory factory;
	SAXParser parser;

	public SAXTest() {
		try {
			factory = SAXParserFactory.newInstance(); // getInstance()와 같이 공유메모리를 사용하고 있다.
			parser = factory.newSAXParser();

//			parser.parse("SAX파싱하고자 하는 XML문서 URL", dh);
			parser.parse("xml/0701/test.xml", this); // this: 핸들러메소드(콜백메소드)의 위치
			// root를 만나고 a태그를 만나고 hello를 만나고 b를 만나고 goodDay를 만나고

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<" + qName);

		for (int i = 0; i < attributes.getLength(); i++) {
			String attrName = attributes.getQName(i); // 속성이름
			String attrValue = attributes.getValue(i); // 속성 값

			System.out.print("  "+attrName+"="+attrValue+"  ");
		}
		System.out.println(" >");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("< /" + qName + " >");
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		System.out.print(str);
	}

	public static void main(String[] args) {
		new SAXTest();
	}
}

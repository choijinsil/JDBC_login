package com.siri.dom;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest2 extends DefaultHandler { // 내가 원하는 것만 핸들러 사용가능하다.
	// sax: 이벤트 기반의 파서
	// SAXParseException <-- XML문서에 관련된 에러(Well - formed하지 못한 문서)
	// ( valid하지 못한 문서)
	SAXParserFactory factory;
	SAXParser parser;
	// 임시변수
	String titleStr;

	String searchAuthor;

	// 상태
	boolean titleState;
	boolean authorState;

	public SAXTest2() {
		try {
			factory = SAXParserFactory.newInstance(); // getInstance()와 같이 공유메모리를 사용하고 있다.
			parser = factory.newSAXParser();
			// books.xml의 모든 책 제목을 화면에 출력하세요.
			searchAuthor = "홍길동";
			parser.parse("xml/book/books.xml", this);

			
			// 미션2. # 도서목록(저자: 홍길동)

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("#도서목록( 저자: " + searchAuthor + " )");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("title")) {
			titleState = true;

		} // title 시작태그를 만났다면

		if (qName.equals("author")) {
			authorState = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("title")) {
			titleState = false;

			if (qName.equals("author")) {
				authorState = false;
			}
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		if (titleState) {
			titleStr = str;
		}

		if (authorState && str.equals(searchAuthor)) { // 저자의 이름을 읽었을때
			System.out.println(titleStr);
		}
	}

	public static void main(String[] args) {
		new SAXTest2();
	}
}

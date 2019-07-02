package com.siri.dom;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest3 extends DefaultHandler { // 내가 원하는 것만 핸들러 사용가능하다.
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

	public SAXTest3() {
		try {
			factory = SAXParserFactory.newInstance(); // getInstance()와 같이 공유메모리를 사용하고 있다.
			factory.setValidating(true); // 유효성 검사를 하겠다.
			// valid 체크를 하겟다.
			
			parser = factory.newSAXParser();
			// books.xml의 모든 책 제목을 화면에 출력하세요.
			searchAuthor = "홍길동";
			parser.parse("xml/book/books3.xml", this);
			System.out.println("성공");
			
			// 미션2. # 도서목록(저자: 홍길동)

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
		public void error(SAXParseException e) throws SAXException {
		// DTD 또는 XML Schema 문서가 있는 XML문서에 대한 Valid 하지 못했을 대
		// (유효하지 못한 XML문서 발견시) 호출
		System.out.println("error" +"문서 내용이 DTD또는 XML스키마와 일치하지 않음.");
		System.out.println(e.getMessage());
		
		}
	
	@Override
		public void fatalError(SAXParseException e) throws SAXException {
		// XML Spec( XML문법 )에 일치하지 않는 문서. well-formed 하지 못한 XML문서 발견시
		System.out.println("failError ---> XML문법에 어긋남." );
		System.out.println(e.getMessage());
		}

	
	public static void main(String[] args) {
		new SAXTest3();
	}
}

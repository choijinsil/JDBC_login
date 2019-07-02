package com.siri.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class DomTest {
	DocumentBuilderFactory factory; // 공장
	DocumentBuilder builder; // 일꾼
	// ------------Dom은 표준, DOM프로그램 시작 --------
	Document doc; // org.w3c.dom
	// DOM ( Document Object Model )
	// text--> 메모리 데이터로 변환해서 프로그래밍

	public DomTest() {
		try {
			factory = DocumentBuilderFactory.newInstance(); // 공장 하나 지었다.
			builder = factory.newDocumentBuilder(); // 아직 DOM파서와 XML은 별개이다.

			// 재료(XML문서) 전달
			doc = builder.parse("xml/test.xml");
			System.out.println("DOM파씽 성공 ^^");
			// 문서상의 루트엘리먼트 얻어오기
			// Document doc: 1.위치(rootElement 바로위) 2. 생성(create)
			Element root= doc.getDocumentElement(); // 문서를 대표하는 엘리먼트
			Node n=root.getFirstChild();
			
			System.out.println("노드타입"+n.getNodeType());
			// Node안에는 1~12까지 명시한 static field final을 정의하고 있다.
			System.out.println("엘리먼트 매핑 수 = "+Node.ELEMENT_NODE); //1
			System.out.println("텍스트 매핑 수 = "+Node.TEXT_NODE); //3
			System.out.println("주석 매핑 수 = "+Node.COMMENT_NODE); //8
			
			System.out.println("노드 이름 = "+n.getNodeName());
			System.out.println("노드 값 = "+n.getNodeValue()); 
			
		} catch (Exception e) {
			System.out.println("파일을 못찾았어요.");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new DomTest();
	}

}

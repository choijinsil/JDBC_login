package com.siri.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMTest3 {
	DocumentBuilderFactory factory;
	DocumentBuilder builder; // DOM파서!
	// DOM표준, 프로그램 시작
	Document doc;

	// 미션. DOMTest2(XML문서를 읽어서 화면출력)
	// DOMTest3 (Xml문서를 일겅서 파일 출력) books2.xml
	// 내일은 노드 관련된 수업을 할것임
	// xml파일 복사 FileWriter
	FileWriter out;

	public DOMTest3() {

		try {
			out = new FileWriter("E:/Siris/workspaces/eclipse-workspace/XMLTest/xml/book/books2.xml");

			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();

			doc = builder.parse("xml/book/books.xml");

			documentTravel(doc);// 시작을 Document( 문서가 포함한 내용 여행)

			System.out.println("메모리XML을 파일복사 성공!!^^*");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void documentTravel(Node n) throws IOException {

		// 매개변수 Node n: Element, Text, Comment, Document,...

		// 노드 구분(1~12) 1:Element 3:Text 8:Comment

		switch (n.getNodeType()) {// 노드 --> 너 누구니? 1~12
		case Node.ELEMENT_NODE: {// 1:{//n: 엘리먼트노드

			NodeList list = n.getChildNodes();// 자식노드구하기 (Element)
			// NodeList: 속성을 제외한 XML문서 구성요소들을 저장
			// ---> getLength(), item(index)

			String tagName = n.getNodeName();// 태그명
			out.write("<" + tagName + ">");

			// for문 ==> 자식요소 호출
			for (int i = 0; i < list.getLength(); i++) {
				// NodeList의 인덱스 표현

				documentTravel(list.item(i));
			}
			// out.write(tagName);

			out.write("</" + tagName + ">");

			NamedNodeMap attrs = n.getAttributes();

			for (int i = 0; i < attrs.getLength(); i++) {
				// Element(태그)가 갖는 속성
//				out.writeln("속성구하기: " + attrs.item(i));
				out.write("속성구하기: " + attrs.item(i));
			}

			break;
		}
		case Node.TEXT_NODE: {
			out.write(n.getNodeValue());
			// 텍스트 내용
			break;
		}
		case Node.COMMENT_NODE: {
			out.write(n.getNodeValue());
			break;
		}
		case Node.DOCUMENT_NODE: {
			
			out.write("<?xml version='1.0' encoding='utf-8'?>\r\n");
			NodeList list = n.getChildNodes();
			
			for (int i = 0; i < list.getLength(); i++) {
				// NodeList의 인덱스 표현
				documentTravel(list.item(i));
			}
		}

		}// switch

	}// documentTravel

	public void documentTravel2(Node n2) {
		if (n2.getNodeType() == 8) {

			n2.getPreviousSibling();
//			out.writeln("8이 있긴잇다.");
			System.out.println("형제여" + n2.getNodeValue());
		}
	}

	public static void main(String[] args) {
		new DOMTest3();
	}

}

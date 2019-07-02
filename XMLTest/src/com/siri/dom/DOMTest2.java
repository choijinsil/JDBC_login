package com.siri.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMTest2 {
	DocumentBuilderFactory factory;
	DocumentBuilder builder; // DOM파서!
	// DOM표준, 프로그램 시작
	Document doc;

	public DOMTest2() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse("xml/book/books.xml");
			// 메모리 사용, 메모리 읽기, 메모리 조작
			// 미션: 전체문서(booksxml) 여행하기
			Element root = doc.getDocumentElement();
			Node root2 = root.getNextSibling();
			documentTravel(root);
			documentTravel2(root2);

		} catch (Exception e) {
			e.printStackTrace();
		} /// 구성요소를 메모리 적재

	}

	public void documentTravel(Node n) {
		// 매개변수 Node n: Element, Text, Comment, Document,...

		// 노드 구분(1~12) 1:Element 3:Text 8:Comment

		switch (n.getNodeType()) {// 노드 --> 너 누구니? 1~12
		case Node.ELEMENT_NODE: {// 1:{//n: 엘리먼트노드

			NodeList list = n.getChildNodes();// 자식노드구하기 (Element)
			// NodeList: 속성을 제외한 XML문서 구성요소들을 저장
			// ---> getLength(), item(index)

			String tagName = n.getNodeName();// 태그명
			System.out.print("<" + tagName + ">");

			// for문 ==> 자식요소 호출
			for (int i = 0; i < list.getLength(); i++) {
				// NodeList의 인덱스 표현
				documentTravel(list.item(i));
			}
			System.out.print("</" + tagName + ">");

			NamedNodeMap attrs = n.getAttributes();

			for (int i = 0; i < attrs.getLength(); i++) {
				// Element(태그)가 갖는 속성
				System.out.println("속성구하기: " + attrs.item(i));
			}

			break;
		}
		case Node.TEXT_NODE: {
			System.out.print(n.getNodeValue());
			// 텍스트 내용
			break;
		}
		case Node.COMMENT_NODE: {
			System.out.println(n.getNodeValue());
		}

		}// switch

	}// documentTravel

	public void documentTravel2(Node n2) {
		if (n2.getNodeType() == 8) {

			n2.getPreviousSibling();
//			System.out.println("8이 있긴잇다.");
			System.out.println("형제여" + n2.getNodeValue());
		}
	}

	public static void main(String[] args) {
		new DOMTest2();
	}

}

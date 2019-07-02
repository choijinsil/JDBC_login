package com.siri.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMTest4 {
   DocumentBuilderFactory factory;//����
   DocumentBuilder builder;//�ϲ�
   
   //----------------- DOMǥ��, DOM���α׷� ���� --------------
   Document doc;//����(�޸�)��ü
 
   
   public DOMTest4() {
	 try {  
	  factory = DocumentBuilderFactory.newInstance();
	  factory.setIgnoringElementContentWhitespace(true);
	  //���鹫�� ����
	  
	  builder = factory.newDocumentBuilder();
	  //builder: DOM�ļ�!!
	  
	  //���(XML����)����
	  //doc = builder.parse(String uri);
	  doc = builder.parse("xml/0628/books3.xml");
	  //text XML�м�  ---> �޸� �ε�
	  System.out.println("DOM�Ľ�(�޸�����) ����~!!^^*");
	  //----------DOM Programming����------------------
/*
 �̼�) books3.xml�� DOM Parsing�Ͽ� ù��° book�� title�� ȭ�鿡 ����Ͻÿ�.
      ===> ���ȭ��: JavaProgramming
      ===> ��, �θ�          �ڽ�        ���� ���踦 ���� ������ �ذ��Ͻÿ�.
              parent  child   sibling
              
doc ---> books ---> book ---> title ---> TextNode("JavaProgramming")            

 */   
	 
        Element root = doc.getDocumentElement(); //��Ʈ������Ʈ ���
        Node firstBook = root.getFirstChild();//��Ʈ�� ù��° �ڽĳ��(book)
	    Node firstTitle = firstBook.getFirstChild();//ù��°book�� ù°�ڽĳ��(title)
	    Node textNode = firstTitle.getFirstChild();//ù��°title�� ù°�ڽĳ��(TextNode)
	    String titleName = textNode.getNodeValue();//textNode�� ���� ���밪 ���
	   
	    /*
	    String titleName = doc.getDocumentElement()
	    		              .getFirstChild()  //����
	                          .getNextSibling()  //<book>
	                          .getFirstChild()  //����
	                          .getNextSibling()  //<title>
	                          .getFirstChild()  //TextNode
	                          .getNodeValue();   //�ؽ�Ʈ ��
	    */                      
	    System.out.println("å����>>>"+ titleName);
	    
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	  
   }//������
	
   public static void main(String[] args) {
	  new DOMTest4();
   }
}

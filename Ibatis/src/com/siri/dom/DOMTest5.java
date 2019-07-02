package com.siri.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMTest5 {
   DocumentBuilderFactory factory;//����
   DocumentBuilder builder;//�ϲ�
   
   //----------------- DOMǥ��, DOM���α׷� ���� --------------
   Document doc;//����(�޸�)��ü
 
   
   public DOMTest5() {
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
  �̼ǡ�: books.xml�� DOM Parsing�Ͽ� ��� å������ ȭ�鿡 ����Ͻÿ�.
  
  ���ȭ��)
    #�������
      JavaProgramming
      OracleSQL
      JavaScript
      XMLBible
 */
	  System.out.println("#�������");
	  NodeList titleList = doc.getElementsByTagName("title");
	  //list [<title><title><title><title>]
	  
	  //XML������ <title>������Ʈ ������ŭ �ݺ�
	  for(int i=0; i<titleList.getLength(); i++) {
		/*  
		  Node titleNode = titleList.item(i);
		  //titleNod ==> <title>XMLBible</title>
		  titleNode.getTextContent();
		  //            XMLBible
		 */
  System.out.println(" "+titleList.item(i).getTextContent());
	  }
/*
�̼�2) books.xml�� DOM Parsing�Ͽ� ��� å������ ȭ�鿡 ���.

   ���ȭ��)
   #��ü ��������
    JavaProgramming(ȫ�浿) : 28000��
    OracleSQL(�����) : 32000��
    JavaScript(���ֿ�) : 29000��
    XMLBible(ȫ�浿) : 33000��
 */
	  NodeList  authorList = doc.getElementsByTagName("author");
	  NodeList  priceList = doc.getElementsByTagName("price");
	  
	  System.out.println("===================================");
	  System.out.println("#��ü ��������");
	  for(int i=0; i<titleList.getLength(); i++) {//������ ����ŭ �ݺ�
		 System.out.println(
			" "+titleList.item(i).getTextContent()+
			"("+authorList.item(i).getTextContent()+ ") : "+
			priceList.item(i).getTextContent()+"��"); 
	  }
	  /*
	  �̼�3) books.xml�� DOM Parsing�Ͽ� ���� 'ȫ�浿'��  å������ ȭ�鿡 ���.

	     ���ȭ��)
	     #��������(����:ȫ�浿)
	      JavaProgramming[28000��]
	      XMLBible[33000��]
	   */	    
	  System.out.println("===================================");
	  String searchAuthor="ȫ�浿";
	  System.out.println("#��������(����:"+searchAuthor+")");
	  for(int i=0; i<titleList.getLength(); i++) {//������ ����ŭ �ݺ�
		if(authorList.item(i).getTextContent().equals(searchAuthor)) {  
		  System.out.println(
				  " "+titleList.item(i).getTextContent()+
				 "["+priceList.item(i).getTextContent()+"��]");
		}
	  }//for

	  
	  
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	  
   }//������
	
   public static void main(String[] args) {
	  new DOMTest5();
   }
}



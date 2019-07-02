package com.siri.dom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TemperatureTest {
  /*
   �̼�) ȭ�鿡 �Ʒ��� ���� ����Ͻÿ�.
   
  ���� ���ʱ� ����1��: 15�� ����� 26.0�� �Դϴ�.
 ===> ���� �ְ���  27.0��, ������� 21.0�� �Դϴ�.
   
   http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1165051000
   
   
  */
	DocumentBuilderFactory factory;
	DocumentBuilder builder;//�ڹ��� DOM�ļ�
	
	Document doc;//W3C ǥ��
	
	public TemperatureTest() {
	try {	
	  factory = DocumentBuilderFactory.newInstance();
	  builder = factory.newDocumentBuilder();
	  
	  //doc=builder.parse(String uri);//uri==> "xml/0628/books.xml"
	  //uri ==> "http://www.kma.go.kr/temp.xml"
	  doc=builder.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1165051000");
	  //---------------DOM ���α׷��� ����(�޸��� �����Ϳ� ����)-----------------
	 /*
	   <hour>15</hour>       ==> �ð�����(3�ð� ����: 12��  15��  18��)
       <day>1</day>          ==> ��¥: 0(����)   1(����)   2(��)  
       <temp>26.0</temp>     ==> Ư���ð��� �µ�
       <tmx>27.0</tmx>       ==> �׳��� �ְ�µ�
       <tmn>21.0</tmn>       ==> �׳��� �����µ�
	  */
	  
	  NodeList hourList = doc.getElementsByTagName("hour");
	  NodeList dayList = doc.getElementsByTagName("day");
	  NodeList tempList = doc.getElementsByTagName("temp");
	  NodeList maxT = doc.getElementsByTagName("tmx");
	  NodeList minT = doc.getElementsByTagName("tmn");
	  
	  String searchHour ="15";
	  
	  //System.out.println("��¥�� ����="+ dayList.getLength());
	  for(int i=0; i<dayList.getLength(); i++) {//��¥�� ��ŭ �ݺ�
		 String dayStr = dayList.item(i).getTextContent();//"0" "1" "2"
 		 String hourStr =  hourList.item(i).getTextContent();
		 if(dayStr.equals("1") && hourStr.equals(searchHour)) {//����, 15��
			 
			System.out.println("���� ���ʱ� ����1��: "+searchHour
			+"�� ����� "+tempList.item(i).getTextContent()+"�� �Դϴ�.");
			
			System.out.println("===> ���� �ְ���  "+
			  maxT.item(i).getTextContent() +"��, ������� "+
			  minT.item(i).getTextContent() +"�� �Դϴ�.");
			
			
			 break;
		 }
	  }//for
	  
   	  }catch(Exception e) {
		  e.printStackTrace();
	  }
	}//������
	
    public static void main(String[] args) {
	    new TemperatureTest();   	
	}
	
}

package com.siri.dom;

import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DOMTest6 {
   //Ư�� XML������ ���� �ʰ�(�ε����� �ʰ�) ���ο� XML������ �޸𸮿� ����(DOMƮ�� ����)
   //�޸�  ---����---> ��������.
	
   DocumentBuilderFactory factory;//����
   DocumentBuilder builder;//�ϲ�
   FileWriter out;
   
   //----------------- DOMǥ��, DOM���α׷� ���� --------------
   Document doc;//����(�޸�)��ü
 
   
   public DOMTest6() {
	 try {  
	   out = new FileWriter("xml/0628/root.xml");
	   //FileWriter�� FileOutputStream������: ��õ� ������ ����!!
		 
	  factory = DocumentBuilderFactory.newInstance();
	  builder = factory.newDocumentBuilder();
	  //builder: DOM�ļ�!!

	  doc = builder.newDocument();
	  //text XML�м�  ---> �޸� �ε�
	  System.out.println("DOM�Ľ�(�޸�����) ����~!!^^*");
	  //----------DOM Programming����------------------

	  //doc: Document��ü ----> �������� �޼ҵ� (������Ʈ, �ؽ�Ʈ, �ּ�)
	  
	  //doc.createElement(String tagName);
	  Element root = doc.createElement("ROOT");//<ROOT></ROOT>
	  Element a = doc.createElement("A");//<A></A>
	  Element b = doc.createElement("B");//<B></B>
	  Element c = doc.createElement("C");//<C></C>
	  Element d = doc.createElement("D");//<D></D>
	  Element e = doc.createElement("E");//<E></E>
	  
	  //doc.createTextNode(String data);
	  Text t1 = doc.createTextNode("Hello");
	  Text t2 = doc.createTextNode("GoodBye");
	  Text t3 = doc.createTextNode("GoodDay");
	  
	  //1.�߰�(�ڷ�) : �θ���.appendChild(�ڽĳ��);
	  doc.appendChild(root);// <ROOT></ROOT>
	  
	  a.appendChild(c);//<A><C></C></A>
	    c.appendChild(t1);//<A><C>Hello</C></A>
	  b.appendChild(t2);//<B>GoodBye</B>  
	  d.appendChild(t3);//<D>GoodDay</D>  
	    
	  root.appendChild(a);//<ROOT><A><C>Hello</C></A></ROOT>  
	  root.appendChild(b);//<ROOT><A><C>Hello</C></A><B>GoodBye</B></ROOT>  
	  
	  //2.�߰�(�����ֱ�) : �θ���.insertBefore(���ο���,�������);
	  root.insertBefore(d, b);
	/*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <D>GoodDay</D>   
	        <B>GoodBye</B>
	    </ROOT>
	 */
	  
	  //3.����: �θ���.removeChild(������ ���);
	   //root.removeChild(b);
	  /*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <D>GoodDay</D> 
	    </ROOT>
	   */
	  
	  //����, b�±׸� �����ϰ� ������ �θ� �� ���(�θ� �ٲ�� ���)?
	    b.getParentNode().removeChild(b);
	    /*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <D>GoodDay</D>
	    </ROOT>
	     */
	  
	  //4.����(��ü): �θ���.replaceChild(�����,�������);
	  //d�±�  ----> e�±� ��ü!!
	    root.replaceChild(e, d);
	    /*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <E></E>
	    </ROOT>
	     */
	  
	  //5.����: �����ҳ��.cloneNode(����deep����);
	   //Node aClone =  a.cloneNode(true); 
	             //a������Ʈ�� �ڽı��� ����  <A><C>Hello</C></A> 
	   Node aClone =  a.cloneNode(false);//a������Ʈ�� ����   <A></A>  
	   
	   root.appendChild(aClone);
	   /*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <E></E>
	        <A></A>
	    </ROOT>
	    */
	   
	   //6.��Ÿ
	   String data="��&������3<4";
	   Text newTxt = doc.createTextNode(data);
	   CDATASection cdata = doc.createCDATASection(data);
	   Comment comm = doc.createComment("���ּ�");
	   
	   //e������Ʈ�� text�߰�
	   //e.appendChild(newTxt);
	   e.appendChild(cdata);
	   /*
	    <ROOT>
	        <A><C>Hello</C></A> 
	        <E><![CDATA[��&������3<4]]></E>
	        <A></A>
	    </ROOT>
	    */
	   
	   e.appendChild(comm);
	    
	   documentTravel(doc);
	   System.out.println("DOMƮ��(�޸�)�� ���Ϸ� �ű�� ����!!");
	  
	 }catch(Exception e) {
		 e.printStackTrace();
	 }finally {
		 try {
			if(out != null)out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	  
   }//������
   
   public void documentTravel(Node n) throws IOException{
		 
		 switch(n.getNodeType()) {//��� --> ���� ��ü��? 1~12
		    case Node.ELEMENT_NODE:{//1:{//n: ������Ʈ��� Element!! 
		    	
		    	NodeList list= n.getChildNodes();//�ڽĳ�屸�ϱ� (Element)
		   	    //NodeList: �Ӽ��� ������ XML���� ������ҵ��� ����
		   	    // ---> getLength(),  item(index)
		    	
		   	    String tagName = n.getNodeName();//�±׸�
		   	    out.write("<"+tagName); //�����±� ���
		   	    
		   	    //������Ʈ(�±�)��  ���� �Ӽ��� ���ؼ� ���.
		   	    NamedNodeMap attrs = n.getAttributes();
		   	    //NamedNodeMap : �Ӽ�(��)�� ��� Ŭ����
		   	    // ---> getLength(), item(index)
		   	    for(int i=0; i<attrs.getLength(); i++) {//�Ӽ� �ε��� ǥ��
		   	       Node attr = attrs.item(i);//�Ӽ����
		   	       String attrName = attr.getNodeName();
		   	       String attrVal = attr.getNodeValue();
		   	       out.write(" "+attrName +"='"+ attrVal+"'");
		   	    }
		   	    
		   	    
		   	    out.write(">");
		   	    
		   	    
		   	    //for�� ==> �ڽĿ�� ȣ��
		   	    for(int i=0; i<list.getLength(); i++) {
		   		  //NodeList�� �ε��� ǥ��		 
		   		  documentTravel(list.item(i));
		   	    }
		   	    
		   	    out.write("</"+tagName+">");//���±� ���
		   	    break;
		    }
		    case Node.TEXT_NODE:{//case 3:
		    	out.write(n.getNodeValue());
		    	                     //�ؽ�Ʈ ����
		    	break;
		    }
		    case Node.COMMENT_NODE:{//case 8:
		    	out.write("<!--"+ n.getNodeValue()+"-->");
		    	break;
		    }
		    case Node.CDATA_SECTION_NODE:{
		    	out.write("<![CDATA["+ n.getNodeValue()+"]]>");
		    	break;
		    }
		    case Node.DOCUMENT_NODE:{
		    	out.write("<?xml version='1.0' encoding='euc-kr'?>\r\n");
		    	
		    	//n: Document
		    	NodeList list= n.getChildNodes();//�ڽĳ�屸�ϱ� (Element)
		   	     //list [books, <!--���ּ�--> ] 
		    	
		   	    //for�� ==> �ڽĿ�� ȣ��
		   	    for(int i=0; i<list.getLength(); i++) {
		   		  //NodeList�� �ε��� ǥ��		 
		   		  documentTravel(list.item(i));
		   	    }
		    }
		 }//switch
		   
	}//documentTravel
	
   public static void main(String[] args) {
	  new DOMTest6();
   }
}



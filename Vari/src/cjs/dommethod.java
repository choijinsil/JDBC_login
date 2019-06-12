package cjs;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class dommethod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html="<html><head><title>Sample Title</title></head>"
				+"<body>"
				+"<p>Sample Content</p>"
				+"<p>Another p tag</p>"
				+"<div id='sampleDiv'><a href='www.google.com'>Google</a></div>"
				+"</body></html>";
		
		Document document = Jsoup.parse(html);
		System.out.println("==========<title>==========\n"+document.title());
		
		Elements paragraphs = document.getElementsByTag("p");
		for(Element paragraph : paragraphs) {
			System.out.println("==========<p>==========\n"+paragraph.text());
		}
		
		Element sampleDiv = document.getElementById("sampleDiv");
		System.out.println("==========<sampleDiv>==========\n"+sampleDiv.text());
		
		//id가 sampleDiv인 a태그에 있는 요소들을 links에 넣음.
		Elements links = sampleDiv.getElementsByTag("a");
		//각 요소를 link라고 함.
		for(Element link : links) {
			System.out.println("Href : "+link.attr("href"));//해당 요소에 있는 속성이 href인 것의 내용 출력
			System.out.println("Text : "+link.text());//해당 요소에 있는 내용물 출력
		}
	}

}

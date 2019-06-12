package cjs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class loadingurl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String html = "<div><p>Sample Content</p>";
		Document document = Jsoup.parseBodyFragment(html);//입력된 html을 파싱함
		Element body = document.body(); //document.getElementById("body")와 동일
		Elements paragraphs = body.getElementsByTag("p");//태그가 p인거 다 가져옴
		for(Element paragraph : paragraphs) { //각 p태그 안에 있는 내용물 출력
			System.out.println(paragraph.text());
		}
		String url="http://www.google.com"; //google.com 주소
		Document document1 = Jsoup.connect(url).get(); //주소에 가서 html 해석한다.
		Elements contents = document1.getElementsByClass("gb_O"); //뽑고자 하는 내용물의 부모 클래스를 찾는다.
		System.out.println(document1.title()); //내용 중에 태그가 title인 것 출력
		for(Element content : contents) {
			System.out.println(content.getElementsByTag("span").text()); //내용 중 태그가 span인 것 출력
		}
	}

}



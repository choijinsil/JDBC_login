package cjs;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import java.io.IOException;

import org.jsoup.nodes.*;

import org.jsoup.select.Elements;

public class navernews {

	public static void main(String[] args) throws IOException {

		String url = "http://news.naver.com/";

		Document document = Jsoup.connect(url).get(); // 네이버 뉴스 url 해석.

		// 이 시각 주요 뉴스의 헤드라인 뽑기

		Elements strong = document.getElementsByClass("newsnow_tx_inner"); // 뽑고자 하는 내용의 부모 class.

		for (Element st : strong) {

			System.out.println(st.getElementsByTag("strong").text());// 부모 클래스 안에 있는 strong 태그의 내용물

			// System.out.println(st.getElementsByTag("a").text());

		}

	}

}

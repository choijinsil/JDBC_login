package cjs;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

public class crawler {

	public static void main(String[] args) {


		String html = "<html><head><title>Sample Title</title></head>"

				+ "<body><p>Sample Content</p></body></html>";

		Document document = Jsoup.parse(html); // html이라는 문자열을 해석

		System.out.println("==========<head>==========\n" + document.head());// head 태그 포함해서 출력

		System.out.println("==========<body>==========\n" + document.body());// body 태그 포함해서 출력

		System.out.println("==========<title>==========\n" + document.title());// title 태그 안의 내용물 출력, 왜 title 태그는 안나오지?

		System.out.println("==========<body>==========\n" + document.getElementsByTag("body"));// body태그를 포함한 모든 태그와 내용물
																								// 출력

		Elements paragraphs = document.getElementsByTag("p");

		for (Element paragraph : paragraphs) {

			System.out.println("==========<p>==========\n" + paragraph.text());// 태그를 제외한 태그 안의 내용물만 출력

		}

	}

}

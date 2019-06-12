package cjs;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;

import java.io.IOException;



public class address_price2 {



	public static void main(String[] args)throws IOException {

		//주택의 주소와 가격 뽑기

		String url="https://www.zillow.com/homes/for_sale/Denver-CO/pmf,pf_pt/118576962_zpid/11093_rid/globalrelevanceex_sort/39.976331,-104.6101,39.554089,-105.104485_rect/10_zm/";

		Document document = Jsoup.connect(url).get();//url 해석하기

		Elements parents = document.getElementsByClass("zsg-photo-card-spec");//원하는 내용의 부모 클래스

		for(Element paragraph: parents) { //부모 클래스 안에 있는 address, price 클래스 출력

			System.out.println(paragraph.getElementsByClass("zsg-photo-card-address").text()+ ' '+paragraph.getElementsByClass("zsg-photo-card-price").text());

		}		

	}	

}

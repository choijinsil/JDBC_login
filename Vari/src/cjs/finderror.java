package cjs;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class finderror {
   public static void main(String[] args) throws IOException{
      
      String url = "https://www.zillow.com/homes/for_sale/Denver-CO/pmf,pf_pt/118576962_zpid/11093_rid/globalrelevanceex_sort/39.976331,-104.6101,39.554089,-105.104485_rect/10_zm/";
      //String url = "https://www.zillow.com/homes/for_sale/pmf,pf_pt/globalrelevanceex_sort/39.78473,-104.864244,39.701045,-104.974108_rect/12_zm/";

      Document document = Jsoup.connect(url).get(); //url(uniform resource locator)에 가서 파싱한 내용을 document에.

      Elements addr = document.getElementsByClass("zsg-photo-card-address");//파싱 내용 중 class가 zsg-photo-card-address인 것을 addr에.
      Elements price = document.getElementsByClass("zsg-photo-card-price");

      for(int i =0; i < price.size(); i++){ //찾아낸 요소의 갯수만큼 반복
         System.out.println(addr.get(i).text()+":"+price.get(i).text());//각 클래스의 내용물 출력
      }
   }
}

package com.example.demo.entity;

import com.google.common.collect.Lists;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * JSOUP爬虫
 *
 * */

public class JsoupUtil {

    /**  爬取京东数据  */
    public static List<ProductVo> getProDuctListByJD(String keyword){
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        List<ProductVo> list = Lists.newArrayList();
        try {
            Connection connect = Jsoup.connect(url);
            connect.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
            Document document = connect.get();
            Element element = document.getElementById("J_goodsList");

            //获取所有的li元素
            Elements elements = element.getElementsByTag("li");

            for (Element el :elements){

                String img = el.getElementsByTag("img").attr("data-lazy-img");
                String price = el.getElementsByClass("p-price").eq(0).text();
                String title = el.getElementsByClass("p-name").eq(0).text();
                String sku = el.attr("data-sku");
                list.add(new ProductVo().setImg(img)
                        .setPrice(price)
                        .setTitle(title)
                        .setSku(sku));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

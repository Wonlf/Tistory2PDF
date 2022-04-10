package com.company;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.*;


public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String URL;
        URL = "https://wonlf.tistory.com/entry/codeengn-basic-L11-%ED%92%80%EC%9D%B4";
        //System.out.print("PDF로 만들 Tistory URL을 입력하세요 : ");
        URL = scan.next();

        StringBuilder body = new StringBuilder();
        Connection conn = Jsoup.connect(URL);
        String filePath = "./tistory.html";

        try {
            Document document = conn.get();
            Elements cssElement = document.getElementsByTag("head");


            Element imageUrlElements = document.getElementById("container");

            Elements sidebarElement =  imageUrlElements.select("#sidebar"); //사이드바
            Elements rmElement1 =  imageUrlElements.select("#lightbox");
            Elements rmElement2 =  imageUrlElements.select("#lightboxOverlay");
            Elements rmElement3 =  imageUrlElements.select(".entry-tag"); //블로그 글 태그
            Elements rmElement4 =  imageUrlElements.select(".container_postbtn"); //태그
            Elements rmElement5 =  imageUrlElements.select(".another_category"); //다른글
            //Elements rmElement6 =  imageUrlElements.select(".sns-go"); //sns 아래를 띄워줘서 굳이 지울 필요 없을 듯
            Elements rmElement7 =  imageUrlElements.select(".area_related_wrap"); //관련글
            Elements rmElement8 =  imageUrlElements.select(".post-reply"); //댓글
            Elements rmElement9 =  imageUrlElements.select("#paging"); //페이징
            Elements rmElement10 =  cssElement.select("script");
            sidebarElement.remove();
            rmElement1.remove();
            rmElement2.remove();
            rmElement3.remove();
            rmElement4.remove();
            rmElement5.remove();
            //rmElement6.remove();
            rmElement7.remove();
            rmElement8.remove();
            rmElement9.remove();
            rmElement10.remove();



            FileWriter fileWriter = new FileWriter(filePath); //2번째 인자로 true 넣으면 append옵션 활성화

            imageUrlElements.prepend("<link rel=\"stylesheet\" href=\"C:\\Users\\zzoccom\\Desktop\\Tistory2PDF\\content.css\">");
            imageUrlElements.prepend("<link rel=\"stylesheet\" href=\"C:\\Users\\zzoccom\\Desktop\\Tistory2PDF\\main.css\">");

            String html = imageUrlElements.toString();


            fileWriter.write(html);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
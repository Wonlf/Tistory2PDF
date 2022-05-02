package com.company;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void parsing(String url_path, String fn){
        Connection conn = Jsoup.connect(url_path);
        String filePath = "./tistory.html";

        //�񵿱� ó��?
        try {
            Document document = conn.get();
            Elements cssElement = document.getElementsByTag("head");


            Element imageUrlElements = document.getElementById("container");

            Elements sidebarElement =  imageUrlElements.select("#sidebar"); //���̵��
            Elements rmElement1 =  imageUrlElements.select("#lightbox");
            Elements rmElement2 =  imageUrlElements.select("#lightboxOverlay");
            Elements rmElement3 =  imageUrlElements.select(".entry-tag"); //��α� �� �±�
            Elements rmElement4 =  imageUrlElements.select(".container_postbtn"); //�±�
            Elements rmElement5 =  imageUrlElements.select(".another_category"); //�ٸ���
            //Elements rmElement6 =  imageUrlElements.select(".sns-go"); //sns �Ʒ��� ����༭ ���� ���� �ʿ� ���� ��
            Elements rmElement7 =  imageUrlElements.select(".area_related_wrap"); //���ñ�
            Elements rmElement8 =  imageUrlElements.select(".post-reply"); //���
            Elements rmElement9 =  imageUrlElements.select("#paging"); //����¡
            Elements rmElement10 =  cssElement.select("script");
            Elements rmElement11 =  imageUrlElements.select(".revenue_unit_wrap ");
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
            rmElement11.remove();

            String name = "������ ����Ǯ��";

            FileWriter fileWriter = new FileWriter(filePath); //2��° ���ڷ� true ������ append�ɼ� Ȱ��ȭ
            imageUrlElements.prepend("<link rel=\"stylesheet\" href=\"./content.css\">");
            imageUrlElements.prepend("<link rel=\"stylesheet\" href=\"./main.css\">");

            Element link = imageUrlElements.select(".p-category").first();
            link.attr("href", "#");
            link.text(name);


            String html = imageUrlElements.toString();

            fileWriter.write(html);
            fileWriter.close();



            String inputpath = "C:\\Users\\zzoccom\\Desktop\\Tistory2PDF\\tistory.html"; //������ �����η� �ۼ��ؾ� Ŀ�ǵ尡 ����
            String outputpath = "C:\\Users\\zzoccom\\Desktop\\Tistory2PDF\\report\\"+ fn +".pdf";

            System.out.println(url_path);
            System.out.println(outputpath);

            String main = "Application\\chrome.exe --enable-logging --headless --disable-gpu --print-to-pdf-no-header --print-to-pdf=" + outputpath + " " + inputpath;


            Process proc = Runtime.getRuntime().exec(main);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);
//        String URL;
//        URL = "https://wonlf.tistory.com/entry/codeengn-basic-L14-%ED%92%80%EC%9D%B4";
//        System.out.print("PDF�� ���� Tistory URL�� �Է��ϼ��� : ");
//        URL = scan.next();

        ArrayList<String> attr_href_string = new ArrayList<String>();
        ArrayList<String> title_string = new ArrayList<String>();


        for (int i = 1; ; i++) {
            String url_path = "https://wonlf.tistory.com/category/%EB%A6%AC%EB%B2%84%EC%8B%B1%20%EB%AC%B8%EC%A0%9C%20%ED%92%80%EC%9D%B4/CodeEngn.com_Basic%20%28Clear%29?page=" + i;
            //only codeengn
            Connection conn = Jsoup.connect(url_path);
            try {
                Document document = conn.get();
                Elements Href = document.select(".post_link");
                Elements Title = document.select(".post_title");
                for(Element e : Href){
                    String a = e.attr("href");
                    attr_href_string.add("https://wonlf.tistory.com" + a);
                }

                for(Element e : Title){
                    String a = e.text();
                    title_string.add(a);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }


        for (int i = 0; i < attr_href_string.size(); i++) {
            parsing(attr_href_string.get(i), title_string.get(i).replaceAll(" ", "_"));
        }


    }
}
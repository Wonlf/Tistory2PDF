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
//    public static String execute(String cmd) {
//        try {
//            Process process = Runtime.getRuntime().exec("cmd /c " + cmd);
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(process.getInputStream()));
//            String line = null;
//            StringBuffer sb = new StringBuffer();
//            sb.append(cmd);
//            while ((line = reader.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
//            }
//
//            System.out.println(sb.toString());
//            return sb.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String URL;
        URL = "https://wonlf.tistory.com/entry/codeengn-basic-L14-%ED%92%80%EC%9D%B4";
//        System.out.print("PDF�� ���� Tistory URL�� �Է��ϼ��� : ");
//        URL = scan.next();

        StringBuilder body = new StringBuilder();
        Connection conn = Jsoup.connect(URL);
        String filePath = "./tistory.html";

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
            String outputpath = "C:\\Users\\zzoccom\\Desktop\\Tistory2PDF\\out.pdf";

            String main = "Application\\chrome.exe --enable-logging --headless --disable-gpu --print-to-pdf-no-header --print-to-pdf=" + outputpath + " " + inputpath;
            System.out.println(main);

            Process proc = Runtime.getRuntime().exec(main);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
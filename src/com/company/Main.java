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
        //URL = "https://wonlf.tistory.com/entry/codeengn-basic-L11-%ED%92%80%EC%9D%B4";
        System.out.print("PDF로 만들 Tistory URL을 입력하세요 : ");
        URL = scan.next();

        StringBuilder body = new StringBuilder();
        Connection conn = Jsoup.connect(URL);
        String filePath = "./tistory.html";

        try {
            Document document = conn.get();
            Elements imageUrlElements = document.getElementsByClass("e-content post-content");

            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(imageUrlElements.toString());

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
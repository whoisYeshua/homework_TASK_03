package TASK_03;
/*Напишите программу, которая с консоли считывает поисковый запрос, и выводит
        результат поиска по Википедии.*/


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TASK3_1 {
    public static void main(String[] args) {

        // 1. Считать запрос
        System.out.println("Введите запрос для поиска на Wikipedia:");
        Scanner scan = new Scanner(System.in);
        String phrase = encode(scan.nextLine());

        // 2. Запрос к серверу
        // 2.1 Генерация запроса
        // https://ru.wikipedia.org/w/api.php?action=query&list=search&format=json&utf8=&srlimit=1&srsearch= - тело запроса, где query - взаимодействие с MediaWiki, list=search - полнотекствовй поиск, format=json&utf8= - вывод в JSON-формате в кодировке UTF-8, srlimit=1 - выводится только первый результат, так дано в задание, srsearch - что будем искать
        String url = "https://ru.wikipedia.org/w/api.php?action=query&list=search&format=json&utf8=&srlimit=1&srsearch=" + phrase;


        String wikiurl = "https://ru.wikipedia.org/wiki/" + phrase;
        System.out.println(url);
        System.out.println(wikiurl);
    }

    // Кодирование запроса
    static String encode(String phrase) {
        return URLEncoder.encode(phrase, StandardCharsets.UTF_8);
    }

}



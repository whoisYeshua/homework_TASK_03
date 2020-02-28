package TASK_03;
/*Напишите программу, которая с консоли считывает поисковый запрос, и выводит
        результат поиска по Википедии.*/

import java.lang.reflect.Type;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;

import java.util.Map;
import java.util.Scanner;
import java.util.List;


import com.google.gson.*; // File -> Project Structure... -> Libraries -> From Maven...
import com.google.gson.reflect.TypeToken;

public class TASK3_1 {
    public static void main(String[] args) throws Exception {

        // 1. Считать запрос
        System.out.println("Введите запрос для поиска на Wikipedia:");
        Scanner scan = new Scanner(System.in);
        String phrase = encode(scan.nextLine());

        // 2. Запрос к серверу
        // 2.1 Генерация запроса
        // https://ru.wikipedia.org/w/api.php?action=query&list=search&format=json&utf8=&srlimit=1&srsearch= - тело запроса, где query - взаимодействие с MediaWiki, list=search - полнотекствовй поиск, format=json&utf8= - вывод в JSON-формате в кодировке UTF-8, srlimit=1 - выводится только первый результат, так дано в задание, srsearch - что будем искать
        String url = "https://ru.wikipedia.org/w/api.php?action=query&list=search&format=json&utf8=&srlimit=1&srsearch=" + phrase;

        // 2.2 Запрос к серверу
        String response = sendGet(url);

        // 2.3 Обработка запроса
        Search expose = ExposeGson.exposeGson(response);
        System.out.println(expose.title+"\n"+expose.snippet);





//        String wikiurl = "https://en.wikipedia.org/w/index.php?sort=relevance&search=" + phrase;
//        System.out.println(url);
//        System.out.println(wikiurl);
    }

    // Кодирование запроса
    private static String encode(String phrase) {
        return URLEncoder.encode(phrase, StandardCharsets.UTF_8);
    }

    private static String sendGet(String url) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        return response.body();
    }

}



package TASK_03;
/*Напишите программу, которая с консоли считывает поисковый запрос, и выводит
        результат поиска по Википедии.*/

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.charset.StandardCharsets;

import java.util.Scanner;

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

        // 2.3 Обработка ответа
        Search expose = ExposeGson.exposeGson(response);

        // 3 Форматирование и вывод ответа
        System.out.println(expose.title+"\n"+format(expose.snippet)+"...\n"+"Сслыка: https://ru.wikipedia.org/w/index.php?sort=relevance&search=" + phrase);

    }

    // Кодирование запроса
    private static String encode(String phrase) {
        return URLEncoder.encode(phrase, StandardCharsets.UTF_8);
    }

    // Запрос к серверу
    private static String sendGet(String url) throws Exception {

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET() // Можно и не указывать, тк идет по дефолту
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()); // Обработка тела ответа в виде строки

        //System.out.println(response.statusCode()); - статус-код

        return response.body(); // Возврат не всего ответа, а только содержимого body
    }

    // Форматируем содиржимое snippet, убирая html-теги
    static String format(String snippetString) {
        snippetString = snippetString.replace("<span class=\"searchmatch\">", "");
        snippetString = snippetString.replace("</span>", "");
        snippetString = snippetString.replace("&lt;", "<");
        snippetString = snippetString.replace("&gt;", ">");
        snippetString = snippetString.replace("&quot;", "\"");
        return snippetString;
    }

}



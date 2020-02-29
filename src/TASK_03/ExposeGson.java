package TASK_03;

import com.google.gson.*; // File -> Project Structure... -> Libraries -> From Maven...
import java.util.List;

public class ExposeGson {

    public static Search exposeGson(String jsonString) {

        Gson gson = new Gson();
        Wikires result = gson.fromJson(jsonString, Wikires.class); // Десериализуем полученный JSON в объект
        return result.query.search.get(0);
    }
}

// Создаем объекты на основании их json представления
class Wikires {
    public Query query;
}

class Query {
    public List<Search> search;
}

class Search {
    public int ns;
    public String title;
    public int pageid;
    public int size;
    public int wordcount;
    public String snippet;
    public String timestamp;

}
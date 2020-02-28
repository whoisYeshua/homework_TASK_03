package TASK_03;

import com.google.gson.*;
import java.util.List;

public class ExposeGson {

    public static Search exposeGson(String jsonString) {

        Gson gson = new Gson();
        Wikires result = gson.fromJson(jsonString, Wikires.class);
        System.out.println(result);

        return result.query.search.get(0);
    }
}

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
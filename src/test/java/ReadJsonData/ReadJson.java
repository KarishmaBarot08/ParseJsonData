package ReadJsonData;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadJson {

    public static JSONObject data;

    public static void main(String[] args) throws IOException, ParseException {
        ReadJson classobj = new ReadJson();
        JSONParser jp = new JSONParser();
        FileReader file = new FileReader(System.getProperty("user.dir") + "./Files/Data.json");
        Object obj = jp.parse(file);
        data = (JSONObject) obj;

        classobj.getData();

        JSONArray listArray = (JSONArray) data.get("list");
        for (int i = 0; i < listArray.size(); i++) {
            JSONObject list = (JSONObject) listArray.get(i);
            //***********fetching key-values*****************
            classobj.getMain(list);
            classobj.getWind(list);
            classobj.getCloud(list);
            classobj.getWeather(list);
            classobj.getRain(list);
            classobj.getDT(list);
        }
    }

    public void getData() {
        String msg = (String) data.get("message");
        String cod = (String) data.get("cod");
        long city_id = (Long) data.get("city_id");
        double calctime = (Double) data.get("calctime");
        long cnt = (Long) data.get("cnt");


        System.out.println("Message is: " + msg);
        System.out.println("cod is: " + cod);
        System.out.println("city_id is: " + city_id);
        System.out.println("calctime is: " + calctime);
        System.out.println("cnt is: " + cnt);
    }

    public void getMain(JSONObject list) {
        JSONObject main = (JSONObject) list.get("main");
        double temp = (Double) main.get("temp");
        double temp_min = (Double) main.get("temp_min");
        double temp_max = (Double) main.get("temp_max");
        String pressure = String.valueOf(main.get("pressure"));
        long humidity = (Long) main.get("humidity");

        System.out.println("***Printing data from main***");

        System.out.println("temp is:" + temp);
        System.out.println("temp_min is:" + temp_min);
        System.out.println("temp_max is:" + temp_max);
        System.out.println("pressure is:" + pressure);
        System.out.println("humidity is:" + humidity);
        if (main.containsKey("sea_level")) {
            double sea_level = (Double) main.get("sea_level");
            System.out.println("sea_level is:" + sea_level);
        }
        if (main.containsKey("grnd_level")) {
            double grnd_level = (Double) main.get("grnd_level");
            System.out.println("grnd_level is:" + grnd_level);
        }
    }

    public void getWind(JSONObject list) {
        JSONObject wind = (JSONObject) list.get("wind");
        double speed = (Double) wind.get("speed");
        String deg = String.valueOf(wind.get("deg"));

        System.out.println("***Printing data from wind***");
        System.out.println("speed is:" + speed);
        System.out.println("deg is:" + deg);
    }

    public void getCloud(JSONObject list) {
        JSONObject clouds = (JSONObject) list.get("clouds");
        long all = (long) clouds.get("all");

        System.out.println("***Printing data from clouds***");
        System.out.println("all is:" + all);
    }

    public void getWeather(JSONObject list) {
        JSONArray weatherList = (JSONArray) list.get("weather");


        for (int k = 0; k < weatherList.size(); k++) {
            JSONObject weatherData = (JSONObject) weatherList.get(k);
            long id = (Long) weatherData.get("id");
            String main_weather = (String) weatherData.get("main");
            String description = (String) weatherData.get("description");
            String icon = (String) weatherData.get("icon");

            System.out.println("***Printing data from weather***");
            System.out.println("id is:" + id);
            System.out.println("main_weather is:" + main_weather);
            System.out.println("description is:" + description);
            System.out.println("icon is:" + icon);

        }
    }

    public void getRain(JSONObject list) {
        if (list.containsKey("rain")) {
            JSONObject rain = (JSONObject) list.get("rain");
            double h = (Double) rain.get("3h");
            System.out.println("***Printing data from rain***");
            System.out.println("3h is:" + h);
        }
    }

    public void getDT(JSONObject list) {
        long dt = (Long) list.get("dt");
        System.out.println("***Printing data from dt***");
        System.out.println("dt is:" + dt);
    }
}

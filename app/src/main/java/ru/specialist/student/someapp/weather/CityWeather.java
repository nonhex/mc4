package ru.specialist.student.someapp.weather;

/**
 * Created by student on 21.07.2015.
 */
public class CityWeather {
    public Coord coord;
    public Weather[] weather;
    public String base;
    public Temperatures main;
    public int visibility, dt;
    public Wind wind;
    /*{
        "rain":{"1h":0.33},
        "clouds":{"all":75},
        "sys":{"type":1, "id":7323, "message":0.0284, "country":"RU", "sunrise":1437441310, "sunset":1437501336},
        "id":524901, "name":"Moscow", "cod":200
    }*/
}

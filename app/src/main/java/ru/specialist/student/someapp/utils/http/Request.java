package ru.specialist.student.someapp.utils.http;

import java.util.Map;

/**
 * Created by xema on 22.07.15.
 */
public class Request {
    public String url;
    public Method method;
    public Map<String, String> params;

    public Request(String url, Method method, Map<String, String> params) {
        this.url = url;
        this.method = method;
        this.params = params;
    }

    public Request(String url, Method method) {
        this.url = url;
        this.method = method;
    }

    public Request(String url) {
        this.url = url;
        this.method = Method.GET;
    }
}

package ru.specialist.student.someapp.utils.http;

import android.os.Handler;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import ru.specialist.student.someapp.Group;

/**
 * Created by xema on 22.07.15.
 */
public class HttpService {
    private static HttpService self = null;
    private Queue<Group<Request, OnSuccess>> queue = new PriorityQueue<>();
    private Queue<Group<String, OnSuccess>> ready_q = new PriorityQueue<>();
    private Handler handler;
    private boolean destroy;
    private Runnable ready_run;

    private HttpService() {
        destroy = false;
        handler = new Handler();
        ready_run = new Runnable() {
            @Override
            public void run() {
                if (!ready_q.isEmpty()) {
                    try {
                        Group<String, OnSuccess> g = ready_q.poll();
                        g.v.run(g.k);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!destroy)
                    handler.post(ready_run);
            }
        };
        handler.post(ready_run);
        new WorkerThread().start();
    }

    private class WorkerThread extends Thread {

        public WorkerThread() {
            setDaemon(true);
        }

        public void run() {
            while (true) {
                if (destroy)
                    break;
                if (queue.isEmpty())
                    continue;
                Group<Request, OnSuccess> g = queue.poll();
                try {
                    HttpResponse response = new DefaultHttpClient().execute(new HttpGet(g.k.url));
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            response.getEntity().getContent()));
                    String line, data = "";
                    while ((line = in.readLine()) != null)
                        data += line;
                    Log.d("MC4", "data.length: " + data.length());
                    ready_q.add(new Group<>(data, g.v));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static HttpService self() {
        if (self == null)
            self = new HttpService();
        return self;
    }

    public static void destroy() {
        self.destroy = true;
        self = null;
    }

    public void put(Request request, OnSuccess onSuccess) {
        queue.add(new Group<>(request, onSuccess));
    }
}

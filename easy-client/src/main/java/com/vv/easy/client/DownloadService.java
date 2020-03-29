package com.vv.easy.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadService {

    private HttpClient httpClient;

    public DownloadService(HttpClient httpClient) {
        this.httpClient = httpClient;

    }

    public void download(List<String> pathsList) {
        ExecutorService executorService = Executors.newFixedThreadPool( 3);
        for(String path : pathsList) {
            executorService.execute(new DownloadTask(path, this.httpClient));
        }
        executorService.shutdown();
    }
}

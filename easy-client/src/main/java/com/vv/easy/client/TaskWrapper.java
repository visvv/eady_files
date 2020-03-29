package com.vv.easy.client;

import org.apache.http.impl.client.CloseableHttpClient;

public class TaskWrapper extends Thread {
    private CloseableHttpClient httpClient;

    public TaskWrapper(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        super.run();
    }
}

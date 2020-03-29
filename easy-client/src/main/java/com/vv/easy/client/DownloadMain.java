package com.vv.easy.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.util.Arrays;
import java.util.List;

public class DownloadMain {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        DownloadService downloadService = new DownloadService(httpClient);
        List<String> paths = Arrays.asList("easy-files-0.0.1-SNAPSHOT.jar", "ReadMe.txt", "voltage-simple-api-java-05.20.0000-Linux-x86_64-64b-r240871.sh");
        downloadService.download(paths);
    }
}

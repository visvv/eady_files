package com.vv.easy.client;


import com.sun.corba.se.impl.logging.InterceptorsSystemException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;

import java.io.*;

public class DownloadTask implements Runnable {
    private String path;
    private HttpClient httpClient;

    public DownloadTask(String path, HttpClient httpClient) {
        this.path = path;
        this.httpClient = httpClient;
    }

    @Override
    public void run() {
        System.out.println(this.httpClient);
        System.out.println("Downloading..." + this.path);
        HttpGet get = new HttpGet("http://16.103.3.35:8091/download/" + this.path);
        try {
            String path = this.httpClient.execute(get, response -> {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode >= 200 && statusCode <= 300) {
                    try {
                        File file = new File(System.getProperty("user.home"), "temp_" + this.path);
                        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                        InputStream inputStream = response.getEntity().getContent();
                        IOUtils.copy(inputStream, outputStream);
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    return this.path;
                }
                throw new ClientProtocolException("ERROR : " + statusCode);
            });
            System.out.println("Completed" + path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}

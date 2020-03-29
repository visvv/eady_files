package com.vv;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class sample {
    public static void main(String[] args) throws IOException {
        try(CloseableHttpClient httpClient  = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8091/list");
            String responseString = httpClient.execute(httpGet, response -> {
                int status = response.getStatusLine().getStatusCode();
                if(status >= 200 && status <= 300) {
                    return  EntityUtils.toString(response.getEntity());
                }
                throw new ClientProtocolException("ERROR : " + status);
            });
            System.out.println(responseString);
        }

    }
}

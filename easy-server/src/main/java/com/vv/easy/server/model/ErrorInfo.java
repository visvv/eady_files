package com.vv.easy.server.model;

public class ErrorInfo {
    private final String url;
    private final String message;

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }
}

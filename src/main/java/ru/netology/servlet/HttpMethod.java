package ru.netology.servlet;

public enum HttpMethod {
    GET("GET"), POST("POST"), UPDATE("UPDATE"), DELETE("DELETE");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}

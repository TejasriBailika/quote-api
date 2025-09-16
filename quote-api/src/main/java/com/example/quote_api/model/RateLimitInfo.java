package com.example.quote_api.model;


public class RateLimitInfo {
    private int requestCount;
    private long firstRequestTime;

    public RateLimitInfo() {
        this.requestCount = 1;
        this.firstRequestTime = System.currentTimeMillis();
    }

    public int getRequestCount() {
        return requestCount;
    }

    public void incrementRequestCount() {
        this.requestCount++;
    }

    public long getFirstRequestTime() {
        return firstRequestTime;
    }

    public void reset() {
        this.requestCount = 1;
        this.firstRequestTime = System.currentTimeMillis();
    }
}
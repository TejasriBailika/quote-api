package com.example.quote_api.service;

import com.example.quote_api.model.RateLimitInfo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {
    private static final int MAX_REQUESTS = 5;
    private static final long TIME_WINDOW = 60 * 1000;

    private final Map<String, RateLimitInfo> ipRequestMap = new ConcurrentHashMap<>();

    public boolean isAllowed(String ipAddress) {
        long currentTime = System.currentTimeMillis();
        RateLimitInfo rateLimitInfo = ipRequestMap.get(ipAddress);

        if (rateLimitInfo == null) {
            rateLimitInfo = new RateLimitInfo();
            ipRequestMap.put(ipAddress, rateLimitInfo);
            return true;
        }

        if (currentTime - rateLimitInfo.getFirstRequestTime() > TIME_WINDOW) {
            rateLimitInfo.reset();
            return true;
        }

        if (rateLimitInfo.getRequestCount() < MAX_REQUESTS) {
            rateLimitInfo.incrementRequestCount();
            return true;
        }

        return false;
    }

    public long getTimeUntilReset(String ipAddress) {
        RateLimitInfo rateLimitInfo = ipRequestMap.get(ipAddress);
        if (rateLimitInfo == null) {
            return 0;
        }

        long elapsedTime = System.currentTimeMillis() - rateLimitInfo.getFirstRequestTime();
        return Math.max(0, (TIME_WINDOW - elapsedTime) / 1000);
    }
}

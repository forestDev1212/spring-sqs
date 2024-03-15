package com.example.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

public class RedisConnectionManager {
    private static final String REDIS_ENDPOINT = "redis://your-cluster-name.xxxxxx.ng.0001.use1.cache.amazonaws.com:6379";
    private static final int REDIS_PORT = 6379; // Default Redis port

    public static StatefulRedisConnection<String, String> getConnection() {
        RedisURI redisURI = RedisURI.create("redis://" + REDIS_ENDPOINT + ":" + REDIS_PORT);
        RedisClient redisClient = RedisClient.create(redisURI);
        return redisClient.connect();
    }
}
